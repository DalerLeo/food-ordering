package com.dalerleo.foodordering;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  private TabAdapter adapter;
  private TabLayout tabLayout;
  private ViewPager viewPager;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    viewPager = (ViewPager) findViewById(R.id.viewPager);
    tabLayout = (TabLayout) findViewById(R.id.tabLayout);

    adapter = new TabAdapter(getSupportFragmentManager());
    adapter.addFragment(new TabFavorites(), "Favorite");
    adapter.addFragment(new TabMenu(), "Menu");
    adapter.addFragment(new TabProfile(), "Profile");

    viewPager.setAdapter(adapter);
    tabLayout.setupWithViewPager(viewPager);
  }
}
