package com.dalerleo.foodordering;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalerleo.foodordering.models.Food;
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

public class TabMenu extends Fragment {
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
    View view = inflater.inflate(R.layout.fragment_menu, container, false);
    mLoadMoreView = view.findViewById(R.id.loadMore);
    setupView();
    return view;
  }


  private void setupView(){
    foodsRef = FirebaseDatabase.getInstance().getReference().child("foods");

    childEventListener = new ChildEventListener() {
      @Override
      public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Food food = dataSnapshot.getValue(Food.class);
        mLoadMoreView.addView(new ItemView(TabMenu.this.getContext(), food));
        Log.d("FOOD_NAME", food.getName());
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


//    mLoadMoreView.setLoadMoreResolver(new LoadMoreView(mLoadMoreView, ));
  }
}
