package com.rockthejvm.part4power

object PatternsEverywhere {

  // big idea #1: catches are actually MATCHES
  val potentailFail = try {
    // code
  } catch {
    case e: RuntimeException => "runtime ex"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }

  // big idea #2: for comprehension generators are based on pattern matching
  val aList = List(1,2,3,4)
  val evenNumbers = for {
    n <- aList if n%2 == 0
  } yield n

  val tuples = List((1,2), (3,4))
  val filteredTuples = for {
    (first, second) <- tuples if first < 3
  } yield second * 10

  // big idea #3: new syntax in scala deconstruction, like in JS or python
  val aTuple = (1,2,3)
  val (a,b,c) = aTuple

  val head :: tail = tuples

  def main(args: Array[String]): Unit = {
    println(evenNumbers)
    println(filteredTuples)
    println(s"deconstructed: $a, $b, $c")  // 1, 2, 3
    println(s"head $head, tail $tail")  //
  }
}
