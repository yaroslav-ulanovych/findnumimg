package com.chyvonalu.findnumimg.core

class UtilsTest extends FunSuite {
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
