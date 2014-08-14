package com.chyvonalu.findnumimg.core

import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.util.matching.Regex

object Utils {
  def toDigits(x: Int): List[Int] = {
    if (x < 10) List(x) else toDigits(x / 10) :+ (x % 10)
  }

  def pad(xs: List[Int], w: Int): List[Int] = {
    val l = xs.length
    if (l < w)
      List(0, 0).slice(0, w - l) ::: xs
    else xs
  }

  def product[T](xs: List[List[T]]): List[List[T]] = {
    xs match {
      case List(xs) => for(x <- xs) yield List(x)
      case List(xs, ys) => for(x <- xs; y <- ys) yield List(x, y)
      case List(xs, ys, zs) => for(x <- xs; y <- ys; z <- zs) yield List(x, y, z)
      case _ => throw new NotImplementedError("product of more than 3 lists is not implemented")
    }
  }

  def highlight(str: String, xs: List[String]): String = {
    var s = str
    val result = new StringBuilder
    xs foreach { x =>
      val i = s indexOf x
      result.append(s.substring(0, i) + x.toUpperCase)
      val rest = s.substring(i + x.length)
      s = rest
    }
    result append s
    result.toString
  }

  def parseRange(s: String): (Integer, Integer, Integer) = {
    val pattern1 = """^\s*(\d+)\s*$""".r
    val pattern2 = """^\s*(\d+)\s+(\d+)\s*$""".r
    s match {
      case pattern1(x) => (x.toInt, x.toInt, x.length)
      case pattern2(x, y) => (x.toInt, y.toInt, math.max(x.length, y.length))
      case _ => null
    }
  }

  def find(num: Int, width: Int, words: Traversable[String], cypher: Cypher): List[String] = {
     val digits = pad(toDigits(num), width)
     val result = ListBuffer[String]()
     val letters = product(digits.map(cypher.get))
     val regexs = letters.map(letters => new Regex("^[аоуэыийяёюеь]*" + letters.mkString("[аоуэыийяёюеь]*")))
     words foreach { word =>
       for((regex, letters) <- (regexs zip letters)) {
         if (regex.findFirstIn(word).isDefined) {
           result += highlight(word, letters)
         }
       }
     }
     result.sortBy(_.length).toList
  }
}
