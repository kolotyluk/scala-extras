# scala-extras

Some useful *extra* stuff for Scala developers

## Coordinates

    group-id: net.kolotyluk
    artifact-id: scala-extras_2.11

Search [Maven Central](https://search.maven.org/#search) for more details

## Example

    import net.kolotyluk.scala.LogbackLogging
    
    object Main extends App with LogbackLogging {
      logger.info("Hello Scala Extras")
    }

## Table of Contents  
1. [Purpose](#purpose)  
1. [Traits](#traits)
    1. [Environment](#environment)
    1. [LogbackLogging](#logback-logging)
    1. [Typesafe Configuration](#typesafe-configuration)

# <a name="purpose"/>Purpose

The main purpose of these extras is to simplify some of the basic trivial boilerplat stuff when
writing Scala code

# <a name="traits"/>Traits

Traits are used to add basical functionality, such as logging, configuration, etc. to your code.

## <a name="environment"/>Environment

Adds the **environment** property to your code.

### Example

    import net.kolotyluk.scala.Environment
    import net.kolotyluk.scala.LogbackLogging
    
    object Main extends App with LogbackLogging wtih Environment {
      logger.info("USERNAME = " + environment.get("USERNAME"))
    }

### Side Effects

Logs the system environment variables. This can be useful in troubleshooting situations, expecially when
environment variable settings affect your code.

## <a name="logback-logging"/>LogbackLogging

Adds the **logger** property to your code.

The most common logging is the SLF4J interface with a Logback implementation.

### Example

    import net.kolotyluk.scala.LogbackLogging
    
    object Main extends App with LogbackLogging {
      logger.info("Hello Scala Extras")
    }

### Side Effects

Logs the Logback configuration. This can be useful in troubleshooting situations, expecially when
you are troubleshooting logging issues.

## <a name="typesafe-configuration"/>Typesafe Configuration

Adds the **config** property to your code.

By default, the Typesafe Config includes all the Java system properties, so you don't need a separate
trait for that.

### Example

    import net.kolotyluk.scala.Environment
    import net.kolotyluk.scala.Typesafe.Configuration
    import net.kolotyluk.scala.LogbackLogging
    
    object Main extends App with LogbackLogging wtih Environment with Configuration {
      logger.info("USERNAME = " + environment.get("USERNAME"))
      logger.info("user.name = " + config.getString("user.name"))
    }

### Side Effects

Logs the configuration settings. This can be useful in troubleshooting situations, expecially when
configurations settings affect your code.
