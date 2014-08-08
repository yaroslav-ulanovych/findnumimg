package com.chyvonalu.findnumimg

object EntryPoint extends App with Ciphers {
  val dict = Utils.loadDict()
  val cipher = LessProductiveCipher
//  for(i <- List(1, 5, 9, 15, 21, 25, 32, 33, 35, 37, 38, 39, 51, 52, 53, 59, 61, 64, 66, 69, 71, 72, 82, 84, 85, 88, 97)) {
  for(i <- 0 until 10) {
    val words = Utils.find(i, dict, cipher)
    println("######: " + i)
    words foreach println
  }
}
