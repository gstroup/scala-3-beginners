//package com.rockthejvm.practice
//
//import scala.annotation.tailrec
//
//// singly linked list
//// 1 -> 2, 2 -> 3, etc.  each one is a node.
//abstract class LList {
//  def head: Int
//  def tail: LList
//  def isEmpty: Boolean
//  def add(element: Int): LList = new Cons(element, this) // add at the front
//}
//
//class Empty extends LList {
//  override def head: Int = throw new NullPointerException()
//  override def tail: LList = throw new NullPointerException()
//  override def isEmpty: Boolean = true
////  override def add(element: Int): LList = Cons(element, this)
//  override def toString = "[]"
//}
//
//// "Cons" means "Constructor"
//class Cons(override val head: Int, override val tail: LList) extends LList {
////  override def head: Int = value
////  override def tail: LList = next
//  override def isEmpty: Boolean = false
//
//  // same impl as Empty, so we move to the abstract superclass
//  //  override def add(element: Int): LList = new Cons(element, this)
//  override def toString: String = {
//    @tailrec
//    def concatElements(remainder: LList, acc: String): String =
//      if (remainder.isEmpty) acc
//      else concatElements(remainder.tail, s"$acc, ${remainder.head}")
//
//    s"[${concatElements(this.tail, s"$head")}]"
//  }
//}
//
//object LListTest {
//
//  def main(args: Array[String]): Unit = {
//    val empty = new Empty();
//    println(empty.isEmpty)
//    println(empty)
//
//    val first3Numbers = new Cons(1, new Cons(2, new Cons(3, empty)))
//    println(first3Numbers)
//    val first3Numbers_v2 = empty.add(1).add(2).add(3)
//    println(first3Numbers_v2)
//    println(first3Numbers_v2.isEmpty)
//  }
//}