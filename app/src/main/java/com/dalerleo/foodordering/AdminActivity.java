package com.dalerleo.foodordering;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class AdminActivity extends AppCompatActivity {
  private static final int RC_SIGN_IN = 123;

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

  public void signOut(View view) {
      // SIGN OUT
      AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
          startActivity(new Intent(AdminActivity.this, LoginActivity.class));
        }
      });
  }
}
