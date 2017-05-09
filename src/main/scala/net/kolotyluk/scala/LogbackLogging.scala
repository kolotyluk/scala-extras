package net.kolotyluk.scala

import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.joran.JoranConfigurator
import ch.qos.logback.core.joran.spi.JoranException
import ch.qos.logback.core.util.StatusPrinter

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/** '''Utility Trait for Logback Logging'''
  *  
  * Provides a property getter for the logger. For example:
  * {{{
  * object Main extends App with LogbackLogging {
  *   logger.info("Hello World")
  * }
  * }}}
  * will produce something like
  * {{{
  * 10:24:31,005 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback.groovy]
  * 10:24:31,005 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback-test.xml]
  * 10:24:31,006 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback.xml]
  * 10:24:31,012 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Setting up default configuration.
  * 
  * 10:24:31.102 [main] INFO  net.kolotyluk.scala.Main$ - Hello World
  * }}}
  * 
  * @author Eric Kolotyluk
  */
trait LogbackLogging {
  
  // This statement is actually here to force the LogbackLogging object to instantiate first,
  // as this causes the status of the logback environment to be logged before anything else
  // is logged. Isn't side effect programming wonderful :-)
  if (LogbackLogging.loggerContext == null) println("LogbackLogging.loggerContext == null")

  val logger = LoggerFactory.getLogger(getClass);

}

/** '''Singleton Logging Configuration'''
  *  
  * Log the current logging environment as if we were in debug mode. This is especially useful
  * when troubleshooting, or reverse engineering code, and trying to understand the logging
  * environment.
  * 
  * @author Eric Kolotyluk
  * 
  * @see [[http://logback.qos.ch/manual/configuration.html LogBack Configuration]]
  * 
  */
object LogbackLogging {
  // assume SLF4J is bound to logback in the current environment
  val loggerContext = LoggerFactory.getILoggerFactory().asInstanceOf[LoggerContext]
  StatusPrinter.print(loggerContext)
}