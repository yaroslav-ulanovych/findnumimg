package com.chyvonalu.findnumimg.core

trait Cypher {
  def get(x: Digit): Seq[Consonant]

  def encode(digits: Digits): Seq[Seq[Consonant]] = {
    Utils.product(digits.map(get))
  }
}

object Cypher {
  def buildFromStrings(xs: Seq[String], consonants: Consonants): Cypher = {
    new Cypher {
      override def get(x: Digit): Seq[Consonant] = consonants.filter(xs.lift(x.value).getOrElse(""))
    }
  }
}
