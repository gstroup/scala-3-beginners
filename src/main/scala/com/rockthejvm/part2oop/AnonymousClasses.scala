package com.rockthejvm.part2oop

object AnonymousClasses {

  abstract class Animal {
    def eat(): Unit
  }

  class Snake extends Animal {
    override def eat(): Unit = println("I'm a snake")
  }
  val aSnake = new Snake

  val aCow = new Animal{
    override def eat(): Unit = println("moo")
  }

  class Person(name: String) {
    def sayHi(): Unit = println(s"Hi, I'm $name")
  }

  val jim = new Person("Jim") {
    override def sayHi(): Unit = println("JIMMY")
  }

  def main(args: Array[String]): Unit = {
    aCow.eat()
    aSnake.eat()
    jim.sayHi()
  }
}
