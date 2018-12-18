package com.dalerleo.foodordering;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dalerleo.foodordering.models.Food;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mindorks.placeholderview.InfinitePlaceHolderView;

import java.util.List;

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
