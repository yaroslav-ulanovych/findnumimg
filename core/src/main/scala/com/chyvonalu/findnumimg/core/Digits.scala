package com.chyvonalu.findnumimg.core

import scala.collection.mutable.ArrayBuffer

case class Digits private(xs: List[Digit], dummy: Unit) extends Seq[Digit] {
  override def length = xs.length
  override def iterator = xs.iterator

  override def apply(idx: Int) = xs.apply(idx)

  def pow(x: Int, y: Int): Int = {
    math.round(math.pow(x, y)).toInt
  }

  def pad(w: Int): Digits = {
    val l = xs.length
    val zeros = List(Digit._0, Digit._0)
    val xs2 = if (w > l) zeros.slice(0, w - l) ++ xs else xs
    Digits(xs2: _*)
  }

  def toInt: Int = {
    xs.reverse.zipWithIndex.map({case (digit, i) => {
      digit.value * pow(10, i)
    }}).reduce(_ + _)
  }

  override def toString = xs.map(_.value.toString).mkString
}

object Digits {
  def apply(xs: Digit*): Digits = Digits(xs.toList, ())

  def apply(x: Int): Digits = {
    val z = ArrayBuffer[Int]()
    var y = x
    while (y > 9) {
      z += (y % 10)
      y = y / 10
    }
    z += y
    Digits(z.map(Digit.fromInt).reverse.toList: _*)
  }
}
