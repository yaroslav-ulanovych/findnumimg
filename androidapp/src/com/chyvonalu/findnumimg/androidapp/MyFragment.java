package com.chyvonalu.findnumimg.androidapp;

import android.support.v4.app.Fragment;

public class MyFragment extends Fragment {

	public MainActivity getMainActivity() {
		return (MainActivity) getActivity();
	}
	public void onEnter() {}

	public void onLeave() {}
}
