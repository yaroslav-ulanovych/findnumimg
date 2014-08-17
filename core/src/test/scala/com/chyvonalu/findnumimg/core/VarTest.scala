package com.chyvonalu.findnumimg.core

import java.io.{FileInputStream, File}

class VarTest extends FunSuite {
  test("Character.isLowerCase") {
    Character.isLowerCase('ф') shouldBe true
    Character.isLowerCase('Ф') shouldBe false
    Character.isLowerCase('q') shouldBe true
    Character.isLowerCase('Q') shouldBe false
  }

  test("dict load time") {
    val file = new File("./androidapp/res/raw/dict")
    val (_, time) = Utils.measure {
      val strings = Utils.inputStreamToLowerCaseStrings(new FileInputStream(file))
      Dictionary.load(strings)
    }
//    println(s"time: $time")
  }
}
