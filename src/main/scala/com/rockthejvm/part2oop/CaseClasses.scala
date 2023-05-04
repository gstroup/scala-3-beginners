package com.rockthejvm.part2oop

object CaseClasses {

  // lightweight data structures - for storage or serialization
  case class Person(name: String, age: Int)

  // case modifier adds some properties to the class
  // 1 - class args are now fields
  val daniel = new Person("Daniel", 99)
  val danielsAge = daniel.age // as if "val" was in the constructor.

  // 2 - toString, equals and hashCode
  val danielToString = daniel.toString // Person("Daniel", 99)
  val daniel2 = new Person("Daniel", 99)
  val isSameDaniel = daniel == daniel2 // true.  would be false for non-case class.

  // 3 - utility methods - copy, productArity, productIterator
  val danielYounger = daniel.copy(age = 3)  // creates a new Person object

  // 4 - CCs have companion objects
  val thePersonSingleton = Person
  val daniel_v2 = Person("Daniel", 99) // CC gives us the apply() method

  // 5 - CCs are serializable - can be sent over the wire or used in a framework like Akka

  // 6 - CCs have extractor patterns for PATTERN MATCHING

  // restrictions - CC must have constructor args
  //  case class CCWithNoArgs { // invalid
  //  }

  // can create a case object though.  which is a singleton.
  case object UnitedKingdom {
    // fields and methods
    def name: String = "The UK"
  }

  case class CCWithArgListNoArgs[A]() // this is legal though.  looks dumb, but might make sense with generics


  def main(args: Array[String]): Unit = {
    println(daniel)
    println(isSameDaniel)
  }
}
