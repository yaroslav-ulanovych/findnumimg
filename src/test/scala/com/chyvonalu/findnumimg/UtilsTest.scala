package com.chyvonalu.findnumimg

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSuite

class UtilsTest extends FunSuite with ShouldMatchers {
  test("toDigits") {
    Utils.toDigits(123) shouldBe List(1, 2, 3)
    Utils.toDigits(1) shouldBe List(1)
  }

  test("pad") {
    Utils.pad(List(1), 3) shouldBe List(0, 0, 1)
    Utils.pad(List(1), 2) shouldBe List(0, 1)
    Utils.pad(List(1), 1) shouldBe List(1)
  }

  test("product") {
    Utils.product(List(List(1, 2))) shouldBe List(List(1), List(2))

    Utils.product(List(List(1, 2), List(3, 4))) shouldBe List(
      List(1, 3), List(1, 4), List(2, 3), List(2, 4)
    )

    Utils.product(List(List(1, 2), List(3), List(4, 5))) shouldBe List(
      List(1, 3, 4), List(1, 3, 5), List(2, 3, 4), List(2, 3, 5)
    )
  }

  test("highlight") {
    Utils.highlight("deepbluesky", List("d", "b", "s")) shouldBe "DeepBlueSky"
    Utils.highlight("blabla", List("bl", "bl")) shouldBe "BLaBLa"
  }
}