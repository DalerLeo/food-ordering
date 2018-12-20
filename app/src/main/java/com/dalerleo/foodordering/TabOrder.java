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
import com.dalerleo.foodordering.models.Order;
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

public class TabOrder extends Fragment {
  private InfinitePlaceHolderView mLoadMoreView;
  private List<Food> foodList = new ArrayList<>();
  DatabaseReference foodsRef;
  ChildEventListener childEventListener;
  FirebaseStorage mStore;
  StorageReference imageRef;
  @Nullable
  @Override
  public View onCreateView(
    @NonNull LayoutInflater inflater,
    @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_orders, container, false);
    mLoadMoreView = view.findViewById(R.id.orderItems);
    setupView();
    return view;
  }


  private void setupView(){
    foodsRef = FirebaseDatabase.getInstance().getReference().child("orders");

    childEventListener = new ChildEventListener() {
      @Override
      public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Order order = dataSnapshot.getValue(Order.class);
        mLoadMoreView.addView(new OrderView(TabOrder.this.getContext(), order));
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