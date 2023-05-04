package com.rockthejvm.practice

import scala.annotation.tailrec

// singly linked list
// [1,2,3] = [1] -> [2] -> [3] -> |
abstract class LList[A] {
  def head: A
  def tail: LList[A]
  def isEmpty: Boolean
  def add(element: A): LList[A] = Cons(element, this)

  infix def ++(anotherList: LList[A]): LList[A]

  def map[B](transformer: A => B): LList[B]
  def filter(predicate: A => Boolean): LList[A]
  def withFilter(predicate: A => Boolean): LList[A] = filter(predicate)
  def flatMap[B](transformer: A => LList[B]): LList[B]
}

// we can make this a case class.
case class Empty[A]() extends LList[A] {
  override def head: A = throw new NoSuchElementException
  override def tail: LList[A] = throw new NoSuchElementException
  override def isEmpty = true
  override def toString = "[]"

  override infix def ++(anotherList: LList[A]): LList[A] = anotherList

  override def map[B](transformer: A => B): LList[B] = Empty()
  override def filter(predicate: A => Boolean): LList[A] = this
  override def flatMap[B](transformer: A => LList[B]): LList[B] = Empty()
}

case class Cons[A](override val head: A, override val tail: LList[A]) extends LList[A] {
  override def isEmpty: Boolean = false
  override def toString = {
    @tailrec
    def concatenateElements(remainder: LList[A], acc: String): String =
      if (remainder.isEmpty) acc
      else concatenateElements(remainder.tail, s"$acc, ${remainder.head}")

    s"[${concatenateElements(this.tail, s"$head")}]"
  }

  override def map[B](transformer: A => B): LList[B] = {
    Cons(transformer(head), tail.map(transformer))
  }

  override def filter(predicate: A => Boolean): LList[A] = {
    if (predicate(head)) Cons(head, tail.filter(predicate))
    else tail.filter(predicate)
  }

  override infix def ++(anotherList: LList[A]): LList[A] =
    Cons(head, tail ++ anotherList)

  override def flatMap[B](transformer: A => LList[B]): LList[B] =
    transformer(head) ++ tail.flatMap(transformer)
}

// replaced with function types:
//trait Predicate[T] {
//  def test(t: T): Boolean
//}
// can be thought of as a function  T => Boolean

// not needed if we use an anonymous class
//class EvenPredicate extends Predicate[Int] {
//  override def test(t: Int): Boolean = t % 2 == 0
//}

//trait Transformer[A,B] {
//  def transform(a: A): B  // same as a function A => B
//}

//class TransformToList extends Transformer[Int, LList[Int]] {
//  override def transform(a: Int): LList[Int] =
//    Cons[Int](a, Cons(a+1, new Empty[Int]))
//}

//class StringToInt extends Transformer[String, Int] {
//  override def transform(str: String): Int = str.toInt
//}

object LList {
  def find[A](list: LList[A], predicate: A => Boolean): A = {
    if (list.isEmpty) throw new NoSuchElementException
    else if (predicate(list.head)) list.head
    else find(list.tail, predicate)
  }
}

object LListTest {
  def main(args: Array[String]): Unit = {
    val empty = Empty[Int]()
    println(empty.isEmpty)
    println(empty)

    val first3Numbers = Cons(1, Cons(2, Cons(3, empty)))
    println(first3Numbers)

    val first3Numbers_v2 = empty.add(1).add(2).add(3)
    println(first3Numbers_v2)
    println(first3Numbers_v2.isEmpty)

    val strings = Cons("greg", Cons("con", Empty()))
    println(strings)

    // predicate can be anonymous class.
//    val evenPredicate = new Function1[Int, Boolean] {
//      override def apply(t: Int) = t % 2 == 0
//    }
//
//    val doubler = new Function1[Int, Int] {
//      override def apply(in: Int): Int = in * 2
//    }
//
//    val transformToList = new Function1[Int,LList[Int]] {
//      override def apply(a: Int): LList[Int] =
//        Cons[Int](a, Cons(a+1, new Empty[Int]))
//    }
    val evenPredicate: Int => Boolean = _ % 2 == 0
    val doubler: Int => Int = _ * 2
    val transformToList: Int => LList[Int] = x => {
      // can't use _ notation here, since we are using the arg more than once.
      Cons[Int](x, Cons(x+1, new Empty[Int]))
    }

    // map
    val numbersDoubled = first3Numbers.map(doubler)
    println(numbersDoubled)
    val numbersNested = first3Numbers.map(transformToList)
    println(numbersNested)

    // filter
    val onlyEvens = first3Numbers.filter(evenPredicate)
    println(onlyEvens)

    val listInBothWays = first3Numbers ++ first3Numbers_v2
    println(listInBothWays)

    val flatList = first3Numbers.flatMap(transformToList)
    println(flatList)

    // find test
    println(LList.find(first3Numbers, evenPredicate))

    // throws exception:
//    println(LList.find(first3Numbers, new Predicate[Int] {
//      override def test(element: Int) = element > 5
//    }))
  }
}



