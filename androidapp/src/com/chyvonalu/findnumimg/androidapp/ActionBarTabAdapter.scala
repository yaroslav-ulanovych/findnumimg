package com.chyvonalu.findnumimg.androidapp

import android.app.{FragmentTransaction, ActionBar}
import android.app.ActionBar.Tab

trait ActionBarTabAdapter extends ActionBar.TabListener {
  override def onTabSelected(p1: Tab, p2: FragmentTransaction) {}

  override def onTabReselected(p1: Tab, p2: FragmentTransaction) {}

  override def onTabUnselected(p1: Tab, p2: FragmentTransaction) {}
}
