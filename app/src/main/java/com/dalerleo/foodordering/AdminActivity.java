package com.dalerleo.foodordering;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

  private TabAdapter adapter;
  private TabLayout tabLayout;
  private ViewPager viewPager;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_admin);
    viewPager = (ViewPager) findViewById(R.id.adminViewPager);
    tabLayout = (TabLayout) findViewById(R.id.adminTabLayout);

    adapter = new TabAdapter(getSupportFragmentManager());
    adapter.addFragment(new TabFoods(), "Food");
    adapter.addFragment(new TabProfile(), "Orders");

    viewPager.setAdapter(adapter);
    tabLayout.setupWithViewPager(viewPager);

  }

  public void AddFood(View view) {
    startActivity(new Intent(this, FoodCreate.class));

  }
}
