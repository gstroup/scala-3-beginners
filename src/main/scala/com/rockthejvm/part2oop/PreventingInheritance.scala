package com.rockthejvm.part2oop

object PreventingInheritance {

  class Person(name: String) {
    // 1 final method
    final def enjoyLife(): Int = 42
  }

  class Adult(name: String) extends Person(name) {
    // override def enjoyLife(): Int = 999
    // cannot override final method
  }

  // 2 final class
  final class Animal
//  class Cat extends Animal // illegal

  // 3 sealing a type hierarchy = inheritance only permitted inside this file.
  sealed class Guitar(nStrings: Int)
  class ElectricGuitar(nStrings: Int) extends Guitar(nStrings)
  class AcousticGuitar extends Guitar(6)
  
  // no modifier = "not encouraged" for inheritance
  // use "open" modifier for extension
  open class ExtensibleGuitar {}

  def main(args: Array[String]): Unit = {

  }
}
