package com.chyvonalu.findnumimg.androidapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.chyvonalu.findnumimg.core.Cypher;

public class SearchView extends MyFragment {
	private LinearLayout searchResultsView;
	private Button searchButton;
	private final MainActivity activity;

	public SearchView(MainActivity activity) {
		this.activity = activity;
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_view, container, false);
		searchResultsView = (LinearLayout) view.findViewById(R.id.search_results_view);
		searchButton = (Button) view.findViewById(R.id.search_button);
		searchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				search();
			}
		});
		return view;
    }

	public void search() {
		Cypher cypher = activity.cypherView.getCypher();

		Button button = new Button(searchResultsView.getContext());
		button.setText("11");
		searchResultsView.addView(button);
	}
}
