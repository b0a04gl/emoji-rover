ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.13"

lazy val root = (project in file("."))
  .settings(
    name := "emoji-rover"
  )

resolvers += "Typesafe Repository" at "https://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.1.2", // Replace with the desired Spark version
  "org.apache.spark" %% "spark-sql" % "3.1.2", // Spark SQL module
  "org.scala-lang" % "scala-library" % "2.12.10", // Replace with your desired Scala version
  "org.apache.spark" %% "spark-streaming" % "3.1.2",
  "redis.clients" % "jedis" % "4.3.1",
  "org.apache.spark" %% "spark-sql-kafka-0-10" % "3.1.2"
)

