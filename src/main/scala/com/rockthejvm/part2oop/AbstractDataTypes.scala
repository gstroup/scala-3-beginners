package com.rockthejvm.part2oop

object AbstractDataTypes {

  abstract class Animal { // can have fields & methods with no implementation
    val creatureType: String // abstract field
    def eat(): Unit // abstract method
    // child classes must implement abstract fields & methods
    // class can also have non-abstract (implemented) fields
    def preferredMeal: String = "anything"  // accessor method
  }
  //val anAnimal: Animal = new Animal  // cannot instantiate an abstract class

  class Dog extends Animal {
    override val creatureType = "domestic"
    override def eat(): Unit = println("asldkfj")
    // can also override non-abstract fields/methods
    override val preferredMeal: String = "bones" // overriding method with a field
    // can only override a method without args/parentheses.  "accessor methods"
  }

  val aDog: Animal = new Dog

  // traits - like java interfaces
  //  can have abstract fields/methods
  trait Carnivore { // Scala 3 - traits can have constructor args.
    def eat(animal: Animal): Unit  // you could even provide an implementation here in the trait.
  }

  class TRex extends Carnivore {
    override def eat(animal: Animal): Unit = println("I'm a T-Rex, I eat animals")
  }

  // single class inheritance; multiple traits inheritance.  (mix-in)
  trait ColdBlooded
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"
    override def eat(): Unit = println("I'm a croc")
    override def eat(animal: Animal): Unit = println("croc eating animal")
  }

  /*
    Philosophy!
  * abstract classes are THINGS
  * traits are BEHAVIORS
  */

  /*
    Any
      AnyRef
        All classes we write
          scala.Null (the null reference)
      AnyVal
        Int, Boolean, Char, ... primitive types


          scala.Nothing
   */

  val aNonExistentAnimal: Animal = null
  val anInt: Int = throw new NullPointerException() // throw returns Nothing

  def main(args: Array[String]): Unit = {

  }
}
