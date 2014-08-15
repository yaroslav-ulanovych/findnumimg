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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import com.chyvonalu.findnumimg.core.*;

import java.io.InputStream;

import scala.Tuple10;
import scala.collection.Traversable;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

	public CypherView cypherView;
	public SearchView searchView;
	public Dictionary dictionary;

	private Cypher cypher;

	SectionsPagerAdapter sectionsPagerAdapter;

	ViewPager viewPager;

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

		InputStream stream = getResources().openRawResource(R.raw.dict);
		dictionary = Dictionary$.MODULE$.load(Utils.inputStreamToLowerCaseStrings(stream));
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
		searchView = new SearchView(this);

		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(sectionsPagerAdapter);

		viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			private Integer prevPosition;
			@Override
			public void onPageSelected(int position) {
				Log.d("PageChangeListener", String.format("position: %d", position));
				if (prevPosition != null)
					((MyFragment)sectionsPagerAdapter.getItem(prevPosition)).onLeave();
				actionBar.setSelectedNavigationItem(position);
				prevPosition = position;
			}
		});

		for (int i = 0; i < sectionsPagerAdapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab()
				.setText(sectionsPagerAdapter.getPageTitle(i))
				.setTabListener(this)
			);
		}

		viewPager.setCurrentItem(1);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 getMenuInflater().inflate(R.menu.my_activity3, menu);
		 return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 int id = item.getItemId();
		 if (id == R.id.action_settings) {
			 return true;
		 }
		 return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		Log.d("TabListener", String.format("tab: %d", tab.getPosition()));
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Log.d("FragmentPagerAdapter", String.format("getItem: %d", position));
			switch(position) {
				case 0: return cypherView;
				case 1: return searchView;
			}
			throw new UnreachableOperationException();
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
