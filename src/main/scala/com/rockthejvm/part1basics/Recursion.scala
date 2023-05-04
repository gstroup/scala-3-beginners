package com.rockthejvm.part1basics

import scala.annotation.tailrec

object Recursion {

  def sumUntil(n:Int): Int =
    if (n <= 0) 0
    else n + sumUntil(n-1)  // "stack" recursion


  def sumUntil_v2(n: Int): Int = {
    @tailrec
    def sumUntilTailrec(x: Int, acc: Int): Int =
      if (x <= 0) acc
      else sumUntilTailrec(x-1, acc + x)
      // TAIL recursion = recursive call occurs LAST in its code path.
      // no risk of stack overflow

    sumUntilTailrec(n, 0)
  }

  def sumNumbersBetween(a: Int, b: Int): Int =
    if (a > b) 0
    else a + sumNumbersBetween(a + 1, b)

  def sumNumbersBetween_v2(a: Int, b: Int): Int = {
    @tailrec
    def sumTailrec(currentNumber: Int, acc: Int): Int =
      if (currentNumber > b) acc
      else sumTailrec(currentNumber+1, acc+currentNumber)

    sumTailrec(a, 0)
  }

  def stringN(s: String, n: Int): String =
    @tailrec
    def stringNRec(x: Int, acc: String): String =
      if (x <= 0) acc
      else stringNRec(x-1, acc+s)

    stringNRec(n, "")

  def fibonacci(n: Int): Int = {
    def fibonacciRec(i: Int, last: Int, previous: Int): Int = {
      if (i >= n) last
      else fibonacciRec(i+1, last + previous, last)
    }
    if (n <= 2) 1
    else fibonacciRec(2, 1, 1)
  }

  def main(args: Array[String]): Unit = {
    println(sumUntil(10))
    println(sumUntil_v2(20000))
    println(sumNumbersBetween(3,7))
    println(sumNumbersBetween_v2(3,7))
    println(stringN("hi", 5))
    println(fibonacci(8))
  }
}
