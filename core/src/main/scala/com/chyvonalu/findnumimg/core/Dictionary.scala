package com.chyvonalu.findnumimg.core

import java.io.InputStream

import scala.collection.mutable
import scala.collection.mutable.{ListBuffer, ArrayBuffer}
import scala.io.Source

trait Dictionary {
  def get(s: String): List[String]
}

object Dictionary {
  def load(xs: Traversable[String]): Dictionary = {
    val map = mutable.HashMap[String, ArrayBuffer[String]]()
    xs foreach {x =>
      val key = Utils.toConsonants(x)
      val xs = map.get(key) match {
        case Some(xs) => xs
        case None => {
          val xs = ArrayBuffer[String]()
          map.put(key, xs)
          xs
        }
      }
      xs += x
    }
    new Dictionary{
      override def get(s: String): List[String] = map.get(s).map(_.toList).getOrElse(List())
    }
  }
}
