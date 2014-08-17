package com.chyvonalu.findnumimg.androidapp

import java.io.InputStream

import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.chyvonalu.findnumimg.core._
import scala.collection.Iterator
import scala.collection.immutable.List


class SearchView extends MyFragment {
  val TAG = "SearchView"
  debug("<init>()")

  private var dictionary: Dictionary = _

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {
    val view: View = inflater.inflate(R.layout.search_view, container, false)
    searchResultsView = view.findViewById(R.id.search_results_view).asInstanceOf[LinearLayout]
    rangeInput = view.findViewById(R.id.range_input).asInstanceOf[EditText]
    rangeInput.setOnClickListener(new View.OnClickListener {
      def onClick(v: View) {
        rangeInput.setText("")
      }
    })
    rangeInput.setOnEditorActionListener(new TextView.OnEditorActionListener {
      def onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean = {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
          val imm: InputMethodManager = getActivity.getSystemService(Context.INPUT_METHOD_SERVICE).asInstanceOf[InputMethodManager]
          imm.hideSoftInputFromWindow(v.getWindowToken, 0)
          search
          return true
        }
        return false
      }
    })
    return view
  }



  override def onResume() {
    super.onResume()
    def measure[T](f: => T): (T, Long) = {
      val before = System.currentTimeMillis
      val z = f
      val after = System.currentTimeMillis
      (z, after - before)
    }
    val stream = getResources.openRawResource(R.raw.dict)

    val (strings, time1) = measure {
      Utils.inputStreamToLowerCaseStrings(stream)
    }

    val (_, time2) = measure {
      dictionary = Dictionary.load(strings)
    }

    debug(s"loaded dict, inputStreamToLowerCaseStrings:  ${time1}ms, load: ${time2}ms")
  }

  def search {
    searchResultsView.removeAllViews
    val cypher = getMainActivity.cypherView.getCypher

    val inflater = getActivity.getLayoutInflater

    RangeParser.parse(rangeInput.getText.toString) match {
      case Some(ranges) => {
        ranges.map(_.normalize) foreach { range =>
          range foreach { digits =>
            val textView = inflater.inflate(R.layout.search_results_header, null).asInstanceOf[TextView]
            textView.setText(digits.toString)
            searchResultsView.addView(textView)
            val before = System.currentTimeMillis
            val list = Utils.find(digits, dictionary, cypher)
            val after = System.currentTimeMillis
            debug(s"search in ${after - before}ms")
            list foreach { s =>
              val textView = inflater.inflate(R.layout.search_results_item, null).asInstanceOf[TextView]
              textView.setText(s)
              searchResultsView.addView(textView)
            }
          }
        }
      }
      case None =>
    }

  }

  private var searchResultsView: LinearLayout = null
  private var rangeInput: EditText = null
}

