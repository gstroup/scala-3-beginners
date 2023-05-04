package com.rockthejvm.part2oop

object Enums {

  // enums were much improved in scala 3

  enum Permissions { // this is "sealed".  can't be extended
    case READ, WRITE, EXECUTE, NONE

    def openDocument(): Unit =
      if (this == READ) println("opening doc")
      else println("no reading")
  }

  val somePermission: Permissions = Permissions.READ

  // constructor args:
  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) // 100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0) // 000
  }

  // companion object:
  object PermissionsWithBits {
    // factory method
    def fromBits(bits: Int): PermissionsWithBits = PermissionsWithBits.NONE
  }

  // standard API
  val somePermissionsOrdinal = somePermission.ordinal
  val allPermissions = Permissions.values // array of all values
  val readPermission: Permissions = Permissions.valueOf("READ")

  def main(args: Array[String]): Unit = {
    somePermission.openDocument()
    println(somePermissionsOrdinal)
    allPermissions.map(p => {
      println(p)
      p
    })
  }
}
