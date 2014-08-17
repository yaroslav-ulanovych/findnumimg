package com.chyvonalu.findnumimg.core

trait Consonants {
  def toConsonant(x: Letter): Option[Consonant]

  def filter(s: String): Seq[Consonant] = {
    val letters = s.filter(_.isLetter).map(x => Letter(x).get)
    letters.map(toConsonant).flatten
  }
}
