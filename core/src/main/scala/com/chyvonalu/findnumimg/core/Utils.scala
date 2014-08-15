package com.chyvonalu.findnumimg.core

import java.io.InputStream

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

  def isConsonant(x: Char) = x match {
    case 'б'|'в'|'г'|'д'|'ж'|'з'|'к'|'л'|'м'|'н'|'п'|'р'|'с'|'т'|'ф'|'х'|'ц'|'ч'|'ш'|'щ' => true
    case _ => false
  }

  def toConsonants(s: String) = s.filter(isConsonant)



  def find(num: Int, width: Int, dictionary: Dictionary, cypher: Cypher): List[String] = {
    val digits = pad(toDigits(num), width)
    val keys = cypher.encode(digits)
    keys.map(key => (key, dictionary.get(key))).map({
      case (key, values) => {
        val x = key.split("").drop(1).toList
        values.map(value => highlight(value, x))
      }
    }).flatten.sortBy(_.length)
  }

  def inputStreamToLowerCaseStrings(stream: InputStream): Traversable[String] = {
    Source.fromInputStream(stream).getLines().toVector.map(_.toLowerCase)
  }

}
