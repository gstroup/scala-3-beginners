package com.rockthejvm.part2oop

object OOBasics {

  class Person(val name: String, age: Int) { // constructor args
    // if we add val, the compiler will add a field
    // fields:
    val allCaps = name.toUpperCase()

    // methods
    def greet(name: String): String =
      s"${this.name} says: Hi $name"

    // same name, different signature = overloading
    def greet(): String = s"Hi, my name is $name"

    // aux constructor = method name is "this"
    // this is rare in scala, since we can just define default params on the constructor.
    def this(name: String) =
      this(name, 0)

    def this() =
      this("Jane Doe")
  }

  val aPerson: Person = new Person("John", 26)
  val john = aPerson.name;

  class Writer(firstName: String, lastName: String, val birthYear: Int) {
    def fullName = s"$firstName $lastName"
  }

  class Novel(val title: String, yearOfRelease: Int, author: Writer) {
    def authorAge: Int = yearOfRelease - author.birthYear
    def isWrittenBy(author: Writer): Boolean = this.author == author
    def copy(newYear: Int): Novel = new Novel(title, newYear, author)
  }

  class Counter(i: Int = 0) {
    def increment: Counter = new Counter(i+1)
    def decrement: Counter = new Counter(i-1)
    def incrementN(n: Int): Counter = new Counter(i+n)
    def decrementN(n: Int): Counter = new Counter(i-n)
    def print(): Unit = println(i)
  }

  val counter = new Counter(5);

  def main(args: Array[String]): Unit = {
    println(aPerson.greet("Greg"))
    counter.print()
    counter.increment.print()
//    counter.print()
    counter.incrementN(5).print()
//    counter.print()
    counter.decrementN(3).print()
//    counter.print()
  }
}
