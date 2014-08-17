package com.chyvonalu.findnumimg.core

class LetterTest extends FunSuite {
  test("apply") {
    Letter('ф') shouldBe Letter('Ф')
  }
}
