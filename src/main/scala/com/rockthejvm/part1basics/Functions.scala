package com.rockthejvm.part1basics

object Functions {

  def aFunction(a: String, b: Int): String ={
    a + " " + b
  }

  val aFunctionInvocation = aFunction("Scala", 999999)

  def aNoArgFunction(): Int = 45
  def aParameterLessFunction: Int = 45 // 2 styles of the same thing

  // functions can be recursive
  def stringConcatenation(str: String, n: Int): String =
    if (n == 0) ""
    else if (n == 1) str
    else str + stringConcatenation(str, n-1)

  val scalax3 = stringConcatenation("Scala", 3)

  // when you need loops, use recursion!!

  // some functions return nothing,
  def aVoidFunction(aString: String): Unit = println(aString)

  // BAD!  Don't cause side effects.
  def computeDoubleStringWithSideEffect(aString: String): String = {
    aVoidFunction(aString)
    aString + aString
  }

  // you can wrap a small function in a bigger one.
  def aBigFunction(n: Int): Int = {
    def aSmallFunction(a: Int, b: Int): Int = a + b
    aSmallFunction(n, n+1)
  }

  def greeting(name: String, age: Int): String = s"Hi, my name is $name and I am $age years old"

  def factorial(n: Int): Int =
    if (n <= 0) 0
    else if (n == 1) 1
    else n * factorial(n-1)
//    def fact(n:Int, acc:Int): Int = {  // this also works
////      println("***" + n + " " +acc)
//      if (n < 0) 0
//      else if (n == 0) acc
//      else fact(n - 1, n * acc)
//    }
//    fact(n, 1)

  def fibonnaci(n: Int): Int =
    // works but very inefficient!
    if (n <= 2) 1
    else fibonnaci(n-1) + fibonnaci(n-2)

  def isPrime(n: Int): Boolean =
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n %t != 0 && isPrimeUntil(t-1)

    isPrimeUntil(n/2)

  def main(args: Array[String]): Unit = {
    println(aFunction("hey", 3))
    println(scalax3)
    println(greeting("greg", 98))
    println(factorial(5))
    println(fibonnaci(8))
    println(isPrime(8))
  }
}
