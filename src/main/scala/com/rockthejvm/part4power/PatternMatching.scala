package com.rockthejvm.part4power

import scala.quoted.Expr
import scala.util.Random

object PatternMatching {

  // switch on steroids
  val random = new Random()
  val aValue = random.nextInt(100)

  val description = aValue match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => s"loser $aValue"  // good practice to add this default case, or else we might get an exception.
  }  // return type is determined by the branches.
  // String here.  could be Any if there are different types in different cases

  // decomposition
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  // this matching requires a case class, not just any old class.
  // patterns are matched in ORDER!  i.e. whichever one matches first. put more specific cases first.
  // what if there's no match?  MatchError
  // what type is returned? lowest common ancestor of all types returned by each branch.
  val greeting = bob match {
    // this is matching against a "pattern".  not a constant like we did above.
    case Person(n, a) if a < 18 => s"Hi, I'm young.  my name is $n, and I'm $a."
    case Person(n, a) => s"Hi, I'm $n, and I'm $a years old."
    case _ => "blah"
  }

  // pattern matching on sealed hierarchies:
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Cat(style: String) extends Animal

  val anAnimal: Animal = Dog("Spaniel")
  val aCat: Animal = Cat("fluffy")
  val animalMatch = aCat match {
    case Dog(someBreed) => "it's a dog"
    case Cat(style) => "it's a cat"
  }

  // EXERCISE

  sealed trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  // human readable string
  def show(expr: Expr): String = expr match {
    case Number(n) => s"$n"
    case Sum(l, r) => show(l) + " + " + show(r)
    case Prod(l, r) => {
      def maybeShowParens(expr: Expr) = expr match {
        case Prod(_, _) => show(expr)
        case Number(_) => show(expr)
        case Sum(_, _) => s"(${show(expr)})"
      }
      maybeShowParens(l) + " * " + maybeShowParens(r)
    }
  }



  def main(args: Array[String]): Unit = {
    println(description)
    println(greeting)
    println(animalMatch)

    println(show(Sum(Number(2), Number(3))))
    println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
    println(show(Sum(Prod(Number(2), Number(3)), Number(4))))
    println(show(Prod(Sum(Number(2), Number(3)), Number(4))))
  }
}
