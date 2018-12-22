package com.dalerleo.foodordering;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dalerleo.foodordering.prefs.UserData;

public class TabProfile extends Fragment {

  TextView profileEmail;
  TextView profileName;
  Button onOrderList;
  UserData user;
  @Nullable
  @Override
  public View onCreateView(
    @NonNull LayoutInflater inflater,
    @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_profile, container, false);
    user = new UserData();
    onOrderList = view.findViewById(R.id.onOrderList);
    profileEmail = view.findViewById(R.id.profileEmail);
    profileName = view.findViewById(R.id.profileName);

    Log.d("LOGGG: ", user.getUsername());
    Log.d("LOGGG: ", user.getName());
    profileEmail.setText(user.getUsername());
//    profileName.setText(user.getName());
    onOrderList.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(view.getContext(), OrderList.class));
      }
    });
    return view;
  }
}
