package com.rockthejvm.part2oop

import scala.language.postfixOps

object MethodNotation {

  class Person(val name: String, val age: Int, favoriteMovie: String) {
    // infix modifier allows us to call without a dot.
    // only works on functions with a single argument.
    infix def likes(movie: String): Boolean =
      movie == favoriteMovie

    def +(person: Person): String =
      s"${this.name} is with ${person.name}"

    // prefix position - no parameters
    // unary ops supported by scala: -, +, ~, !
    def unary_- : String =
      s"$name's alter ego"

    def isAlive: Boolean = true

    def apply(): String =
      s"Hi, my name is $name and I love $favoriteMovie"

    infix def +(nickname: String): Person =
      new Person(name + " " + nickname, age, favoriteMovie)

    def unary_+ : Person = new Person(name, age+1, favoriteMovie)

    def apply(times: Int): String =
      s"$name watched $favoriteMovie $times times"
  }
  // methods can have weird names such as ?, ! in akka
  //  >> is used in cats for sequencing IOs

  val mary = new Person("Mary", 34, "Inception")
  val john = new Person("John", 36, "Fight Club")

  val negativeOne = -1

  def main(args: Array[String]): Unit = {
    println(mary.likes("Fight Club"))
    println(mary likes "Fight Club")
    println(mary + john)
//    println(mary.+(john)) // identical
    println(2 + 3)
//    println(2.+(3))  // identical

    //prefix position:
    println(-mary)

    // postfix position:
    println(mary.isAlive)
    println(mary isAlive)  // discouraged!  no point really, just use the dot.

    // apply method is special!
    println(mary.apply());
    println(mary());

    val maryWithNickName = mary + "the rockstar"
    println(maryWithNickName.name)
    val maryOlder = +mary
    println(maryOlder.age)
    println(mary(17))
  }
}
