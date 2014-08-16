package com.chyvonalu.findnumimg.androidapp

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.AttributeSet
import android.view.{View, ViewGroup, LayoutInflater}

trait FragmentLifeCycleLogging extends Fragment {
  self: HasLogger =>

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    debug("onCreate")
  }

  override def onDestroy() {
    super.onDestroy()
    debug("onDestroy")
  }

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {
    val view = super.onCreateView(inflater, container, savedInstanceState)
    debug("onCreateView")
    view
  }

  override def onDestroyView() {
    super.onDestroyView()
    debug("onDestroyView")
  }

  override def onStart() {
    super.onStart()
    debug("onStart")
  }

  override def onStop() {
    super.onStop()
    debug("onStop")
  }

  override def onResume() {
    super.onResume()
    debug("onResume")
  }

  override def onPause() {
    super.onPause()
    debug("onPause")
  }

  override def onHiddenChanged(hidden: Boolean) {
    super.onHiddenChanged(hidden)
    debug("onHiddenChanged: " + hidden)
  }

  override def onInflate(activity: Activity, attrs: AttributeSet, savedInstanceState: Bundle) {
    super.onInflate(activity, attrs, savedInstanceState)
    debug("onInflate")
  }

  override def onAttach(activity: Activity) {
    super.onAttach(activity)
    debug("onAttach")
  }

  override def onDetach() {
    super.onDetach()
    debug("onDetach")
  }

  override def onViewCreated(view: View, savedInstanceState: Bundle) {
    super.onViewCreated(view, savedInstanceState)
    debug("onViewCreated")
  }

  override def onActivityCreated(savedInstanceState: Bundle) {
    super.onActivityCreated(savedInstanceState)
    debug("onActivityCreated")
  }

  override def onViewStateRestored(savedInstanceState: Bundle) {
    super.onViewStateRestored(savedInstanceState)
    debug("onViewStateRestored")
  }

  override def onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    debug("onSaveInstanceState")
  }

  override def onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig)
    debug("onConfigurationChanged")
  }
}