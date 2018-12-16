package com.dalerleo.foodordering;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfiniteFeedInfo {

  @SerializedName("title")
  @Expose
  private String title;

  @SerializedName("image_url")
  @Expose
  private String imageUrl;

  @SerializedName("price")
  @Expose
  private String price;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

}
