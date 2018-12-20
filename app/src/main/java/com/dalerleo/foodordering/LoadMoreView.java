package com.dalerleo.foodordering;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.dalerleo.foodordering.models.Food;
import com.dalerleo.foodordering.views.MenuItemView;
import com.mindorks.placeholderview.InfinitePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.infinite.LoadMore;

import java.util.List;

@Layout(R.layout.load_more_view)
public class LoadMoreView {


  private InfinitePlaceHolderView mLoadMoreView;
  private List<InfiniteFeedInfo> mFeedList;

  public LoadMoreView(InfinitePlaceHolderView loadMoreView, List<InfiniteFeedInfo> feedList) {
    this.mLoadMoreView = loadMoreView;
    this.mFeedList = feedList;
  }

  @LoadMore
  protected void onLoadMore(){
    Log.d("DEBUG", "onLoadMore");
    new ForcedWaitedLoading();
  }

  class ForcedWaitedLoading implements Runnable{

    public ForcedWaitedLoading() {
      new Thread(this).start();
    }

    @Override
    public void run() {

      try {
        Thread.currentThread().sleep(2000);
      }catch (InterruptedException e){
        e.printStackTrace();
      }
      new Handler(Looper.getMainLooper()).post(new Runnable() {
        @Override
        public void run() {
          int count = mLoadMoreView.getViewCount();
          Log.d("DEBUG", "count " + count);
          Food food = new Food("PIZZA", 20000);

          mLoadMoreView.addView(new MenuItemView(mLoadMoreView.getContext(), food, "dalerleo"));
          mLoadMoreView.loadingDone();
        }
      });
    }
  }
}

