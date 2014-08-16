package com.chyvonalu.findnumimg.androidapp

import android.app.ActionBar
import android.content.Context
import android.content.SharedPreferences
import android.support.annotation.NonNull
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.app.FragmentTransaction
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.Fragment
import android.util.AttributeSet
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import java.io.InputStream

import com.chyvonalu.findnumimg.core.{Utils, Dictionary, Cypher}

class MainActivity extends FragmentActivity with ActionBar.TabListener with HasLogger with ActivityLifecycleLogging {
  val TAG = "MainActivity"
  debug("<init>()")

  def loadSettings {
    val settings: SharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)
    cypher = Cypher.buildFromStrings(Tuple10.apply(settings.getString("cypher0", "н"), settings.getString("cypher1", "м"), settings.getString("cypher2", "б"), settings.getString("cypher3", "т"), settings.getString("cypher4", "ч к"), settings.getString("cypher5", "п"), settings.getString("cypher6", "ш г х"), settings.getString("cypher7", "с"), settings.getString("cypher8", "в"), settings.getString("cypher9", "д")))
    val stream: InputStream = getResources.openRawResource(R.raw.dict)
    dictionary = Dictionary.load(Utils.inputStreamToLowerCaseStrings(stream))
  }

  def getSearchView: Nothing = {
    return getSupportFragmentManager.findFragmentById(R.layout.search_view).asInstanceOf[Nothing]
  }

  def getCypherView: Nothing = {
    return getSupportFragmentManager.findFragmentById(R.layout.cypher_view).asInstanceOf[Nothing]
  }

  override def onCreateView(name: String, @NonNull context: Context, @NonNull attrs: AttributeSet): View = {
    return super.onCreateView(name, context, attrs)
  }

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main_activity)
    loadSettings
    val actionBar: ActionBar = getActionBar
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS)
    sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager)
    viewPager = findViewById(R.id.pager).asInstanceOf[ViewPager]
    viewPager.setAdapter(sectionsPagerAdapter)
    viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener {
      override def onPageSelected(position: Int) {
        debug(s"onPageSelected: $position")
        actionBar.setSelectedNavigationItem(position)
        prevPosition = position
      }

      private var prevPosition: Int = -1
    })
    var i: Int = 0
    while (i < sectionsPagerAdapter.getCount) {
      actionBar.addTab(actionBar.newTab.setText(sectionsPagerAdapter.getPageTitle(i)).setTabListener(this))
      i += 1;
    }
  }

  override def onResume {
    super.onResume
    viewPager.setCurrentItem(1)
  }

  override def onCreateOptionsMenu(menu: Menu): Boolean = {
    getMenuInflater.inflate(R.menu.my_activity3, menu)
    return true
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    val id: Int = item.getItemId
    if (id == R.id.action_settings) {
      return true
    }
    return super.onOptionsItemSelected(item)
  }

  def onTabSelected(tab: ActionBar.Tab, fragmentTransaction: FragmentTransaction) {
    debug(s"onTabSelected: ${tab.getPosition}")
    viewPager.setCurrentItem(tab.getPosition)
  }

  def onTabUnselected(tab: ActionBar.Tab, fragmentTransaction: FragmentTransaction) {
  }

  def onTabReselected(tab: ActionBar.Tab, fragmentTransaction: FragmentTransaction) {
  }

  var dictionary: Dictionary = null
  var cypher: Cypher = null
  private[androidapp] var sectionsPagerAdapter: MainActivity#SectionsPagerAdapter = null
  private[androidapp] var viewPager: ViewPager = null

  class SectionsPagerAdapter(fm: FragmentManager) extends FragmentPagerAdapter(fm) {
    def getItem(position: Int): Fragment = {
      debug(s"FragmentPagerAdapter.getItem: $position")
      position match {
        case 0 => new CypherView
        case 1 => new SearchView
        case _ => throw new UnreachableOperationException
      }

    }

    def getCount: Int = {
      return 2
    }

    override def getPageTitle(position: Int): CharSequence = {
      position match {
        case 0 =>
          return "Cypher"
        case 1 =>
          return "Search"
      }
      throw new UnreachableOperationException
    }
  }

}