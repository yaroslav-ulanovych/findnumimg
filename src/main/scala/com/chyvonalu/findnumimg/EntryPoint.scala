package com.chyvonalu.findnumimg

object EntryPoint extends App {
  val words = Utils.find(40, Utils.loadDict())
  words foreach println
}
