package com.chyvonalu.findnumimg.core

class DigitsTest extends FunSuite {
  test("apply") {
    import Digit._
    Digits(_0, _0, _7).xs.map(_.value) shouldBe List(0, 0, 7)
  }

  test("fromInt") {
    import Digit._
    Digits(123) shouldBe Digits(_1, _2, _3)
    Digits(1) shouldBe Digits(_1)
    Digits(0) shouldBe Digits(_0)
  }

  test("toInt") {
    import Digit._
    Digits(_1, _2, _3).toInt shouldBe 123
  }
  
  test("pad") {
    import Digit._
    Digits(_1).pad(3) shouldBe Digits(_0, _0, _1)
    Digits(_1).pad(2) shouldBe Digits(_0, _1)
    Digits(_1).pad(1) shouldBe Digits(_1)
  }
}
