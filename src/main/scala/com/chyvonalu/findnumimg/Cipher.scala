package com.chyvonalu.findnumimg

trait Cipher {
  def get(x: Int): List[String]
  def +(that: Cipher): Cipher = new Cipher {
    def get(x: Int): List[String] = Cipher.this.get(x) ++ that.get(x)
  }
}

case class MapCipher(map: Map[Int, List[String]]) extends Cipher {
  def get(x: Int): List[String] = map.getOrElse(x, List())
}

trait Ciphers {
  val PerfectCipher = MapCipher(Map(
    0 -> List("н"),
    3 -> List("т"),
    4 -> List("ч"),
    5 -> List("п"),
    6 -> List("ш"),
    7 -> List("с"),
    8 -> List("в"),
    9 -> List("д")
  ))

  val ForeignCipher = MapCipher(Map(
    0 -> List("з"),
    1 -> List("м"),
    2 -> List("б", "ц"),
    4 -> List("к"),
    5 -> List("ф"),
    6 -> List("г", "х")
  ))

  val LessProductiveCipher = MapCipher(Map(
    0 -> List("н"),
    1 -> List("м"),
    2 -> List("л"),
    3 -> List("т"),
    4 -> List("к"),
    5 -> List("п"),
    6 -> List("р"),
    7 -> List("с"),
    8 -> List("в"),
    9 -> List("9")
  ))

  val ProductiveCipher = MapCipher(Map(
    0 -> List("н", "ф"),
    1 -> List("т", "щ"),
    2 -> List("р", "ц"),
    3 -> List("с", "ш"),
    4 -> List("л", "ж"),
    5 -> List("в", "х"),
    6 -> List("к", "ч"),
    7 -> List("п", "г"),
    8 -> List("м", "б"),
    9 -> List("д", "з")
  ))
}

