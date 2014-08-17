package com.chyvonalu.findnumimg.core

class NumRangeTest extends FunSuite {
  test("traversable") {
    import Digit._
    NumRange(0, 2, 3).toList shouldBe List(
      Digits(_0, _0, _0),
      Digits(_0, _0, _1),
      Digits(_0, _0, _2)
    )
  }
}
