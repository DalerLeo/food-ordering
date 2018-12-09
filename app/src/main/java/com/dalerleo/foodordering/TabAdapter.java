package com.dalerleo.foodordering;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TabAdapter extends FragmentStatePagerAdapter {
  private final List<Fragment> mFragmentList = new ArrayList<>();
  private final List<String> mFragmentTitleList = new ArrayList<>();

  public TabAdapter(FragmentManager fm) {
    super(fm);
  }

  @Nullable
  @Override
  public CharSequence getPageTitle(int position) {
    return mFragmentTitleList.get(position);
  }

  @Override
  public Fragment getItem(int i) {
    return mFragmentList.get(i);
  }

  public void addFragment(Fragment fragment, String title) {
    mFragmentList.add(fragment);
    mFragmentTitleList.add(title);
  }


  @Override
  public int getCount() {
    return mFragmentList.size();
  }
}
