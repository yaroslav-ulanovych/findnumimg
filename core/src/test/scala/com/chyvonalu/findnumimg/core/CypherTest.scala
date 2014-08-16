package com.chyvonalu.findnumimg.core

import org.scalatest.{Matchers, FunSuite}

class CypherTest extends FunSuite with Matchers {
  test("splitString") {
    import Cypher.splitString
    splitString("a b") shouldBe List("a", "b")
    splitString("a") shouldBe List("a")
    splitString("н") shouldBe List("н")
    splitString("   ") shouldBe List()
  }

  test("buildFromStrings") {
    val cypher = Cypher.buildFromStrings(List("н", "м", "б", "т", "ч к", "п", "ш г х", "с", "в", "д"))
    cypher.get(0) shouldBe List("н")
    cypher.get(1) shouldBe List("м")
    cypher.get(2) shouldBe List("б")
    cypher.get(3) shouldBe List("т")
    cypher.get(4) shouldBe List("ч", "к")
    cypher.get(5) shouldBe List("п")
    cypher.get(6) shouldBe List("ш", "г", "х")
    cypher.get(7) shouldBe List("с")
    cypher.get(8) shouldBe List("в")
    cypher.get(9) shouldBe List("д")
  }

  test("encode") {
    val cypher = Cypher.buildFromStrings(List("н", "м", "б", "т", "ч к", "п", "ш г х", "с", "в", "д"))
    cypher.encode(List(4, 6)) shouldBe List("чш", "чг", "чх", "кш", "кг", "кх")
    cypher.encode(List(0)) shouldBe List("н")
  }
}
