package com.example.wagba.Basket.orderItems;

import android.os.Parcel;

public class OrderItemsModel {
    private int quantity;
    private String mealName;
    private String size;
    private float price;

    protected OrderItemsModel(Parcel in) {
        quantity = in.readInt();
        mealName = in.readString();
        size = in.readString();
        price = in.readFloat();
    }



    public String getQuantity() {
        return String.format(Integer.toString(quantity), "X");
    }

    public String getMealName() {
        return mealName;
    }

    public String getSize() {
        return size;
    }

    public String getPrice() {
        return String.format(Float.toString(price), "EGP");
    }

    public float getTotalPrice(){
        return this.price * this.quantity;

    }
    public OrderItemsModel(int quantity, String mealName, String size, float price) {
        this.quantity = quantity;
        this.mealName = mealName;
        this.size = size;
        this.price = price;
    }

}
