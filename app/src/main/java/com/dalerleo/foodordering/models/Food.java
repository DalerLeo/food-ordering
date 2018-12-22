package com.dalerleo.foodordering.models;

public class Food {

  private String name;
  private String content;
  private String image_url;
  private long price;

  public String getImage_url() {
    return image_url;
  }
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Food(String name, String content, String image_url, long price) {
    this.name = name;
    this.content = content;
    this.image_url = image_url;
    this.price = price;
  }

  public void setImage_url(String image_url) {
    this.image_url = image_url;
  }

  public Food(String name, String image_url, long price) {

    this.name = name;
    this.image_url = image_url;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getPrice() {
    return price;
  }
  public String getPriceCurrency() {
    return Long.toString(price) + " SUM";
  }

  public void setPrice(long price) {
    this.price = price;
  }


  public Food() {

  }

  public Food(String name, long price) {

    this.name = name;
    this.price = price;
  }
}
