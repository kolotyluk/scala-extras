package net.kolotyluk.scala

import net.kolotyluk.scala.typesafe.Configuration

/**
 * @author Eric Kolotyluk
 */
object Main extends App with LogbackLogging with Environment with Configuration {
  logger.info("Hello Scala Extras")
  logger.info(s"USERNAME = " + environment.get("USERNAME"))
  logger.info("user.name = " + config.getString("user.name"))
}