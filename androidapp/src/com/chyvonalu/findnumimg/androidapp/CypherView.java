package com.chyvonalu.findnumimg.androidapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.chyvonalu.findnumimg.core.Cypher;
import com.chyvonalu.findnumimg.core.Cypher$;
import scala.Tuple10;


public class CypherView extends MyFragment {
	public CypherView() {}

	public static final String TAG = "CypherView";

	public EditText[] getInputs() {
		View view = getView();
		return new EditText[]{
			(EditText) view.findViewById(R.id.cypher0),
			(EditText) view.findViewById(R.id.cypher1),
			(EditText) view.findViewById(R.id.cypher2),
			(EditText) view.findViewById(R.id.cypher3),
			(EditText) view.findViewById(R.id.cypher4),
			(EditText) view.findViewById(R.id.cypher5),
			(EditText) view.findViewById(R.id.cypher6),
			(EditText) view.findViewById(R.id.cypher7),
			(EditText) view.findViewById(R.id.cypher8),
			(EditText) view.findViewById(R.id.cypher9)
		};
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.cypher_view, container, false);
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onEnter");
	}


	public void updateView() {
		Cypher cypher = getMainActivity().cypher;
		EditText[] inputs = getInputs();
		for (int i = 0; i < 10; i++) {
			inputs[i].setText(cypher.get(i).mkString(" "));
		}
	}

	public void updateModel() {
		EditText[] inputs = getInputs();
		String s0 = inputs[0].getText().toString();
		String s1 = inputs[1].getText().toString();
		getMainActivity().cypher = Cypher$.MODULE$.buildFromStrings(Tuple10.apply(
				s0,
				s1,
				inputs[2].getText().toString(),
				inputs[3].getText().toString(),
				inputs[4].getText().toString(),
				inputs[5].getText().toString(),
				inputs[6].getText().toString(),
				inputs[7].getText().toString(),
				inputs[8].getText().toString(),
				inputs[9].getText().toString()
		));
	}


}
