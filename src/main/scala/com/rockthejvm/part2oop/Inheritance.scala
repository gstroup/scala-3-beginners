package com.rockthejvm.part2oop

object Inheritance {

  class Animal {
    val creatureType = "wild"
    def eat(): Unit = println("nomnomnom")
  }

  class Cat extends Animal {
    def crunch() = {
      eat()
      println("crunch")
    }
  }

  val cat = new Cat();

  class Person(val name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name)
      // must specify super-constructor

  class Dog extends Animal {
    override val creatureType = "Domestic"
    override def eat(): Unit = println("tasty meat")
    override def toString: String = "a dog"
  }

  // subtype polymorphism
  val dog: Animal = new Dog

  // overloading vs overriding.
  class Crocodile extends Animal {
    // this is overriding obviously
    override val creatureType: String = "very wild"
    override def eat(): Unit = println("I can eat anything!")

    // this is overloading.  same name, different signature.
    def eat(animal: Animal): Unit = println("I'm eating this deer")

    def eat(dog: Dog): Unit = println("eating a dog")
    def eat(person: Person): Unit = println("eating a human")
    def eat(person: Person, dog: Dog): Unit = println("eating a human and a dog")
//    def eat(): Int = 45  // not a valid overload, since there is already an eat() method that returns Unit.
    def eat(dog: Dog, person: Person): Unit = println("eating a dog and a human")

  }

  def main(args: Array[String]): Unit = {
//    cat.eat()
    cat.crunch()
    dog.eat()  // the most specific method will be called!  "tasty meat"
    println(dog)

    val croc = new Crocodile
    val greg = new Person("Greg", 47)
    croc.eat(greg)
  }
}
