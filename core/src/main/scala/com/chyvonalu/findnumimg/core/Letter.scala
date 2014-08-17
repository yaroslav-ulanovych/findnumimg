package com.chyvonalu.findnumimg.core

case class Letter private(value: Char, dummy: Unit)

object Letter {
  def apply(value: Char): Option[Letter] = {
    if (value.isLetter) Some(Letter(value.toLower, ())) else None
  }
}