package com.dalerleo.foodordering.views;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dalerleo.foodordering.R;
import com.dalerleo.foodordering.models.Food;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@Layout(R.layout.food_item_view)
public class FoodView {

  @View(R.id.foodName)
  protected TextView titleTxt;
  boolean isPressed = false;

  @View(R.id.foodPrice)
  protected TextView priceTxt;

  @View(R.id.foodImage)
  protected ImageView imageView;

  protected Food mInfo;
  protected Context mContext;

  public FoodView(Context context, Food info) {
    mContext = context;
    mInfo = info;
  }

  @Resolve
  protected void onResolved() {

    titleTxt.setText(mInfo.getName());
    priceTxt.setText(Long.toString(mInfo.getPrice()));
    Glide.with(mContext).load(mInfo.getImage_url()).into(imageView);
  }
}
