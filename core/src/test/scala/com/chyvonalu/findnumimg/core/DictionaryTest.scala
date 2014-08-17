package com.chyvonalu.findnumimg.core

class DictionaryTest extends FunSuite {
  test{"load"} {
    val dict = Dictionary.load(List("авокадо", "коса", "киса"))

    def get(s: String) = dict.get(RussianConsonants.filter(s))

    get("вкд") shouldBe List("авокадо")
    get("кс") shouldBe List("коса", "киса")
    get("") shouldBe List()
  }

}
