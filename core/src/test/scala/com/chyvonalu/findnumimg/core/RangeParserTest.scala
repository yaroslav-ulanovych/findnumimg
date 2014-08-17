package com.chyvonalu.findnumimg.core

class RangeParserTest extends FunSuite {
  test("parse") {
    RangeParser.parse("  1  ") shouldBe Some(List(NumRange(1, 1, 1)))
    RangeParser.parse("  001  ") shouldBe Some(List(NumRange(1, 1, 3)))
    RangeParser.parse("  1  -  2  ") shouldBe Some(List(NumRange(1, 2, 1)))
    RangeParser.parse("1  02 -  005") shouldBe Some(List(NumRange(1, 1, 1), NumRange(2, 5, 3)))
    RangeParser.parse("1  2 - 3 04") shouldBe Some(List(NumRange(1, 1, 1), NumRange(2, 3, 1), NumRange(4, 4, 2)))
  }
}
