package com.chyvonalu.findnumimg.core

object RussianConsonants extends Consonants {
  private val chars = List(
    'б', 'в', 'г', 'д', 'ж', 'з', 'к', 'л', 'м', 'н', 'п', 'р', 'с', 'т', 'ф', 'х', 'ц', 'ч', 'ш', 'щ'
  )

  private val letters = chars.map(x => Letter(x).get)

  override def toConsonant(x: Letter): Option[Consonant] = {
    letters.find(_ == x).map(x => Consonant(x))
  }
}
