package com.chyvonalu.findnumimg.androidapp

import android.support.v4.app.Fragment

class MyFragment extends Fragment {
  def getMainActivity: MainActivity = {
    return getActivity.asInstanceOf[MainActivity]
  }

  def onEnter {
  }

  def onLeave {
  }
}