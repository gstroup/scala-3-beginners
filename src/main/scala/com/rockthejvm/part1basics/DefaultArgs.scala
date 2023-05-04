package com.rockthejvm.part1basics

import scala.annotation.tailrec

object DefaultArgs {

  // default of zero for second arg.
  @tailrec
  def sumUntilTailrec(x: Int, acc: Int = 0): Int =
    if (x <= 0) acc
    else sumUntilTailrec(x - 1, acc + x)

  def savePicture(dirPath: String, name: String, format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit =
    println("Saving picture in format " + format + " in path " + dirPath)

  def main(args: Array[String]): Unit = {
    println(sumUntilTailrec(100))
    savePicture("/users/gstroup/photos", "myphoto")  // just use default args
    savePicture("/users/gstroup/photos", "myphoto", "png")
    // use named args to help the compiler.  then order doesn't matter
    savePicture("/users/gstroup/photos", "myphoto", width = 800, height = 600)
    savePicture("/users/gstroup/photos", "myphoto", height = 600,  width = 800)
  }
}
