package com.chyvonalu.findnumimg.androidapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.chyvonalu.findnumimg.core.Cypher

class CypherView() extends MyFragment() {
  val TAG = "CypherView"
  debug("<init>()")

  def getInputs: Array[EditText] = {
    val view: View = getView
    return Array[EditText](view.findViewById(R.id.cypher0).asInstanceOf[EditText], view.findViewById(R.id.cypher1).asInstanceOf[EditText], view.findViewById(R.id.cypher2).asInstanceOf[EditText], view.findViewById(R.id.cypher3).asInstanceOf[EditText], view.findViewById(R.id.cypher4).asInstanceOf[EditText], view.findViewById(R.id.cypher5).asInstanceOf[EditText], view.findViewById(R.id.cypher6).asInstanceOf[EditText], view.findViewById(R.id.cypher7).asInstanceOf[EditText], view.findViewById(R.id.cypher8).asInstanceOf[EditText], view.findViewById(R.id.cypher9).asInstanceOf[EditText])
  }

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {
    return inflater.inflate(R.layout.cypher_view, container, false)
  }

  def updateView {
    val cypher = getMainActivity.cypher
    val inputs: Array[EditText] = getInputs
    inputs.zipWithIndex.foreach({
      case (input, i) => input.setText(cypher.get(i).mkString(" "))
    })
  }

  def updateModel {
    val inputs: Array[EditText] = getInputs
    val s0: String = inputs(0).getText.toString
    val s1: String = inputs(1).getText.toString
    getMainActivity.cypher = Cypher.buildFromStrings(Tuple10.apply(s0, s1, inputs(2).getText.toString, inputs(3).getText.toString, inputs(4).getText.toString, inputs(5).getText.toString, inputs(6).getText.toString, inputs(7).getText.toString, inputs(8).getText.toString, inputs(9).getText.toString))
  }
}

