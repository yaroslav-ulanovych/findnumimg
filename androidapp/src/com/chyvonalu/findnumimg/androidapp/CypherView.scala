package com.chyvonalu.findnumimg.androidapp

import android.app.Activity
import android.content.{SharedPreferences, Context}
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.chyvonalu.findnumimg.core.{Digit, RussianConsonants, Cypher}

class CypherView() extends MyFragment() {
  val TAG = "CypherView"
  debug("<init>()")

  private var inputs: Seq[EditText] = _
  
  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {
    super.onCreateView(inflater, container, savedInstanceState)
    val view = inflater.inflate(R.layout.cypher_view, container, false)
    val ids = List(R.id.cypher0, R.id.cypher1, R.id.cypher2, R.id.cypher3, R.id.cypher4, R.id.cypher5, R.id.cypher6, R.id.cypher7, R.id.cypher8, R.id.cypher9)
    inputs = ids.map(id => view.findViewById(id).asInstanceOf[EditText])
    debug("inputs: " + inputs)
    view
  }


  override def onAttach(activity: Activity): Unit = {
    super.onAttach(activity)
    activity.asInstanceOf[MainActivity].cypherView = this
  }

  override def onResume(): Unit = {
    super.onResume()

    val preferences = getActivity.getSharedPreferences("settings", Context.MODE_PRIVATE)

    def get(k: String, v: String) = {
      val vv = preferences.getString(k, v)
      debug(s"load $k: $vv, default: $v")
      vv
    }

    val cypher = Cypher.buildFromStrings(List(
      get("cypher0", "н"),
      get("cypher1", "м"),
      get("cypher2", "б"),
      get("cypher3", "т"),
      get("cypher4", "ч к"),
      get("cypher5", "п"),
      get("cypher6", "ш г х"),
      get("cypher7", "с"),
      get("cypher8", "в"),
      get("cypher9", "д")
    ), RussianConsonants)

    inputs.zipWithIndex.foreach({
      case (input, i) => {
        val text = cypher.get(Digit.fromInt(i)).map(_.letter.value).mkString(" ")
        input.setText(text)
      }
    })
  }


  override def onPause(): Unit = {
    super.onPause()
    val preferences = getActivity.getSharedPreferences("settings", Context.MODE_PRIVATE).edit()
    inputs.zipWithIndex.foreach({case (input, i) => {
      val key = "cypher" + i
      val value = input.getText.toString
      debug(s"save $key: $value")
      preferences.putString(key, value)
    }})
    val z = preferences.commit
    debug("SharedPreferences.commit: " + z);
  }

  def getCypher: Cypher = {
    val xs = inputs.map(_.getText.toString)
    Cypher.buildFromStrings(xs, RussianConsonants)
  }
}

