package com.chyvonalu.findnumimg

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

  def loadDict(): Traversable[String] = {
    val url = getClass.getClassLoader.getResource("dict")
    Source.fromURL(url).getLines().toVector.map(_.toLowerCase)
  }

  def find(num: Int, words: Traversable[String], cipher: Cipher): List[String] = {
    val digits = pad(toDigits(num), 2)
    val result = ListBuffer[String]()
    val letters = product(digits.map(cipher.get))
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
