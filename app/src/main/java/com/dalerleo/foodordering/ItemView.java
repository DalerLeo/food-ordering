package com.dalerleo.foodordering;

import android.content.Context;
import android.support.design.button.MaterialButton;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.dalerleo.foodordering.models.Food;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@Layout(R.layout.load_more_item_view)
public class ItemView {

  @View(R.id.titleTxt)
  protected TextView titleTxt;
  boolean isPressed = false;

  @View(R.id.priceTxt)
  protected TextView priceTxt;

  @View(R.id.imageView)
  protected ImageView imageView;

  @View(R.id.animation_view)
  protected LottieAnimationView lottieFav;

  @View(R.id.cardBtn)
  protected MaterialButton cardBtn;

  protected Food mInfo;
  protected Context mContext;

  public ItemView(Context context, Food info) {
    mContext = context;
    mInfo = info;
  }

  @Resolve
  protected void onResolved() {
    cardBtn.setOnClickListener(new android.view.View.OnClickListener() {
      @Override
      public void onClick(android.view.View v) {
        Log.d("sdas", "");
      }
    });

    lottieFav.setOnClickListener(new android.view.View.OnClickListener() {
      @Override
      public void onClick(android.view.View v) {
        if (!lottieFav.isAnimating()) {
          if (!isPressed) {
            lottieFav.playAnimation();
            isPressed = true;
          } else {
            lottieFav.setProgress(0);
            isPressed = false;
          }
        }
      }
    });


    titleTxt.setText(mInfo.getName());
    priceTxt.setText(Long.toString(mInfo.getPrice()));
    Glide.with(mContext).load(mInfo.getImage_url()).into(imageView);
  }
}
