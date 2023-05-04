package com.rockthejvm.part3fp

import scala.util.Random

object LinearCollections {

  // Seq "sequence", ordered with indexing
  //val seq = Seq(1,2,3,4)

  def testSeq(): Unit = {
    val aSequence = Seq(4,2,3,1)
    // main API: index an element
    val thirdElement = aSequence(2)  // element 3
    println(thirdElement)
    // map/flatMap/filter/for
    val anIncrementedSequence = aSequence.map(_ + 1)
    val aFlatMappedSequence = aSequence.flatMap((x => Seq(x, x + 1)))
    val aFilteredSequence = aSequence.filter(_ % 2 == 0)

    // other methods
    val reversed = aSequence.reverse
    val concatenation = aSequence ++ Seq(5,6,7)
    val sortedSequence = aSequence.sorted
    val sum = aSequence.foldLeft(0)(_ + _)
    val stringRep = aSequence.mkString("[", ",", "]")

    println(concatenation)
    println(sortedSequence)
    println(sum)
    println(stringRep)
  }

  // List - one implementation of Sequence
  def testLists(): Unit = {
    val aList = List(1,2,3)
    // same API as Seq.
    val first = aList.head
    val rest = aList.tail

    // appending and prepending
    val aBiggerList = 0 +: aList :+ 4
    println(aBiggerList)
    val prepending = 0 :: aList
    println(prepending)
    // utility methods
    val scalax5 = List.fill(5)("Scala")
    println(scalax5)
  }

  // ranges
  def testRanges(): Unit = {
    // an inclusive range:
    val range = 1 to 10  // "to" is a method on the Int type
    // non inclusive
    val nonInclusiveRange = 1 until 10 // 10 not included
    // same Seq API
    (1 to 10).foreach(_ => println("scala"))
  }

  // arrays
  def testArrays(): Unit = {
    val anArray = Array(1,2,3,4,5,6)  // just like a java int[] on the JVM
    // most Seq APIs, but arrays are not Seqs
    val aSequence: Seq[Int] = anArray.toIndexedSeq
    // arrays are MUTABLE!  watch out.
    anArray.update(2, 30)  // no new array is allocated
  }

  // vectors = fast Seqs for a large amount of data
  def testVectors(): Unit = {
    val aVector: Vector[Int] = Vector(1,2,3,4,5,6)
    // the same Seq API
  }

  def smallBenchmark(): Unit = {
    val maxRuns = 1000
    val maxCapacity = 1000000

    def getWriteTime(collection: Seq[Int]): Double = {
      val random = new Random()
      val times = for {
        i <- 1 to maxRuns
      } yield {
        val index = random.nextInt(maxCapacity)
        val element = random.nextInt()
        val currentTime = System.nanoTime()
        val updatedCollection = collection.updated(index, element)
        System.nanoTime() - currentTime
      }

      times.sum * 1.0 / maxRuns
    }

    val numbersList = (1 to maxCapacity).toList
    val numbersVector = (1 to maxCapacity).toVector

    println(getWriteTime(numbersList))
    println(getWriteTime(numbersVector))
  }

  // sets - unique elements.  no ordering.
  def testSets(): Unit = {
    val aSet = Set(1,2,3,4,5,4)  // set only contains one "4"
    // equals + hashCode = has set
    // main API: test if an element is in the set
    val contains3 = aSet.contains(3) // true
    val contains3_v2 = aSet(3)  // same.  returns true
    // adding/removing
    val aBiggerSet = aSet + 6 // returns a new set with a 6.
    val aSmallerSet = aSet - 4
    // concatenation, also known as union
    val anotherSet = Set(4,5,6,7,8)
    val muchBiggerSet = aSet ++ anotherSet
    // alternatively:
    val muchBiggerSet_v2 = aSet.union(anotherSet)
    // shorthand for union:
    val muchBiggerSet_v3 = aSet | anotherSet
    println(muchBiggerSet_v3)
    // difference
    val aDiffSet = aSet.diff(anotherSet)
    val aDiffSet_v2 = aSet -- anotherSet // same
    println(aDiffSet_v2)
    // intersection
    val anIntersection = aSet.intersect((anotherSet)) // [4,5]
    val anIntersection_V2 = aSet & anotherSet
    println(anIntersection_V2)
  }

  def main(args: Array[String]): Unit = {
    testSeq()
    testLists()
    testRanges()
    testArrays()
    testSets()
//    smallBenchmark()
  }
}
