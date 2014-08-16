package com.chyvonalu.findnumimg.core

case class NumRange(from: Int, to: Int, width: Int) {
  def normalize = NumRange(math.min(from, to), math.max(from, to), width)
}
