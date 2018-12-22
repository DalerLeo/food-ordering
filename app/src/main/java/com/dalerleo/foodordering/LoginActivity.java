package com.dalerleo.foodordering;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dalerleo.foodordering.prefs.UserData;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class LoginActivity extends AppCompatActivity {
  // Choose an arbitrary request code value
  private static final int RC_SIGN_IN = 123;
  public static String USER_REF = "userRef";
  public static String USER_NAME = "userName";
  private String username;
  FirebaseAuth mAuth;
  FirebaseAuth.AuthStateListener mAuthStateListerner;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login_activity);


    mAuth = FirebaseAuth.getInstance();

    mAuthStateListerner = new FirebaseAuth.AuthStateListener() {
      @Override
      public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
          onSignInInit(user.getEmail(), user.getDisplayName());
        }
        else {
          onSignOutClean();
          startActivityForResult(
            // Get an instance of AuthUI based on the default app
            AuthUI.getInstance()
              .createSignInIntentBuilder()
              .setIsSmartLockEnabled(false)
              .build(),
            RC_SIGN_IN);
        }
      }
    };

  }

  private void onSignOutClean() {
    username = "";
  }

  private void onSignInInit(String userName, String name) {
    this.username = userName;
    UserData user = new UserData();
    user.setUsername(username);
//    user.setUsername(name);
    if (userName.contains("admin")) {
      Intent intent = new Intent(this, AdminActivity.class);
      startActivity(intent);
    }else {
      Intent intent = new Intent(this, MainActivity.class);
      startActivity(intent);

    }

  }

  @Override
  protected void onResume() {
    super.onResume();
    mAuth.addAuthStateListener(mAuthStateListerner);
  }

  @Override
  protected void onPause() {
    super.onPause();
    if(mAuthStateListerner != null) {
      mAuth.removeAuthStateListener(mAuthStateListerner);
    }
  }


  public void signOut(View view) {
    AuthUI.getInstance().signOut(this);
  }


}
