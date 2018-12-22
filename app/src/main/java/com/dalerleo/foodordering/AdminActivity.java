package com.dalerleo.foodordering;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class AdminActivity extends AppCompatActivity {
  private TabAdapter adapter;
  private TabLayout tabLayout;
  private ViewPager viewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_admin);
    viewPager = findViewById(R.id.adminViewPager);
    tabLayout = findViewById(R.id.adminTabLayout);

    adapter = new TabAdapter(getSupportFragmentManager());
    adapter.addFragment(new TabFoods(), "Food");
    adapter.addFragment(new TabOrder(), "Orders");
    adapter.addFragment(TabProfile.newInstance("Admin"), "Profile");

    viewPager.setAdapter(adapter);
    tabLayout.setupWithViewPager(viewPager);
  }

  public void AddFood(View view) {
    NotificationCompat.Builder mBuilder =
      new NotificationCompat.Builder(this)
        .setSmallIcon(R.drawable.fav_icon)
        .setContentTitle("My notification")
        .setContentText("Hello World!");
// Creates an explicit intent for an Activity in your app
    Intent resultIntent = new Intent(this, FoodCreate.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
    TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
    stackBuilder.addParentStack(FoodCreate.class);
// Adds the Intent that starts the Activity to the top of the stack
    stackBuilder.addNextIntent(resultIntent);
    PendingIntent resultPendingIntent =
      stackBuilder.getPendingIntent(
        0,
        PendingIntent.FLAG_UPDATE_CURRENT
      );
    mBuilder.setContentIntent(resultPendingIntent);
    NotificationManager mNotificationManager =
      (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
    mNotificationManager.notify(123, mBuilder.build());

//    startActivity(new Intent(this, FoodCreate.class));
  }

  public void signOut(View view) {
    // SIGN OUT
    AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
      @Override
      public void onComplete(@NonNull Task<Void> task) {
        startActivity(new Intent(AdminActivity.this, LoginActivity.class));
      }
    });
  }



}
