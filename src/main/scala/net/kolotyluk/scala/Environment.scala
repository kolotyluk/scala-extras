package net.kolotyluk.scala

import scala.collection.JavaConversions._

/** '''Utility Trait for System Environment'''
  * 
  * Provides a property getter for the singleton configuration.
  * 
  * Typical use:
  * {{{
  * object Main extends App with Environment {
  *   println(environment.get("USERNAME"))
  * }
  * }}}
  * 
  * @author Eric Kolotyluk
  * @see [[https://docs.oracle.com/javase/8/docs/api/java/lang/System.html#getenv() Java System.getenv()]]
  */
trait Environment {
  
  /** Public property getter for System Environment
   * 
   */
  def environment = Environment.environment

}

/** '''Typesafe Environment object'''
  *
  * This logs the current environment variables. It is best to log the environment
  * before logging the configuration because the configuration may depend on the
  * environment.
  *
  * @author Eric Kolotyluk
  */
object Environment extends LogbackLogging {
  
  // It is generally good operational practice to put as much configuration information
  // in the service log as possible at start-up. This can help troubleshooting efforts
  // by having a more complete understanding of the initial state of service. EK
  
  // todo - establish an optional mechanism for this - EK
  
  val environment = System.getenv
  val environmentLog =
    environment
      .entrySet
      .toList
      .sortBy(_.getKey)
      .map( entry => s"${entry.getKey} = ${entry.getValue}" )
      .mkString("<environment>\n\t", "\n\t", "\n</environment>")
  // Best to use a single print statement than several in a loop in order to keep
  // asynchronous messages from polluting a set of information. Also, this is more
  // functional than imperative. EK
  println(environmentLog)
  
  // todo - use the logging system, if you know it's there, and which one it is - EK

}