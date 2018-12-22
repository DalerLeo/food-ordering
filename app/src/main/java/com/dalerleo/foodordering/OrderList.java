package com.dalerleo.foodordering;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.dalerleo.foodordering.R;
import com.dalerleo.foodordering.models.Food;
import com.dalerleo.foodordering.models.Order;
import com.dalerleo.foodordering.prefs.UserData;
import com.dalerleo.foodordering.views.OrderView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mindorks.placeholderview.InfinitePlaceHolderView;

import java.util.ArrayList;
import java.util.List;

public class OrderList extends AppCompatActivity {
  private InfinitePlaceHolderView mLoadMoreView;
  private List<Food> foodList = new ArrayList<>();
  Query orderRef;
  ChildEventListener childEventListener;

  ProgressBar progressBar;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_order_list);
    mLoadMoreView = findViewById(R.id.orderItems);
    progressBar = findViewById(R.id.progress);
    setupView();
  }

  private void setupView(){
    orderRef = FirebaseDatabase
      .getInstance()
      .getReference()
      .child("orders")
      .orderByChild("username")
      .equalTo(new UserData().getUsername());

    childEventListener = new ChildEventListener() {
      @Override
      public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        progressBar.setVisibility(View.GONE);
        Order order = dataSnapshot.getValue(Order.class);
        String orderId = dataSnapshot.getKey();
        mLoadMoreView.addView(new OrderView(OrderList.this, order, true));
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

    orderRef.addChildEventListener(childEventListener);

    //  }


//    mLoadMoreView.setLoadMoreResolver(new LoadMoreView(mLoadMoreView, ));
  }
}
