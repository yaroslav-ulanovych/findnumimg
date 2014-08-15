package com.chyvonalu.findnumimg.core

import scala.util.parsing.combinator.RegexParsers

object RangeParser extends RegexParsers {
  def number = """\d+""".r ^^ identity
  def minus = """-""".r ^^ identity

  def range = (number <~ minus) ~ number ^^ {case x ~ y => NumRange(x.toInt, y.toInt, math.max(x.length, y.length))}

  def single = number ^^ {x => NumRange(x.toInt, x.toInt, x.length)}

  def ranges = (range | single)+

  def parse(s: String): Option[List[NumRange]] = parseAll(ranges, s) match {
    case Success(x, _) => Some(x)
    case e: NoSuccess => println(e.msg); None
  }
}
