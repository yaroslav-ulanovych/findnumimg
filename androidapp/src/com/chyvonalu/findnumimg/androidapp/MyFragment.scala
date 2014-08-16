package com.chyvonalu.findnumimg.androidapp

import android.support.v4.app.Fragment

trait MyFragment extends Fragment with HasLogger with FragmentLifeCycleLogging {
  val TAG: String

  def getMainActivity: MainActivity = {
    return getActivity.asInstanceOf[MainActivity]
  }


  def onEnter {
  }

  def onLeave {
  }
}