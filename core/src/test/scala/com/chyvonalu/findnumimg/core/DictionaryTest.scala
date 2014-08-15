package com.chyvonalu.findnumimg.core

import org.scalatest.{Matchers, FunSuite}

class DictionaryTest extends FunSuite with Matchers {
  test{"load"} {
    val dict = Dictionary.load(List("авокадо", "коса", "киса"))
    dict.get("вкд") shouldBe List("авокадо")
    dict.get("кс") shouldBe List("коса", "киса")
    dict.get("") shouldBe List()
  }

}
