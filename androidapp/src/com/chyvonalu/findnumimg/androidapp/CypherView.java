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
	private EditText cypher0;
	private EditText cypher1;
	private EditText cypher2;
	private EditText cypher3;
	private EditText cypher4;
	private EditText cypher5;
	private EditText cypher6;
	private EditText cypher7;
	private EditText cypher8;
	private EditText cypher9;

	private Cypher cypher;

	public CypherView(Cypher cypher) {
		this.cypher = cypher;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View view = inflater.inflate(R.layout.cypher_view, container, false);
		cypher0 = (EditText) view.findViewById(R.id.cypher0);
		cypher1 = (EditText) view.findViewById(R.id.cypher1);
		cypher2 = (EditText) view.findViewById(R.id.cypher2);
		cypher3 = (EditText) view.findViewById(R.id.cypher3);
		cypher4 = (EditText) view.findViewById(R.id.cypher4);
		cypher5 = (EditText) view.findViewById(R.id.cypher5);
		cypher6 = (EditText) view.findViewById(R.id.cypher6);
		cypher7 = (EditText) view.findViewById(R.id.cypher7);
		cypher8 = (EditText) view.findViewById(R.id.cypher8);
		cypher9 = (EditText) view.findViewById(R.id.cypher9);

		updateView();
		return view;
	}

	public void updateView() {
		cypher0.setText(cypher.get(0).mkString(" "));
		cypher1.setText(cypher.get(1).mkString(" "));
		cypher2.setText(cypher.get(2).mkString(" "));
		cypher3.setText(cypher.get(3).mkString(" "));
		cypher4.setText(cypher.get(4).mkString(" "));
		cypher5.setText(cypher.get(5).mkString(" "));
		cypher6.setText(cypher.get(6).mkString(" "));
		cypher7.setText(cypher.get(7).mkString(" "));
		cypher8.setText(cypher.get(8).mkString(" "));
		cypher9.setText(cypher.get(9).mkString(" "));
	}

	public void updateModel() {
		cypher = Cypher$.MODULE$.buildFromStrings(Tuple10.apply(
			cypher0.getText().toString(),
			cypher1.getText().toString(),
			cypher2.getText().toString(),
			cypher3.getText().toString(),
			cypher4.getText().toString(),
			cypher5.getText().toString(),
			cypher6.getText().toString(),
			cypher7.getText().toString(),
			cypher8.getText().toString(),
			cypher9.getText().toString()
		));
	}

	@Override
	public void onLeave() {
		updateModel();
	}

	public Cypher getCypher() {
		return cypher;
	}
}
