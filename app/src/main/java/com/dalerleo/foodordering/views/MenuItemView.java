package com.dalerleo.foodordering.views;

import android.content.Context;
import android.support.design.button.MaterialButton;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.dalerleo.foodordering.R;
import com.dalerleo.foodordering.models.Food;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@Layout(R.layout.menu_item_view)
public class MenuItemView {


  DatabaseReference favsRef;
  boolean isFav = false;
  boolean isPressed = false;

  @View(R.id.titleTxt)
  protected TextView titleTxt;

  @View(R.id.priceTxt)
  protected TextView priceTxt;

  @View(R.id.imageView)
  protected ImageView imageView;

  @View(R.id.animation_view)
  protected LottieAnimationView lottieFav;

  public Food mInfo;
  protected Context mContext;
  private  String username;

  public MenuItemView(Context context, Food info,String username) {
    mContext = context;
    mInfo = info;
    this.username = username;
  }

  public MenuItemView(Context context, Food info,String username, boolean isFav) {
    mContext = context;
    mInfo = info;
    this.username = username;
    this.isFav = isFav;
    this.isPressed = true;
  }

  @Resolve
  protected void onResolved() {
    favsRef = FirebaseDatabase.getInstance().getReference().child("favs");

    if(isFav) {
      lottieFav.playAnimation();
    }

    lottieFav.setOnClickListener(new android.view.View.OnClickListener() {
      @Override
      public void onClick(android.view.View v) {

        if (!lottieFav.isAnimating() && !username.isEmpty()) {
          if (!isPressed) {
            setFav();
            lottieFav.playAnimation();
            isPressed = true;
          } else {
            unsetFav();
            lottieFav.setProgress(0);
            isPressed = false;
          }
        }
      }
    });

    titleTxt.setText(mInfo.getName());
    priceTxt.setText(mInfo.getPriceCurrency());
    Glide.with(mContext).load(mInfo.getImage_url()).into(imageView);
  }

  void setFav() {
    DatabaseReference newFav = favsRef.child(username).child(mInfo.getName());

    newFav.setValue(new Food(
      mInfo.getName(),
      mInfo.getImage_url(),
      mInfo.getPrice()
    ));
  }
  void unsetFav() {
    favsRef.child(username).child(mInfo.getName()).removeValue();
  }
}
