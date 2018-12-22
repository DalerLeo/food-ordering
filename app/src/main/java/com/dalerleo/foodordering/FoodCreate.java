package com.dalerleo.foodordering;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dalerleo.foodordering.models.Food;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FoodCreate extends AppCompatActivity {
  FirebaseStorage mStore;
  StorageReference imageRef;
  DatabaseReference foodsRef;

  Button chooseImg, uploadFood;
  ProgressBar progressBar;
  EditText priceEl, nameEl, contentEl;
  ImageView imgView;
  int PICK_IMAGE_REQUEST = 111;
  String imagePath;
  Uri filePath;
  ProgressDialog pd;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_food_create);

    mStore = FirebaseStorage.getInstance();
    foodsRef = FirebaseDatabase.getInstance().getReference().child("foods");

    imageRef = mStore.getReference("food_images");
    chooseImg = (Button) findViewById(R.id.chooseImg);
    uploadFood = (Button) findViewById(R.id.uploadFood);
    priceEl = (EditText) findViewById(R.id.createPrice);
    nameEl = (EditText) findViewById(R.id.createName);
    contentEl = (EditText) findViewById(R.id.createContent);
    imgView = (ImageView) findViewById(R.id.imgView);
    progressBar = findViewById(R.id.progressBar);

    progressBar.setVisibility(View.INVISIBLE);
    // Choose image from gallery
    chooseImg.setOnClickListener(new View.OnClickListener() {
      @Override
      // Open Gallery
      public void onClick(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
      }
    });


    uploadFood.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String name = nameEl.getText().toString();
        String content = contentEl.getText().toString();
        String price = priceEl.getText().toString();
        if (!price.equals("") && !name.equals("") && !content.isEmpty() && !imagePath.isEmpty()) {
          progressBar.setVisibility(View.VISIBLE);
          DatabaseReference newFood = foodsRef.push();
          newFood.setValue(new Food(
            name,
            content,
            imagePath,
            Long.parseLong(price)
          )).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
              progressBar.setVisibility(View.INVISIBLE);
              startActivity(new Intent(FoodCreate.this, AdminActivity.class));
            }
          });
        }else {
          Toast.makeText(FoodCreate.this, "Please, fill all fields", Toast.LENGTH_LONG).show();
        }

      }
    });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    progressBar.setVisibility(View.VISIBLE);
    if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
      filePath = data.getData();
      if (filePath.getLastPathSegment() != null) {
        final StorageReference photoRef = imageRef.child(filePath.getLastPathSegment());
        try {
          photoRef.putFile(filePath)
            .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
              @Override
              public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                  throw task.getException();
                }

                // Continue with the task to get the download URL
                return photoRef.getDownloadUrl();
              }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
              if (task.isSuccessful()) {
                Uri downloadUri = task.getResult();
                imagePath = downloadUri.toString();
                progressBar.setVisibility(View.GONE);
              } else {
                Toast.makeText(FoodCreate.this, "ERROR While Loading File", Toast.LENGTH_LONG).show();
              }
            }
          });
          //getting image from gallery
          Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

          //Setting image to ImageView
          imgView.setImageBitmap(bitmap);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

    }
  }

}
