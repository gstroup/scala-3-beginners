package com.rockthejvm.part2oop

object Objects {

  object MySingleton { // type + the only instance of the type
    val aField = 45
    def aMethod(x: Int) = x +1
  }

  val theSingleton = MySingleton
  val anotherSingleton = MySingleton
  val isSameSingleton = theSingleton == anotherSingleton
  // objects can have fields & methods
  val theSingletonField = MySingleton.aField
  val theSingletonMethodCall = MySingleton.aMethod(99)

  // more interesting stuff
  class Person(name: String) {
    def sayHi(): String = s"Hi, my name is $name"
  }
  // companions = class + object with the same name in the same file
  object Person {
    // companions can access each other's private fields and methods
    val N_EYES = 2
    def canFly(): Boolean = false
  }

  // methods & fields in classes are used for instance-dependent stuff
  val mary = new Person("Mary")
  val mary_v2 = new Person("Mary")
  val marysGreeting = mary.sayHi()

  // methods & fields in the singleton object are used for instance independent stuff.  "static"
  val humansCanFly = Person.canFly()
  val nEyesHuman = Person.N_EYES

  // equality
  // 1 - equality of reference (pointing to same instance).  (In java, this is "==")
  val sameMary = mary.eq(mary_v2) // false
  // 2 - equality of "sameness"  up to us to define.  In java, this is ".equals()"
  val sameMary_v2 = mary equals mary_v2 // false
  val sameMary_v3 = mary == mary_v2 // in scala, this is the same as "equals"  (reference)  // false

  // objects can extend classes
  object BigFoot extends Person("Big Foot")

  // A Scala application is a singleton object with a main method.
  //    def main(args: Array[String]): Unit
  /* Java:
    public class Objects {
      public static void main(String[] args) { ...}
    }
  */
  def main(args: Array[String]): Unit = {
    println(isSameSingleton)
  }
}
