package com.chyvonalu.findnumimg.core

import java.io.InputStream

import scala.io.Source

object Dictionary {
  def load(stream: InputStream): Traversable[String] = {
    Source.fromInputStream(stream).getLines().toVector.map(_.toLowerCase)
  }
}
