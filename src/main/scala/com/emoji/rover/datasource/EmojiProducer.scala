package com.emoji.rover.datasource

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import java.util.Properties
import scala.util.Random

object EmojiProducer {
  def main(args: Array[String]): Unit = {
    val kafkaBrokers = "localhost:9092"
    val kafkaTopic = "emoji-pool"
    val producerProps = new Properties()

    producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokers)
    producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](producerProps)
    val emojis = List( "😁", "😂", "😎", "😍", "👍", "👏", "❤️", "🚀","💃","🏆","🙌", "🤩")
    val random = new Random()

    while (true) {
      val randomEmoji = emojis(random.nextInt(emojis.length))
      val key = "emoji"
      val value = randomEmoji
      val record = new ProducerRecord[String, String](kafkaTopic, key, value)

      producer.send(record)
      println(s"Sent emoji: $value to Kafka topic: $kafkaTopic")

      Thread.sleep(500)
    }

    producer.close()
  }
}

