package com.rockthejvm.part2oop

object Generics {

//  abstract class MyList {
//    def head: Int
//    def tail: Int
//  }
//
//  class Empty extends MyList {
//    override def head: Int = throw new NoSuchElementException
//    override def tail: Int = throw new NoSuchElementException
//  }
//
//  class NonEmpty(override val head: Int, override val tail: Int) extends MyList

  // Let's make this more GENERIC
  abstract class MyList[A] {  // "generic" list - Java: abstract class MyList<A>
    def head: A
    def tail: MyList[A]
  }

  class Empty[A] extends MyList[A] {
    override def head: A = throw new NoSuchElementException
    override def tail: MyList[A] = throw new NoSuchElementException
  }

  class NonEmpty[A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  val listOfInts: MyList[Int] = new NonEmpty[Int](1, new NonEmpty[Int](2, Empty[Int]()))
  val listOfStrings = new NonEmpty[String]("Scala", new NonEmpty[String]("Go", Empty()))

  //problem:
  //  val firstNumber: Any = listOfInts.head // we don't know this is an int.
  // we've lost type safety.
  val firstNumber = listOfInts.head
  val adding = firstNumber + 3
  val firstString = listOfStrings.head

  // multiple generic types
  trait MyMap[K,V]

  // generic methods
  object MyList {
    def from2Elements[A](elem1: A, elem2: A): MyList[A] =
      new NonEmpty[A](elem1, new NonEmpty[A](elem2, new Empty[A]))
  }

  val first2Numbers = MyList.from2Elements(1, 2)
  val first2Numbers_v3 = MyList.from2Elements(1, NonEmpty(2, Empty()))

  def main(args: Array[String]): Unit = {
    println(listOfStrings)
    println(first2Numbers.head)
    println(first2Numbers.tail.tail)
  }

}
