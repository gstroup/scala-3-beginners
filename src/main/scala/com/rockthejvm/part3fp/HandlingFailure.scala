package com.rockthejvm.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure {

  // Try = a potentially failing computation
  // recommended syntax:
  val aTry: Try[Int] = Try(42)
  val aFailedTry: Try[Int] = Try(throw new RuntimeException())

  // subtypes of Try: Success, Failure
  // not recommended syntax:
  val aTry2: Try[Int] = Success(42)
  val aFailedTry2: Try[Int] = Failure(new RuntimeException())

  // main API:
  val checkSuccess = aTry.isSuccess
  val checkFailure = aTry.isFailure

  val chain = aFailedTry.orElse(aTry)

  // most useful methods are map, flatMap, filter, for
  val anIncrementedTry = aTry.map(_ + 1)
  val aFlatMappedTry = aTry.flatMap(mol => Try(s"My meaning of life is $mol"))
  val aFilteredTry = aTry.filter(_ % 2 == 0)  // Success(42).
  // returns failure if the try fails.
  //  returns failure with NoSucheElementException if nothing matches filter.

  // WHY?  to avoid unsafe APIS which can throw exceptions
  def unsafeMethod(): String = throw new RuntimeException("BAD")
  // could use try-catch-finally, but that's not fancy enough.

  // purely functional approach
  val stringLengthPure = Try(unsafeMethod()).map(_.length).getOrElse(-1)

  // DESIGN a better unsafe method
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException("BAD"))
  def betterBackup(): Try[String] = Success("Yay")
  val stringLengthPure2 = betterUnsafeMethod().map(_.length)
  val aSafeChain = betterUnsafeMethod().orElse(betterBackup()).map(_.length)

  // Exercise:
  val host = "localhost"
  val port = "8081"
  val myUrl = "www.google.com"

  class Connection {
    val random = new Random()
    def get(url: String): String = {
      if (random.nextBoolean()) "<html>Success</html>"
      else throw new RuntimeException("fail")
    }

    def getSafe(url: String): Try[String] =
      Try(get(url))
  }

  object HttpService {
    val random = new Random()

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("fail 2")
    }

    def getConnectionSafe(host: String, port: String): Try[Connection] =
      Try(getConnection(host, port))
  }

  val connectionTry: Try[Connection] = Try(HttpService.getConnection(host, port))

  val maybeHtml2 = for {
    conn <- HttpService.getConnectionSafe(host, port)
    html <- conn.getSafe(myUrl)
  } yield html  // returns Try[String]

  def main(args: Array[String]): Unit = {
    // My work:
    val result = for {
      conn <- connectionTry
      result <- Try(conn.get(myUrl))
    } yield result
    println(result.fold(f => f.getMessage, s => s))  //.getOrElse(s"Failed to get url: $myUrl"))
  }
}
