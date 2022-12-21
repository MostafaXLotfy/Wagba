package com.example.wagba.model;

import com.example.wagba.utils.Constant;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.database.PropertyName;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Order {
    @PropertyName(Constant.ORDER_ID)
    private String uid;
    String restaurantName;
    private int quantity;
    private String date;
    private Float price;

    public Order(){
        quantity = 0;
        price = 0.0f;
    }

    public Order(Basket basket){
        restaurantName = basket.getRestaurantName();
        price = basket.getTotal();
        quantity = basket.getQuantity();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ENGLISH);
        date = dateFormat.format(new Date());
    }

    public String getRestaurantName() {
        return this.restaurantName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }

    public float getPrice() {
        return price;
    }

    public String getUid() {
        return uid;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setRestaurant(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
