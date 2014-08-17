package com.chyvonalu.findnumimg.core

import org.scalatest.{Matchers, FunSuite}

class VarTest extends FunSuite with Matchers {
  test("Character.isLowerCase") {
    Character.isLowerCase('ф') shouldBe true
    Character.isLowerCase('Ф') shouldBe false
    Character.isLowerCase('q') shouldBe true
    Character.isLowerCase('Q') shouldBe false
  }
}
