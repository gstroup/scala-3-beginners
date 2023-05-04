package com.rockthejvm.part1basics

object Expressions {

  val meaningOfLife = 40 + 2

  // mathematical operations: +, -, *, /, bitwise |, &, <<, >>, >>>
  val mathExpression = 2 + 3 * 4

  // comparison expressions: <, <=, >, >=, ==, !=  All return a Boolean
  // boolean expressions: !, ||, &&

  // instructions are in imperative languages.  they're executed
  // expressions are evaluated.  we think in terms of expressions

  val aCondition = true
  val anIfExpression = if (aCondition) 45 else 99

  // code blocks
  val aCodeBlock = {
    val localValue = 78
    localValue + 54 // the last expression is returned.
  }

  // everything in scala is an expression

  def main(args: Array[String]): Unit = {
    println(if (aCondition) 45 else 99)
    println(aCodeBlock)
  }
}
