package com.example.wagba.model;

import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;

@Entity(tableName = "order_item_table")
public class OrderItem {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "orderItemID")
    private int uid;

    @NonNull
    @ColumnInfo(name = "restaurantID")
    private int restaurantID;

    private int quantity;

//    @Ignore
    private String mealName;
    private String size;
    private float price;

    public OrderItem(){

    }

    protected OrderItem(Parcel in) {
        quantity = in.readInt();
        mealName = in.readString();
        size = in.readString();
        price = in.readFloat();
    }


    public OrderItem(String mealName, int quantity, String size, float price) {
        this.quantity = quantity;
        this.mealName = mealName;
        this.size = size;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotalPrice() {
        return this.price * this.quantity;

    }



    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }
}
