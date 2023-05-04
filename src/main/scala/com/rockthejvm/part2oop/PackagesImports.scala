package com.rockthejvm.part2oop

// in scala, you can now create fields & methods at the top level:
val meaningOfLife = 42
def computeMyLife: String = "Scala"
// this is not really recommended though!

object PackagesImports {  // top-level

  // fully qualified name
//  val aList = com.rockthejvm.practice.LList[Int] = ???

  import com.rockthejvm.practice.LList
  val list2: LList[Int] = ???

  // aliasing:
  import java.util.{List as JList}
  val javaList: JList[Int] = ???

  // import package with *
  import com.rockthejvm.practice.*
  //val aPredicate: Predicate[Int] = ???

  // multiple symbols on one line
  import PhysicsConstants.{SPEED_OF_LIGHT, EARTH_GRAVITY}

  // exclude one symbol
  object PhysicsPlay {
    import PhysicsConstants.{PLANCK as _, *}
    //val planck = PLANCK // will not work!
  }

  // default imports:
  // scala.*, scala.Predef.*
  // java.lang.*
  // many more, but these are most important

  // exports
  class PhysicsCalculator {
    import PhysicsConstants.*

    def computePhotonEnergy(waveLength: Double): Double =
      PLANCK / waveLength
  }

  object ScienceApp {
    val physicsCalculator = new PhysicsCalculator

    export physicsCalculator.computePhotonEnergy

    def computeEquivalentMass(waveLength: Double): Double =
      computePhotonEnergy(waveLength) / (SPEED_OF_LIGHT * SPEED_OF_LIGHT)
  }

  def main(args: Array[String]): Unit = {

  }
}

object PhysicsConstants {
  val SPEED_OF_LIGHT = 299792458
  val PLANCK = 6.62e-34
  val EARTH_GRAVITY = 9.8
}
