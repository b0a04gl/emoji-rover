
# Emoji Rover - Real-time Emoji Aggregation and Display

Emoji Rover is a real-time data processing project that involves sending and aggregating emojis, visualizing them on a web page using Flask and JavaScript.

## Inspiration for this work

![emoji-rover](https://miro.medium.com/v2/resize:fit:1190/1*rSRWALA4XzOdDcn-5vv7Zw.gif)

[Capturing A Billion Emo(j)i-ons. Moving from a third party system to our… | by Dedeepya Bonthu | Disney+ Hotstar](https://blog.hotstar.com/capturing-a-billion-emojis-62114cc0b440)

## Project Overview

Emoji Rover processes a stream of emojis in the following steps:

1. Emojis are produced and sent to Apache Kafka using the `EmojiProducer` Scala object.
2. Apache Spark reads these emojis from Kafka, aggregates their counts, and stores the results in Redis using the `EmojiConsumer` Scala object.
3. A Python Flask web application retrieves emojis with their counts from Redis and populates them on a web page in an animated way.

## Project Components

The project consists of the following components:

- **EmojiProducer.scala**: This Scala object is responsible for producing emojis and sending them to an Apache Kafka topic.

- **EmojiConsumer.scala**: This Scala object reads emojis from Kafka, performs real-time aggregation, and stores the results in a Redis database.

- **Python Flask Web App**: The Flask web application retrieves emoji counts from Redis and visualizes them in an animated way on a web page.

- **JavaScript (emoji-fanout-index.html)**: The JavaScript code within the web page fetches data from the Flask API and animates the emojis on the front end.

## Project Setup

Before running the project, ensure you have the necessary components installed:

- Apache Kafka
- Apache Spark
- Redis
- Python (for the Flask web app)
- Scala (for the EmojiProducer and EmojiConsumer)
- SBT (Scala Build Tool)

To run the project:

1. Start your Kafka, Spark, and Redis instances.

2. Compile and run the `EmojiProducer` Scala object to send emojis to Kafka.

3. Compile and run the `EmojiConsumer` Scala object to process emojis and aggregate counts to Redis.

4. Start the Python Flask web application to serve the web page.

5. Open the web page in your browser to see emojis being animated in real-time.

## Project Configuration

- Configuration settings such as Kafka brokers, Redis connection details, and the web page template can be adjusted within the respective source files (EmojiProducer.scala, EmojiConsumer.scala, and the Flask web app).

## Technologies Used

- Scala
- Apache Kafka
- Apache Spark
- Redis
- Python (Flask)
- JavaScript (for frontend animation)

## Demo
TBD
