package com.rockthejvm.part3fp

object TuplesMaps {

  // tuples = finite ordered "lists".  same as python.
  val aTuple = (2, "greg")  // type is Tuple2[Int, String] == (Int, String)
  val firstField = aTuple._1
  val aCopiedTuple = aTuple.copy(_1 = 54)  // first field is 54, other fields remain the same.

  // tuples of 2 elements
  val aTuple2 = 2 -> "greg" // IDENTICAL to (2, "greg")
  // can use this syntax, which is the same as maps

  ////////
  // maps: keys -> values
  val aMap = Map()

  val phoneBook: Map[String, Int] = Map(
    "jim" -> 2342344,  // these are tuples in a Map
    "greg" -> 34908348,
    "rob" -> 9595604
  ).withDefaultValue(-1)
  // map core APIs
  val phoneBookHasGreg = phoneBook.contains("greg")
  val marysPhoneNumber = phoneBook("mary")  // throws an exception since mary is not in the map

  // add a pair
  val newPair = "Mary" -> 34983498
  val newPhoneBook = phoneBook + newPair

  // remove a pair
  val phoneBookWithoutJim = phoneBook - "jim"

  // list -> map
  val linearPhonebook = List(
    "jim" -> 2342344,
    "greg" -> 34908348,
    "rob" -> 9595604
  )
  val phoneBook2 = linearPhonebook.toMap  // can call this on a list of 2-element tuples

  // map -> linear collection
  val linearPhonebook2 = phoneBook.toList // or toSeq, toVector, toArray, toSet

  // map, flatMap, filter
  val aProcessedPhoneBook = phoneBook.map(pair => (pair._1.toUpperCase(), pair._2))
  // line above could be dangerous, if you have both "Jim" and "jim" in the original.  you'll lose one.

  // filtering keys
  val noJs = phoneBook.view.filterKeys(!_.startsWith("J")).toMap
  // it's more common to filter on the values:
  // mapping values
  val prefixNumbers = phoneBook.view.mapValues(number => s"0255-$number").toMap
  val prefixNumbers2 = phoneBook.view.mapValues("0255-" + _).toMap

  // other collections can create maps
  val names = List("Bob", "James", "Angela", "Mary", "Greg", "John", "Andy")
  val nameGroupings = names.groupBy(name => name.charAt(0)) // Map[Char, List[String}}

  def main(args: Array[String]): Unit = {
    println(phoneBook)
    println(phoneBookHasGreg)
    println(marysPhoneNumber)
    println(prefixNumbers)
    println(prefixNumbers2)
    println(nameGroupings)
  }
}
