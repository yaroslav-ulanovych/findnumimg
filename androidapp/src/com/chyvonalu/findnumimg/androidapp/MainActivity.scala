package com.chyvonalu.findnumimg.androidapp

import android.app.ActionBar
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.app.FragmentTransaction
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.Fragment
import android.util.AttributeSet
import android.view.View

class MainActivity extends FragmentActivity with ActionBarTabAdapter with HasLogger with ActivityLifecycleLogging {
  val TAG = "MainActivity"
  debug("<init>()")

  var cypherView: CypherView = _
  var searchView: SearchView = _
  private var viewPager: ViewPager = _

  override def onCreateView(name: String, context: Context, attrs: AttributeSet): View = {
    super.onCreateView(name, context, attrs)
  }

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main_activity)

    val actionBar = getActionBar
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS)

    val sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager)

    viewPager = findViewById(R.id.pager).asInstanceOf[ViewPager]
    viewPager.setAdapter(sectionsPagerAdapter)
    viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener {
      override def onPageSelected(position: Int) {
        debug(s"onPageSelected: $position")
        actionBar.setSelectedNavigationItem(position)
      }
    })

    for(i <- 0 until sectionsPagerAdapter.getCount) {
      actionBar.addTab(actionBar.newTab.setText(sectionsPagerAdapter.getPageTitle(i)).setTabListener(this))
    }
  }

  override def onTabSelected(tab: ActionBar.Tab, fragmentTransaction: FragmentTransaction) {
    debug(s"onTabSelected: ${tab.getPosition}")
    viewPager.setCurrentItem(tab.getPosition)
  }

  class SectionsPagerAdapter(fm: FragmentManager) extends FragmentPagerAdapter(fm) {
    def getItem(position: Int): Fragment = {
      debug(s"FragmentPagerAdapter.getItem: $position")
      position match {
        case 0 => new CypherView
        case 1 => new SearchView
        case _ => throw new UnreachableOperationException
      }
    }

    def getCount = 2

    override def getPageTitle(position: Int): CharSequence = position match {
      case 0 => "Cypher"
      case 1 => "Search"
      case _ => throw new UnreachableOperationException
    }
  }

}