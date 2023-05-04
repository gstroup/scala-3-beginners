package com.rockthejvm.part3fp

object MapFlatMapFilterFor {

  // now we'll use scala's standard linked list, instead of our LList implementation.
  val aList = List(1,2,3)
  // empty list is called Nil
  val firstElement = aList.head
  val restOfElements = aList.tail

  // map
  val anIncrementedList = aList.map(_ + 1)

  // filter
  val oddNumbers = aList.filter(_ % 2 != 0)

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  val flatMappedList = aList.flatMap(toPair)

  val numbers = List(1,2,3,4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white", "red")

  // for-comprehension - this is not an iteration
  val combinations = for {
    n <- numbers if n % 2 == 0 // this is known as a generator.  converted to a "flatMap" call under the covers.
        // the "if guard" is converted into "withFilter" calls
    c <- chars
    color <- colors
  } yield s"$n$c - $color"  // last expression is converted into a "map" call

  // for-comprehensions with Unit and side effects
  // if foreach

  import com.rockthejvm.practice.*
  val lSimpleNumbers: LList[Int] = Cons(1, Cons(2, Cons(3, Empty())))
  val incrementedNumbers = lSimpleNumbers.map(_+1)
  val filteredNumbers = lSimpleNumbers.filter(_ % 2 == 0)
  val toPairLList: Int => LList[Int] = (x: Int) => Cons(x, Cons(x + 1, Empty()))
  val flatMappedNumbers = lSimpleNumbers.flatMap(toPairLList)

  // map + flatmap = for-comprehensions
  val combinationNumbers = for {
    n <- lSimpleNumbers if n % 2 == 0  // have to add "withFilter" in LList to support this.
    c <- Cons('a', Cons('b', Cons('c', Empty())))
  } yield s"$n-$c"

  def main(args: Array[String]): Unit = {
    //numbers.foreach(println)
    for {
      num <- numbers
    } println(num)
    println(oddNumbers)
    println(flatMappedList)
    println(combinations)
    println("------------")
    println(incrementedNumbers)
    println(filteredNumbers)
    println(flatMappedNumbers)
    println(combinationNumbers)
  }
}
