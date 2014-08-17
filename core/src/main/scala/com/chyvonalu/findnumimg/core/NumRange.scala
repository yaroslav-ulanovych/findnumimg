package com.chyvonalu.findnumimg.core

case class NumRange private(from: Int, to: Int, width: Int, dummy: Unit) extends Traversable[Digits] {

  override def foreach[U](f: (Digits) => U): Unit = {
    for(x <- from to to) {
      f(Digits(x) pad width)
    }
  }

  def normalize = NumRange(math.min(from, to), math.max(from, to), width)
}

object NumRange {
  def apply(x: Int, y: Int, width: Int): NumRange = {
    val from = x min y
    val to = x max y
    NumRange(from, to, width, ())
  }
}