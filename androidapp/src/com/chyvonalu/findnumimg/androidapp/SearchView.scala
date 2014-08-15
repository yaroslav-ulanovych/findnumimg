package com.chyvonalu.findnumimg.androidapp

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

  override def onResume {
    super.onResume
    Log.d(TAG, "onEnter")
  }

  def search {
    searchResultsView.removeAllViews
    val cypher: Cypher = getMainActivity.cypher
    val dictionary: Dictionary = getMainActivity.dictionary
    val rangesOpt: Option[List[NumRange]] = RangeParser.parse(rangeInput.getText.toString)
    if (rangesOpt.isDefined) {
      val rangeList: List[NumRange] = rangesOpt.get
      val rangeIterator: Iterator[NumRange] = rangeList.iterator
      while (rangeIterator.hasNext) {
        val range: NumRange = rangeIterator.next
        val from: Int = Math.min(range.from, range.to)
        val to: Int = Math.max(range.from, range.to)
        val width: Int = range.width
        var i: Int = from
        while (i <= to) {
          var textView: TextView = new TextView(searchResultsView.getContext)
          val string: SpannableString = new SpannableString(String.format("%0" + width + "d", new Integer(i)))
          string.setSpan(new StyleSpan(Typeface.BOLD), 0, string.length, 0)
          string.setSpan(new UnderlineSpan, 0, string.length, 0)
          textView.setText(string)
          textView.setPadding(0, 16, 0, 0)
          searchResultsView.addView(textView)
          val before: Long = System.currentTimeMillis
          val list: List[String] = Utils.find(i, width, dictionary, cypher)
          val after: Long = System.currentTimeMillis
          Log.d("search", s"${after - before}ms")
          val iterator: Iterator[String] = list.iterator
          while (iterator.hasNext) {
            val s: String = iterator.next
            textView = new TextView(searchResultsView.getContext)
            textView.setText(s)
            searchResultsView.addView(textView)
          }
          i += 1
        }
      }
    }
  }

  private var searchResultsView: LinearLayout = null
  private var rangeInput: EditText = null
}
