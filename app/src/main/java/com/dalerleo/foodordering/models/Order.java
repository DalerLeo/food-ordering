package com.dalerleo.foodordering.models;

public class Order {

private String name;
private int amount;
private int price;

  public Order(String name, int amount, int price) {
    this.name = name;
    this.amount = amount;
    this.price = price;
  }

  public String getName() {

    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }
}
