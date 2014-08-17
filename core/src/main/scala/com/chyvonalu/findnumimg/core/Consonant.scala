package com.chyvonalu.findnumimg.core

case class Consonant(letter: Letter, dummy: Unit)

object Consonant {
  def apply(letter: Letter): Consonant = Consonant(letter, ())
}
