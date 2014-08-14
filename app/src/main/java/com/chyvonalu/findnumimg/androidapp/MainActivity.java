package com.chyvonalu.findnumimg.androidapp;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import com.chyvonalu.findnumimg.core.Cypher;
import com.chyvonalu.findnumimg.core.Cypher$;
import com.chyvonalu.findnumimg.core.Utils;
import scala.Tuple10;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

	public CypherView cypherView;
	public SearchView searchView;

	private Cypher cypher;

    /**
      * The {@link android.support.v4.view.PagerAdapter} that will provide
      * fragments for each of the sections. We use a
      * {@link FragmentPagerAdapter} derivative, which will keep every
      * loaded fragment in memory. If this becomes too memory intensive, it
      * may be best to switch to a
      * {@link android.support.v4.app.FragmentStatePagerAdapter}.
      */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
      * The {@link ViewPager} that will host the section contents.
      */
    ViewPager mViewPager;

	public void loadSettings() {
		SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);
		cypher = Cypher$.MODULE$.buildFromStrings(Tuple10.apply(
			settings.getString("cypher0", "н"),
			settings.getString("cypher1", "м"),
			settings.getString("cypher2", "б"),
			settings.getString("cypher3", "т"),
			settings.getString("cypher4", "ч к"),
			settings.getString("cypher5", "п"),
			settings.getString("cypher6", "ш г х"),
			settings.getString("cypher7", "с"),
			settings.getString("cypher8", "в"),
			settings.getString("cypher9", "д")
		));
	}



	@Override
	public View onCreateView(String name, @NonNull Context context, @NonNull AttributeSet attrs) {
		return super.onCreateView(name, context, attrs);
	}

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);

		loadSettings();
		cypherView = new CypherView(cypher);
		searchView = new SearchView();



         // Set up the action bar.
         final ActionBar actionBar = getActionBar();
         actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

         // Create the adapter that will return a fragment for each of the three
         // primary sections of the activity.
         mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

         // Set up the ViewPager with the sections adapter.
         mViewPager = (ViewPager) findViewById(R.id.pager);
         mViewPager.setAdapter(mSectionsPagerAdapter);

         // When swiping between different sections, select the corresponding
         // tab. We can also use ActionBar.Tab#select() to do this if we have
         // a reference to the Tab.
         mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
             @Override
             public void onPageSelected(int position) {
                 actionBar.setSelectedNavigationItem(position);
             }
         });

         // For each of the sections in the app, add a tab to the action bar.
         for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
             // Create a tab with text corresponding to the page title defined by
             // the adapter. Also specify this Activity object, which implements
             // the TabListener interface, as the callback (listener) for when
             // this tab is selected.
             actionBar.addTab(
                     actionBar.newTab()
                             .setText(mSectionsPagerAdapter.getPageTitle(i))
                             .setTabListener(this));
         }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.my_activity3, menu);
         return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // Handle action bar item clicks here. The action bar will
         // automatically handle clicks on the Home/Up button, so long
         // as you specify a parent activity in AndroidManifest.xml.
         int id = item.getItemId();
         if (id == R.id.action_settings) {
             return true;
         }
         return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
         mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		private Fragment prevFragment;

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

			@Override
		public Fragment getItem(int position) {
			Fragment fragment;
			switch(position) {
				case 0: fragment = cypherView; break;
				case 1: fragment = searchView; break;
				default: throw new UnreachableOperationException();
			}
			prevFragment = fragment;
			if (prevFragment != null) {
				((MyFragment)prevFragment).onLeave();
			}
			return fragment;
			}

         @Override
         public int getCount() {
             return 2;
         }

         @Override
         public CharSequence getPageTitle(int position) {
             switch (position) {
                 case 0: return "Cypher";
                 case 1: return "Search";
             }
             throw new UnreachableOperationException();
         }
    }
}
