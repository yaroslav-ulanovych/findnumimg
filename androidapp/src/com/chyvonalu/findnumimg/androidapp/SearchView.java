package com.chyvonalu.findnumimg.androidapp;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chyvonalu.findnumimg.core.*;

import scala.Function1;
import scala.Option;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.Traversable;
import scala.collection.immutable.List;

public class SearchView extends MyFragment {
	private LinearLayout searchResultsView;
	private EditText rangeInput;
	private final MainActivity activity;

	public SearchView(MainActivity activity) {
		this.activity = activity;
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_view, container, false);
		searchResultsView = (LinearLayout) view.findViewById(R.id.search_results_view);
		rangeInput = (EditText) view.findViewById(R.id.range_input);
		rangeInput.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				rangeInput.setText("");
			}
		});

		rangeInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					// hide keyboard
					InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

					search();
					return true;
				}
				return false;
			}
		});

		return view;
    }

	public void search() {
		searchResultsView.removeAllViews();
		Cypher cypher = activity.cypherView.getCypher();
		Dictionary dictionary = activity.dictionary;
		Option<List<NumRange>> rangesOpt = RangeParser$.MODULE$.parse(rangeInput.getText().toString());
		if (rangesOpt.isDefined()) {
			List<NumRange> rangeList = rangesOpt.get();
			Iterator<NumRange> rangeIterator = rangeList.iterator();
			while(rangeIterator.hasNext()) {
				NumRange range = rangeIterator.next();

				int from = Math.min(range.from(), range.to());
				int to = Math.max(range.from(), range.to());
				int width = range.width();
				for (int i = from; i <= to; i++) {

					TextView textView = new TextView(searchResultsView.getContext());
					SpannableString string = new SpannableString(String.format("%0" + width + "d", i));
					string.setSpan(new StyleSpan(Typeface.BOLD), 0, string.length(), 0);
					string.setSpan(new UnderlineSpan(), 0, string.length(), 0);
					textView.setText(string);
					textView.setPadding(0, 16, 0, 0);
					searchResultsView.addView(textView);

					long before = System.currentTimeMillis();
					List<String> list = Utils.find(i, width, dictionary, cypher);
					long after = System.currentTimeMillis();
					Log.d("search", String.format("%dms", after - before));

					Iterator<String> iterator = list.iterator();
					while (iterator.hasNext()) {
						String s = iterator.next();

						textView = new TextView(searchResultsView.getContext());
						textView.setText(s);
						searchResultsView.addView(textView);
					}
				}
			}
		}
	}
}
