package com.rockthejvm.part2oop

object Exceptions {

  val aString: String = null // avoid using null.  this is just for demonstration

  // 1 - throw
  //val aWeirdValue: Int = throw new NullPointerException("my cool npe")  // returns Nothing

  // type heirarchy:
  // Throwable
  //    Error - eg: SOError, OOMError
  //    Exception - problem with logic, e.g. NPE, NoSuchElementException, etc.

  // 2 - catch
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new NullPointerException // RuntimeException("no int for you")
    else 42

  val potentialFail = try {
    getInt(true)
  } catch {
    // always place most specific exceptions first!
    case e: NullPointerException => 35
    case e: RuntimeException => 54
      // etc
  } finally {
    // optional.  always runs.  used to close resources.
    println("Finally.")
    // just returns Unit
  }

  class MyException extends RuntimeException {
    // my fields and methods
    override def getMessage: String = "MY MESSAGE"
  }

  val myException = new MyException

  def infinite(): Int = 1 + infinite()

  def oomCrash(): Unit = {
    def bigString(n: Int, acc: String): String = {
      if (n ==0) acc
      else bigString(n-1, acc + acc)
    }
    bigString(2398238, "Scala")
  }

  def main(args: Array[String]): Unit = {
//    println(aString.length)  // NPE
    println(potentialFail)
//    val throwingMyException = throw myException
//    infinite()
    oomCrash()
  }
}
