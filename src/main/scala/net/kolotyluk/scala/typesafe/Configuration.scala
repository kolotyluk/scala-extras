package net.kolotyluk.scala.typesafe

import com.typesafe.config.ConfigFactory
import scala.collection.JavaConversions._
import net.kolotyluk.scala.Environment
//import net.kolotyluk.scala.LogbackLogging

/** '''Utility Trait for Typesafe Config'''
  * 
  * Provides a property getter for the singleton configuration.
  * 
  * Typical use:
  * {{{
  * object Main extends App with Configuration {
  *   println("user.name = " + config.getString("user.name"))
  * }
  * }}}
  * It is important that com.typesafe.config.ConfigFactory only be called once.
  * If it is called more than once, important configuration information can be
  * lost, or there can be other inconsistencies that are hard to troubleshoot.
  * For example, if you are using this in an Akka environment: then pass the config
  * in when you call create the ActorSystem
  * {{{
  * // Note: past the established config to the ActorSystem, so that
  * // ConfigFactory.load() is not called more than once.
  * private implicit val system = ActorSystem("somename", config)
  * }}}
  * 
  * @author Eric Kolotyluk
  * @see [[https://github.com/typesafehub/config Typesafe Config on Github]]
  */
trait Configuration {
  
  /** Public property getter for Typesafe config
   * 
   */
  def config = Configuration.configuration
  
}

/** '''Typesafe Configuration object'''
  *
  * This forces instantiation of the Environment object, and as a side effect,
  * it logs the current environment variables. It is best to log the environment
  * before logging the configuration because the configuration may depend on the
  * environment. Finally, it logs the current configuration.
  *
  * @author Eric Kolotyluk
  */
object Configuration extends Environment {
  
  environment // trick to force Environment to initialize first - EK
  
  // Note: Typesafe config contains Java system properties, so we don't have to get that separately. EK

  val configuration = ConfigFactory.load()
    
  // It is generally good operational practice to put as much configuration information
  // in the service log as possible at start-up. This can help troubleshooting efforts
  // by having a more complete understanding of the initial state of service. EK
  
  // todo - establish an optional mechanism for this - EK

  val configLog =
    configuration
      .entrySet
      .toList
      .sortBy(_.getKey)
      .map( entry => s"${entry.getKey} = ${entry.getValue.render}" )
      .mkString("<config>\n\t", "\n\t", "\n</config>")
  // Best to use a single print statement than several in a loop in order to keep
  // asynchronous messages from polluting a set of information. Also, this is more
  // functional than imperative. EK
  println(configLog)
  
  // todo - use the logging system, if you know it's there, and which one it is - EK
}