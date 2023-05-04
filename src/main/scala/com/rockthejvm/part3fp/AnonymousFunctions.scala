package com.rockthejvm.part3fp

object AnonymousFunctions {

  // most functions are instances of FunctionN
  val doubler: Int => Int = new Function[Int, Int] {
    override def apply(x: Int): Int = x * 2
  }
  // or we can use lambdas = anonymous function instances:
  val doubler_v2: Int => Int = (x: Int) => x * 2 // identical
  val adder: (Int, Int) => Int = (x: Int, y: Int) => x + y
  // zero-arg functions:
  val justDoSomething: () => Int = () => 45
  val anInvocation = justDoSomething()

  // alt syntax with curly braces
  val stringToInt = { (str: String) =>
    // add more code here
    str.toInt
  }

  // type inference is powerful with lambdas.  compiler can infer argument types.
  val doubler3: Int => Int = x => x * 2
  val adder2: (Int, Int) => Int = (x, y) => x + y

  // scala prefers an even MORE shorthand syntax for lambdas.
  val doubler4: Int => Int = _ * 2  // x => x * 2
  val adder3: (Int, Int) => Int = _ + _ // (x, y) => x + y
  // in adder3, each underscore is a different argument

  val superAdder = (x: Int) => (y: Int) => x + y

  def main(args: Array[String]): Unit = {
    println(justDoSomething)
    println(justDoSomething())
    println(superAdder(9)(7))  // 16
  }
}
