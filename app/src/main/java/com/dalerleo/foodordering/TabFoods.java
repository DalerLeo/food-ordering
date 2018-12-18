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

import com.dalerleo.foodordering.models.Food;
import com.dalerleo.foodordering.models.Order;
import com.dalerleo.foodordering.views.FoodView;
import com.dalerleo.foodordering.views.OrderView;
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
  @Nullable
  @Override
  public View onCreateView(
    @NonNull LayoutInflater inflater,
    @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_food, container, false);
    mLoadMoreView = view.findViewById(R.id.foodItems);
    Log.d("ADD: ", "FOOODDD");

    mLoadMoreView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
      @Override
      public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        return false;
      }

      @Override
      public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

      }

      @Override
      public void onRequestDisallowInterceptTouchEvent(boolean b) {

      }
    });
    setupView();
    return view;
  }


  private void setupView(){
    foodsRef = FirebaseDatabase.getInstance().getReference().child("foods");

    childEventListener = new ChildEventListener() {
      @Override
      public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Food food = dataSnapshot.getValue(Food.class);
        mLoadMoreView.addView(new FoodView(TabFoods.this.getContext(), food));
      }

      @Override
      public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Log.d("ADDD", "CHANGE");

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

  //  }

    mLoadMoreView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
      @Override
      public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        return false;
      }

      @Override
      public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        Log.d("TOUCH", "tOUCJ");

      }

      @Override
      public void onRequestDisallowInterceptTouchEvent(boolean b) {

      }
    });

//    mLoadMoreView.setLoadMoreResolver(new LoadMoreView(mLoadMoreView, ));
  }
}
