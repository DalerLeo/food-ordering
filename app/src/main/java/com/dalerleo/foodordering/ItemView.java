package com.dalerleo.foodordering;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@Layout(R.layout.load_more_item_view)
public class ItemView {

  @View(R.id.titleTxt)
  protected TextView titleTxt;

  @View(R.id.priceTxt)
  protected TextView priceTxt;

  @View(R.id.imageView)
  protected ImageView imageView;

  protected InfiniteFeedInfo mInfo;
  protected Context mContext;

  public ItemView(Context context, InfiniteFeedInfo info) {
    mContext = context;
    mInfo = info;
  }

  @Resolve
  protected void onResolved() {
    Log.d("TITLE", mInfo.getTitle());
    Log.d("CAPTIOMN", mInfo.getPrice());
    titleTxt.setText(mInfo.getTitle());
    priceTxt.setText(mInfo.getPrice());
    Glide.with(mContext).load(mInfo.getImageUrl()).into(imageView);
  }
}
