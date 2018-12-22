package com.dalerleo.foodordering;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dalerleo.foodordering.models.Food;
import com.dalerleo.foodordering.prefs.UserData;
import com.dalerleo.foodordering.views.MenuItemView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mindorks.placeholderview.InfinitePlaceHolderView;

import java.util.List;

public class TabFavorites extends Fragment {
  ChildEventListener childEventListener;
  private InfinitePlaceHolderView mLoadMoreView;
  String userName;
  ProgressBar progressBar;

  @Nullable
  @Override
  public View onCreateView(
    @NonNull LayoutInflater inflater,
    @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_favorites, container, false);
    mLoadMoreView = view.findViewById(R.id.favList);
    progressBar = view.findViewById(R.id.progress);
    userName = new UserData().getUserPath();
    setupView();
    return view;
  }

  private void setupView() {
    Query userFavFood = FirebaseDatabase
      .getInstance()
      .getReference()
      .child("favs")
      .child(userName);

    childEventListener = new ChildEventListener() {
      @Override
      public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        progressBar.setVisibility(View.INVISIBLE);

        Food food = dataSnapshot.getValue(Food.class);
        mLoadMoreView.addView(new MenuItemView(
          TabFavorites.this.getContext(),
          food,
          userName,
          true
        ));
      }

      @Override
      public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Log.d("ADDD", "CHANGE");

      }

      @Override
      public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
        List<Object> list = mLoadMoreView.getAllViewResolvers();
        Food food = dataSnapshot.getValue(Food.class);
        if (food == null)
          return;

        for (Object ob : list) {
          if (ob instanceof MenuItemView)
            if (((MenuItemView) ob).mInfo.getName().equals(food.getName()))
              mLoadMoreView.removeView(ob);
        };
      }

      @Override
      public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    };


    userFavFood.addChildEventListener(childEventListener);

  }
}

