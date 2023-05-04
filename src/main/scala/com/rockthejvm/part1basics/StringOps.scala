package com.rockthejvm.part1basics

object StringOps {

  val aString: String = "Hello there"
  val secondChar = aString.charAt(1)
  val words = aString.split(" ")  // returns an array
  val startsWithHell = aString.startsWith("Hell")
  val allDashes = aString.replace(' ', '-')
  val allUppers = aString.toUpperCase()  // or toLowerCase
  val nChars = aString.length
  // other functions in scala standard library
  val reversed = aString.reverse
  val aBunchOfChars = aString.take(10) // takes first 10 chars
  val numberAsString = "33"
  val number = numberAsString.toInt

  // interpolation
  val name = "Alice"
  val age = 12
  val greeting = s"Hello, I'm $name and I am $age years old"
  val greeting_v3 = s"Hello, I'm $name and I will be ${age + 1} years old"

  // f-interpolation
  val speed = 1.2f
  val myth = f"$name can eat $speed%4.5f burgers per minute."

  // raw-interpoliation
  val escapes = raw"This is a \n newline"

  def main(args: Array[String]): Unit = {
    println(myth)
    println(escapes)
  }
}
