package com.emoji.rover.web

import scala.sys.process._

object PyExecutor {
  def main(args: Array[String]): Unit = {
    //FIXME:: Replace with the path to your virtual environment
    val virtualEnvPath = "/Users/b0a04gl/.virtualenvs/py"
    //FIXME:: Replace with the path to your target py file path
    val pythonScript = "/Users/b0a04gl/Documents/#BA6/emoji-rover/emoji-fanout/emoji-fanout.py"

    val command = s"$virtualEnvPath/bin/python -u $pythonScript"
    println(command)

    val process = Process(command)
    val exitCode = process.!

    if (exitCode == 0) {
      println("Script executed successfully.")
    } else {
      println(s"Script execution failed with exit code $exitCode.")
    }
  }
}


/***
 *
 *
 * val output = command.!!
 * while(output.nonEmpty) {
 * println(output)
 * }
 */
