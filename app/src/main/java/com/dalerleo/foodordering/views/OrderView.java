package com.dalerleo.foodordering.views;

import android.content.Context;
import android.support.design.button.MaterialButton;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.dalerleo.foodordering.R;
import com.dalerleo.foodordering.models.Food;
import com.dalerleo.foodordering.models.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@Layout(R.layout.order_item_view)
public class OrderView {

  @View(R.id.orderUser)
  protected TextView orderUser;

  @View(R.id.orderName)
  protected TextView orderName;

  @View(R.id.orderPrice)
  protected TextView orderPrice;

  @View(R.id.orderAmount)
  protected TextView orderAmount;

  @View(R.id.acceptBtn)
  protected MaterialButton acceptBtn;

  @View(R.id.cancelBtn)
  protected MaterialButton cancelBtn;

  protected String username;
  protected Order mInfo;
  protected Context mContext;
  private DatabaseReference foodsRef;


  public OrderView(Context context, Order info) {
    mContext = context;
    mInfo = info;
  }

  public OrderView(Context context, Order info, String username) {
    mContext = context;
    mInfo = info;
    this.username = username;
  }

  @Resolve
  protected void onResolved() {
    foodsRef = FirebaseDatabase.getInstance().getReference().child("foods");


    cancelBtn.setOnClickListener(new android.view.View.OnClickListener() {
      @Override
      public void onClick(android.view.View v) {
        DatabaseReference newFood = foodsRef.push();

        Log.d("sdas", "");
      }
    });

    acceptBtn.setOnClickListener(new android.view.View.OnClickListener() {
      @Override
      public void onClick(android.view.View v) {
        Log.d("sdas", "");
      }
    });


    orderUser.setText(mInfo.getUsername());
    orderName.setText(mInfo.getName());
    orderAmount.setText(mInfo.getAmountText());
    orderPrice.setText(mInfo.getPriceCurrency());
  }
}
