package com.dalerleo.foodordering;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mindorks.placeholderview.InfinitePlaceHolderView;

import java.util.List;

public class TabMenu extends Fragment {
  private InfinitePlaceHolderView mLoadMoreView;

  @Nullable
  @Override
  public View onCreateView(
    @NonNull LayoutInflater inflater,
    @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_menu, container, false);
    mLoadMoreView = view.findViewById(R.id.loadMore);
    setupView();
    return view;
  }


  private void setupView(){

    List<InfiniteFeedInfo> feedList = Utils.loadInfiniteFeeds(TabMenu.this.getContext());
    Log.d("DEBUG", "LoadMoreView.LOAD_VIEW_SET_COUNT " + LoadMoreView.LOAD_VIEW_SET_COUNT);
    for(int i = 0; i < LoadMoreView.LOAD_VIEW_SET_COUNT; i++){
      mLoadMoreView.addView(new ItemView(TabMenu.this.getContext(), feedList.get(i)));
    }
    mLoadMoreView.setLoadMoreResolver(new LoadMoreView(mLoadMoreView, feedList));
  }
}
