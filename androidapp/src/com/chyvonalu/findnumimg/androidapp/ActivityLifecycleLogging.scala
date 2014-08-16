package com.chyvonalu.findnumimg.androidapp

import android.app.{Fragment, Activity}
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.AttributeSet
import android.view.View

trait ActivityLifecycleLogging extends Activity {
  self: HasLogger =>

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    debug("onCreate")
  }

  override def onPostCreate(savedInstanceState: Bundle) {
    super.onPostCreate(savedInstanceState)
    debug("onPostCreate")
  }

  override def onDestroy() {
    super.onDestroy()
    debug("onDestroy")
  }

  override def onCreateView(name: String, context: Context, attrs: AttributeSet): View = {
    val view = super.onCreateView(name, context, attrs)
//    debug("onCreateView: " + name)
    view
  }

  override def onCreateView(parent: View, name: String, context: Context, attrs: AttributeSet): View = {
    val view = super.onCreateView(parent, name, context, attrs)
//    debug("onCreateViewWithParent: " + name)
    view
  }

  override def onStart() {
    super.onStart()
    debug("onStart")
  }

  override def onRestart() {
    super.onRestart()
    debug("onRestart")
  }

  override def onStop() {
    super.onStop()
    debug("onStop")
  }

  override def onResume() {
    super.onResume()
    debug("onResume")
  }

  override def onPostResume() {
    super.onPostResume()
    debug("onPostResume")
  }

  override def onPause() {
    super.onPause()
    debug("onPause")
  }

  override def onAttachedToWindow() {
    super.onAttachedToWindow()
    debug("onAttachedToWindow")
  }

  override def onDetachedFromWindow() {
    super.onDetachedFromWindow()
    debug("onDetachedFromWindow")
  }

  override def onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    debug("onSaveInstanceState")
  }

  override def onRestoreInstanceState(savedInstanceState: Bundle) {
    super.onRestoreInstanceState(savedInstanceState)
    debug("onRestoreInstanceState")
  }

  override def onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig)
    debug("onConfigurationChanged")
  }

  override def onAttachFragment(fragment: Fragment) {
    super.onAttachFragment(fragment)
    debug("onAttachFragment")
  }

  override def onBackPressed() {
    super.onBackPressed()
    debug("onBackPressed")
  }

  override def onWindowFocusChanged(hasFocus: Boolean) {
    super.onWindowFocusChanged(hasFocus)
    debug("onWindowFocusChanged: " + hasFocus)
  }

  override def onUserLeaveHint() {
    super.onUserLeaveHint()
    debug("onUserLeaveHint")
  }
}