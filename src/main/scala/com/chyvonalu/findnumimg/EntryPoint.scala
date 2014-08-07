package com.chyvonalu.findnumimg

object EntryPoint extends App {
  val dict = Utils.loadDict()
  val x = 10
  for(i <- x until (x + 10)) {
    var words = Utils.find(i, dict)
    println("######: " + i)
    words foreach println
  }
}
