package com.rockthejvm.part1basics

object CBNvsCBV {

  // CBV call by value = args are evaluated before function invocation
  def aFunction(arg: Int): Int = arg + 1
  val aComputation = aFunction(23 + 67)  // 90 is passed in to the function

  // CBN call by name = args are passed LITERALLY as an expression
    // this ugly arrow syntax is used to denote call by name:
  def aByNameFunction(arg: => Int): Int = arg + 1
  val anotherComputation = aByNameFunction(23 + 67)
  // same output, but argument is evaluated later - at every reference

  def printTwiceByValue(x: Long): Unit =
    println("By value: " + +x)
    println("By value: " + +x)

  def printTwiceByName(x: => Long): Unit =
    println("By name: " + +x)
    println("By name: " + +x)

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  def main(args: Array[String]): Unit = {
//    println(aComputation)
//    println(anotherComputation)
    printTwiceByValue(System.nanoTime())  // prints same value twice
    printTwiceByName(System.nanoTime())   // prints two different values
    //println(infinite())  --> Stack overflow
    printFirst(412, infinite())  // -- never calls infinite.  prints the first val.
  }
}
