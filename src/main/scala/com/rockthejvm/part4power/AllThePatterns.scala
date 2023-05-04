package com.rockthejvm.part4power

import com.rockthejvm.practice.*

object AllThePatterns {

  // we already did pattern matching on constants & case classes.  now we'll do more.

  object MySingleton
  // 1 - constants
  val someValue: Any = "scala"
  val constants = someValue match {
    case 42 => "a number"
    case "scala" => "SCALA"
    case true => "the truth"
    case MySingleton => "a singleton"
  }

  // 2 - match anything
  val matchAnythingVar = 2 + 3 match {
    case something => s"I matched something: $something"
  }
  val matchAnything = someValue match {
    case _ => "I will match everything"  // use _ wildcard if the variable is not important
  }

  // 3 - tuples
  val aTuple = (1,4)
  val matchTuple = aTuple match {
    case (1, somethingElse) => s"a tuple with 1 and $somethingElse"
    case(something, 2) => s"a tuple with 2 as its second child"
  }

  // PM structures can be NESTED.
  val nestedTuple = (1, (2,3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) => "..."
  }

  // 4 - case classes
  val aList: LList[Int] = Cons(1, Cons(2, Empty()))
  val matchList = aList match {
    case Empty() => "empty list"
    case Cons(head, Cons(_, tail)) => s"non-empty list starting with $head"
  }

  // Option and Try are case classes in the standard library
  val anOption: Option[Int] = Option(2)
  val matchOption = anOption match {
    case None => "empty option"
    case Some(value) => s"non-empty value: $value"
  }

  // 5 - list patterns
  val aStandardList = List(1,2,3,42)
  val matchStandardList = aStandardList match {
    case List(1, _, _, _) => "list with 4 elements, first is 1"
    //case List(1, _*) => "list starting with 1, any number of elements"
    case 1 :: tail => "list starting with 1, any number of elements (again)"
    case List(1, 2, _) :+ 42 => "list ending in 42"
    case head :: tail => "deconstructed list"
  }

  // 6 - type specifiers
  val unknown: Any = 2
  val matchTyped = unknown match {
    case anInt: Int => s"I matched an int, so I can do math: ${anInt + 2}"
    case aString: String => s"I matched a String: $aString"
    case _: Double => "Matched a double, don't care about the value"
  }

  // 7 - name binding
  val bindingNames = aList match {
    case Cons(head, rest @ Cons(_, tail)) => s"Can use $head, $tail, $rest"
  }

  // 8 - chained patterns or multi-patterns
  val multiMatch = aList match {
    case Empty() | Cons(0, _) => "an empty looking list"  // like writing 2 cases in one
    case _ => "crap"
  }

  // 9 - if guards - used for filtering
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialEl, _)) if specialEl > 5 => "second element > 5"
    case _ => "crap"
  }

  // Example:
  val aSimpleInt = 45
  val isEven_bad = aSimpleInt match {
    case n if n % 2 == 0 => true
    case _ => false
  }
  // the code above is not good!  it's an anti-pattern
  val isEven_bad2 = if (aSimpleInt % 2 == 0) true else false
  // simpler and better:
  val isEvenGood = aSimpleInt % 2 == 0

  val numbers = List(1,2,3,4)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => " a list of strings"
    case listOfInts: List[Int] => " a list of ints"
    case _ => "something else"
  }
  def main(args: Array[String]): Unit = {
    println(matchStandardList)
    println(bindingNames)
    println(numbersMatch)  // prints "a list of strings" !
  }

  /**
   * PM runs at runtime
   * - uses reflection
   * - generic types are erased at runtime!
   *    List[String] --> List
   *    List[Int] --> List
   */
}
