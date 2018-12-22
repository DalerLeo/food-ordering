package com.dalerleo.foodordering;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.dalerleo.foodordering.models.Order;
import com.dalerleo.foodordering.prefs.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FoodDetail extends AppCompatActivity {

  TextView detailName;
  TextView detailContent;
  TextView detailPrice;
  TextView detailAmount;
  EditText detailAddress;
  ImageView detailImage;
  MaterialButton onOrder;
  DatabaseReference orderRef;
  UserData userData;
  long priceNum;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_food_detail);
    orderRef = FirebaseDatabase.getInstance().getReference().child("orders");
    userData = new UserData();
    detailAmount = findViewById(R.id.detailAmount);
    detailName = findViewById(R.id.detailName);
    detailAddress = findViewById(R.id.detailAddress);
    detailContent = findViewById(R.id.detailContent);
    detailPrice = findViewById(R.id.detailPrice);
    detailImage = findViewById(R.id.detailImage);
    onOrder = findViewById(R.id.onOrder);
    Intent intent = getIntent();
    String name = intent.getStringExtra("name");
    String price = intent.getStringExtra("price");
    priceNum = intent.getLongExtra("priceNum", 0);
    String imageUrl = intent.getStringExtra("image_url");
    String content = intent.getStringExtra("content");

    detailName.setText(name);
    detailContent.setText(content);
    detailPrice.setText(price);
    Glide.with(this).load(imageUrl).into(detailImage);

    onOrder.setOnClickListener(new android.view.View.OnClickListener() {
      @Override
      public void onClick(android.view.View v) {
        DatabaseReference newOrder = orderRef.push();
        long amount = Long.parseLong(detailAmount.getText().toString());

        newOrder.setValue(new Order(
          userData.getUsername(),
          detailName.getText().toString(),
          detailAddress.getText().toString(),
          (int) amount,
          amount * priceNum,
          1
        )).addOnCompleteListener(new OnCompleteListener<Void>() {
          @Override
          public void onComplete(@NonNull Task<Void> task) {
            Toast.makeText(FoodDetail.this, "Successfully ordered", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(FoodDetail.this, MainActivity.class));
          }
        });

      }
    });
  }

}
