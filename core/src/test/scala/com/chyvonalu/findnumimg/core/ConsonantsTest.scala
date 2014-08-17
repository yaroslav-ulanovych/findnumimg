package com.chyvonalu.findnumimg.core

class ConsonantsTest extends FunSuite {
  test("filter") {
    val consonants = new Consonants {
      override def toConsonant(x: Letter): Option[Consonant] = {
        List(Letter('a')).flatten.find(_ == x).map(x => Consonant.apply(x))
      }
    }
    val actual = consonants.filter("at least say hello").map(_.letter.value)
    actual shouldBe List('a', 'a', 'a')
  }
}
