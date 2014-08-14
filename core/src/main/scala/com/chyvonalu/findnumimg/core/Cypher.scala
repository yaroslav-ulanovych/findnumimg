package com.chyvonalu.findnumimg.core

trait Cypher {
  def get(x: Int): List[String]
}

object Cypher {
  def splitString(s: String): List[String] = s.split("""\s+""").toList

  def buildFromStrings(xs: (String, String, String, String, String, String, String, String, String, String)): Cypher = {
    new Cypher {
      override def get(x: Int): List[String] = splitString(xs.productElement(x).asInstanceOf[String])
    }
  }
}
