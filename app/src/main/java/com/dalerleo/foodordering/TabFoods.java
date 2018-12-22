package com.dalerleo.foodordering;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dalerleo.foodordering.models.Food;
import com.dalerleo.foodordering.prefs.UserData;
import com.dalerleo.foodordering.views.FoodView;
import com.dalerleo.foodordering.views.MenuItemView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mindorks.placeholderview.InfinitePlaceHolderView;

import java.util.ArrayList;
import java.util.List;

public class TabFoods extends Fragment {
  private InfinitePlaceHolderView mLoadMoreView;
  private List<Food> foodList = new ArrayList<>();
  DatabaseReference foodsRef;
  ChildEventListener childEventListener;
  ProgressBar progressBar;
  @Nullable
  @Override
  public View onCreateView(
    @NonNull LayoutInflater inflater,
    @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_food, container, false);
    mLoadMoreView = view.findViewById(R.id.foodItems);
    progressBar = view.findViewById(R.id.progress);

    setupView();
    return view;
  }


  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    progressBar.setVisibility(View.VISIBLE);

  }

  private void setupView(){
    foodsRef = FirebaseDatabase.getInstance().getReference().child("foods");

    childEventListener = new ChildEventListener() {
      @Override
      public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        progressBar.setVisibility(View.INVISIBLE);
        Food food = dataSnapshot.getValue(Food.class);
        mLoadMoreView.addView(new MenuItemView(TabFoods.this.getContext(), food, true));
      }

      @Override
      public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

      }

      @Override
      public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

      }

      @Override
      public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    };

    foodsRef.addChildEventListener(childEventListener);

  }
}
