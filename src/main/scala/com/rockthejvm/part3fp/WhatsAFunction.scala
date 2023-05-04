package com.rockthejvm.part3fp

object WhatsAFunction {

  // in FP, functions are "first-class" citizens - can be passed around
  // the problem is that we're running on top of the JVM, which was built for OOP
  // solution:

  trait MyFunction[A, B] {
    def apply(arg: A): B
  }

  val doubler = new MyFunction[Int, Int] {
    override def apply(arg: Int): Int = arg * 2
  }

  val meaningOfLife = 42
  val meaningDoubled = doubler(meaningOfLife)  // instances can be invoked like functions.  (uses apply)

  // function types
  val doublerStandard = new Function1[Int, Int] {
    override def apply(arg: Int): Int = arg * 2
  }
  val meaningDoubled2 = doublerStandard(meaningOfLife)

  // all functions are instances of FunctionX with apply methods
  // up to 22 params.
  val adder = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
  val aSum = adder(2, 67)

  val aThreeArgFunction = new Function3[Int, String, Double, Boolean] {
    override def apply(v1: Int, v2: String, v3: Double): Boolean = ???
  }

//  val concatenator = new Function2[String, String, String] {
  val concatenator: (String, String) => String = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  def exercise3(int: Int): (String, String) => String = {
    concatenator
  }

  // function values != methods.  methods are defined with "def".

  def main(args: Array[String]): Unit = {
    println(aSum)
    println(concatenator("greg", "stroup"))
  }
}
