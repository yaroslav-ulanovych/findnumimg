package com.chyvonalu.findnumimg.core

class CypherTest extends FunSuite {
  test("buildFromStrings") {
    val cypher = Cypher.buildFromStrings(
      List("н", "м", "б", "т", "ч к", "п", "ш г х", "с", "в", "д"),
      RussianConsonants
    )

    import Digit._
    
    def get(x: Digit) = cypher.get(x).map(_.letter.value)

    get(_0) shouldBe List('н')
    get(_1) shouldBe List('м')
    get(_2) shouldBe List('б')
    get(_3) shouldBe List('т')
    get(_4) shouldBe List('ч', 'к')
    get(_5) shouldBe List('п')
    get(_6) shouldBe List('ш', 'г', 'х')
    get(_7) shouldBe List('с')
    get(_8) shouldBe List('в')
    get(_9) shouldBe List('д')
  }

  test("encode 1") {
    val cypher = Cypher.buildFromStrings(
      List("н", "м", "б", "т", "ч к", "п", "ш г х", "с", "в", "д"),
      RussianConsonants
    )

    import Digit._

    val actual = cypher.encode(Digits(_4, _6)).map(_.map(_.letter.value))

    val expected = List(
      List('ч', 'ш'), List('ч', 'г'), List('ч', 'х'),
      List('к', 'ш'), List('к', 'г'), List('к', 'х')
    )

    actual shouldBe expected
  }

  test("encode 2") {
    val cypher = Cypher.buildFromStrings(
      List("н", "м", "б", "т", "ч к", "п", "ш г х", "с", "в", "д"),
      RussianConsonants
    )

    import Digit._

    val actual = cypher.encode(Digits(_0)).map(_.map(_.letter.value))

    val expected = List(List('н'))

    actual shouldBe expected
  }
}
