package com.rockthejvm.practice

import scala.annotation.tailrec

object TuplesMapsExercises {

  /**
   * Social network = Map[String, Set[String]]  names to friends' names
   * Friend relationships are MUTUAL
   *
   * - add a person to the network
   * - remove a person from the network
   * - add a friend relationship (network, a, b)  both from a -> b and b -> a
   * - unfriend
   *
   * - get number of friends of a person
   * - who has the most friends
   * - how many people have NO friends
   * - check if there is a social connection between 2 people.  (direct or not)
   *
   * Daniel - Mary - Tom
   */

  def addPerson(network: Map[String, Set[String]], newPerson: String): Map[String, Set[String]] = {
    val newTuple = newPerson -> Set[String]()
    network + newTuple
//    network + (newPerson -> Set())
  }

  def removePerson(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    // remove the person.  also remove person from everyone else's friend set.
    (network - person).map(pair => (pair._1, pair._2 - person))
  }

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    if (!network.contains(a)) throw new IllegalArgumentException(s"$a is not in the network")
    else if (!network.contains(b)) throw new IllegalArgumentException(s"$b is not in the network")
    else {
      val aFriends = network(a) + b
      val bFriends = network(b) + a
      network + (a -> aFriends) + (b-> bFriends)
    }
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    if (!network.contains(a) || !network.contains(b)) network
    else {
      val aFriends = network(a) - b
      val bFriends = network(b) - a
      network + (a -> aFriends) + (b -> bFriends)
    }
  }

  def getFriends(network: Map[String, Set[String]], person: String): Int = {
    if (!network.contains(person)) -1
    else network(person).size
  }

  def mostFriends(network: Map[String, Set[String]]): String = {
    if (network.isEmpty) throw new RuntimeException("network is empty")
    else {
      // ("", -1) is the starting value.
      val best = network.foldLeft(("", -1)) { (currentBest, newAssociation) =>
        val currentMostPopularPerson = currentBest._1
        val mostFriendsSoFar = currentBest._2

        val newPerson = newAssociation._1
        val newPersonFriends = newAssociation._2.size

        if (mostFriendsSoFar < newPersonFriends) (newPerson, newPersonFriends)
        else currentBest
      }
      best._1
    }
  }

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
//    network.filter(pair => pair._2.isEmpty).size
        network.count(pair => pair._2.isEmpty)   // same as line above
  }

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def search(discoveredPeople: Set[String], consideredPeople: Set[String]): Boolean = {
      // this is a breadth-first search
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        val personsFriends = network(person)
        if (personsFriends.contains(b)) true
        else search(discoveredPeople - person ++ personsFriends -- consideredPeople, consideredPeople + person)
      }
    }
    if (!network.contains(a) || !network.contains(b)) false
    else search(network(a), Set(a))
  }

  def main(args: Array[String]): Unit = {
    val empty: Map[String, Set[String]] = Map()
    val network = addPerson(addPerson(empty, "Mary"), "Bob")
    println(network)
    println(friend(network, "Bob", "Mary"))
    println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))

    val people = addPerson(addPerson(addPerson(empty, "Bob"), "Mary"), "Jim")
    val simpleNet = friend(friend(people, "Bob", "Mary"), "Jim", "Mary")
    println(simpleNet)
    println(getFriends(simpleNet, "Mary"))
    println(getFriends(simpleNet, "Bob"))
    println(getFriends(simpleNet, "asldfkj"))

    println(mostFriends(simpleNet))

    println(nPeopleWithNoFriends(addPerson(simpleNet, "Greg")))

    println(socialConnection(simpleNet, "Bob", "Jim"))
  }
}
