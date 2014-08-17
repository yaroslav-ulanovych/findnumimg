package com.chyvonalu.findnumimg.core

import java.io.InputStream

import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.util.matching.Regex

object Utils {
  def product[T](xs: Seq[Seq[T]]): Seq[Seq[T]] = {
    xs match {
      case Seq(xs) => for(x <- xs) yield List(x)
      case Seq(xs, ys) => for(x <- xs; y <- ys) yield List(x, y)
      case Seq(xs, ys, zs) => for(x <- xs; y <- ys; z <- zs) yield List(x, y, z)
      case _ => throw new NotImplementedError("product of more than 3 lists is not implemented")
    }
  }

  def highlight(str: String, xs: Seq[String]): String = {
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

  def measure[T](f: => T): (T, Long) = {
    val before = System.currentTimeMillis
    val z = f
    val after = System.currentTimeMillis
    (z, after - before)
  }

  def find(digits: Digits, dictionary: Dictionary, cypher: Cypher): Seq[String] = {
    val keys = cypher.encode(digits)
    keys.map(key => (key, dictionary.get(key))).map({
      case (key, values) => {
        values.map(value => highlight(value, key.map(x => Character.toString(x.letter.value))))
      }
    }).flatten.sortBy(_.length)
  }

  def inputStreamToLowerCaseStrings(stream: InputStream): Traversable[String] = {
    Source.fromInputStream(stream).getLines().toVector.map(_.toLowerCase)
  }

}
