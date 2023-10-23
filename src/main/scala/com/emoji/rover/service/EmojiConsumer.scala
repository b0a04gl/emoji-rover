package com.emoji.rover.service

import org.apache.spark.sql.{ForeachWriter, SparkSession}
import org.apache.spark.sql.functions.{count, explode, split}
import redis.clients.jedis.Jedis

object EmojiConsumer {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("EmojiConsumer")
      .master("local[2]")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")


    import spark.implicits._

    val kafkaBrokers = "localhost:9092"
    val kafkaTopic = "emoji-pool"
    val kafkaGroupId = "hotstar_2023"
    val kafkaStartingOffsets = "earliest"


    val kafkaStream = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", kafkaBrokers)
      .option("subscribe", kafkaTopic)
      .option("startingOffsets", kafkaStartingOffsets)
      .option("group.id", kafkaGroupId)
      .load()

    val processedStream = kafkaStream.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")

    val wordsDF = processedStream.select(explode(split($"value", " ")).alias("emoji"))

    val aggregatedCount = wordsDF.groupBy("emoji").agg(count("*").alias("count"))

    val customForeachWriter = new ForeachWriter[org.apache.spark.sql.Row] {
      private var jedis: Jedis = _

      def open(partitionId: Long, version: Long): Boolean = {
        jedis = new RedisInitializer().getJedisInstance
        true
      }



      def process(value: org.apache.spark.sql.Row): Unit = {
        val emoji = value.getAs[String]("emoji")
        val count = value.getAs[Long]("count")
        println(s"Emoji: $emoji, Count: $count")
          jedis.hincrBy("emoji_counts", emoji, count)
      }



      def close(errorOrNull: Throwable): Unit = {
        if (jedis != null) {
          jedis.close()
        }
      }
    }

    val query = aggregatedCount.writeStream
      .outputMode("complete")
      .foreach(customForeachWriter)
      .start()

    query.awaitTermination()
  }
}
