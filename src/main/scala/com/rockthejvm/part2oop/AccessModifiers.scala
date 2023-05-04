package com.rockthejvm.part2oop

object AccessModifiers {

  class Person(val name: String) {
    protected def sayHi(): String = s"Hi, my name is $name"
    // protected == access inside the class and child classes
    private def watchNetflix(): String = "laskdfja" // only accessible inside this class.
  }
  class Kid(override val name: String, age: Int) extends Person(name) {
    def greetPolitely(): String = // no modifier == "public"
      sayHi() + " I'm a kid"
  }

  val aPerson = new Person("Maddie")
  val aKid = new Kid("Bobby", 5)

  def main(args: Array[String]): Unit = {
    println(aKid.greetPolitely())
  }
}
