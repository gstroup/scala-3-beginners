package com.rockthejvm.part3fp

import scala.util.Random

object Options {

  // options are like collections with 0 or 1 value
  val anOption: Option[Int] = Option(42)
  val anEmptyOption: Option[Int] = Option.empty

  // an alternative way, but not really recommended:
  val aPresentValue: Option[Int] = Some(4)
  val anEmptyOption2: Option[Int] = None

  // "standard" API
  val isEmpty = anOption.isEmpty
  val innerValue = anOption.getOrElse(90)
  val anotherOption = Option(46)
  val aChainedOption = anEmptyOption.orElse(anotherOption)

  // map, flatMap, filter, for
  val anIncrementedOption = anOption.map(_ + 1)  // Some(43)
  val aFilteredOption = anIncrementedOption.filter(_ % 2 == 0)  // None
  val aFlatMappedOption = anOption.flatMap(value => Option(value * 10))


  // why options??  To work with unsafe API
  def unsafeMethod(): String = null
  def fallbackMethod(): String = "valid result"

  // defensive old style
  //val stringLength = if (unsafeMethod() == null) -1 else unsafeMethod().length

  // option-style
  val stringLengthOption = Option(unsafeMethod()).map(_.length)

  // a use-case for orElse
  val someResult = Option(unsafeMethod()).orElse(Option(fallbackMethod()))

  // DESIGN - return options if you're not sure
  def betterUnsafeMethod(): Option[String] = None
  def betterFallbackMethod(): Option[String] = Some("valid result")
  val betterChain = betterUnsafeMethod().orElse(betterFallbackMethod())

  // example: Map.get
  val phonebook = Map("Daniel" -> 12345)
  val marysNumber = phonebook.get("mary")  // None

  val config: Map[String, String] = Map(
    "host" -> "123.234.234.4",
    "port" -> "8081"
  )

  class Connection {
    def connect(): String = "Connection successful"
  }

  object Connection {
    val random = new Random()

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  def main(args: Array[String]): Unit = {
    val host = config.get("host")
    val port = config.get("port")
    val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
    val connStatus = connection.map(_.connect())
    val connStatus2 =
      config.get("host").flatMap(h =>
        config.get("port").flatMap(p =>
          Connection(h, p).map(_.connect())
        )
     )

    val connStatus3 = for {
      h <- config.get("host")
      p <- config.get("port")
      conn <- Connection(h, p)
    } yield conn.connect()


    //println(connStatus.getOrElse("Conn failed"))
//    println(connStatus2.getOrElse("Conn failed 2"))
    println(connStatus3.getOrElse("Conn failed 3"))


    //    val conn: Option[Connection] = Connection(config.getOrElse("host", "0.0.0.0"), config.getOrElse("port", "80"));
////    val result = Option(conn.connect());
//    println(conn.
  }
}
