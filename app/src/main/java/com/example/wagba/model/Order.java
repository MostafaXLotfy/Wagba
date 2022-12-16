package com.example.wagba.model;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.database.PropertyName;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Order {
    private String uid;
//    @PropertyName("name")
    private String restaurantName;
    private int quantity;
    private Date date;
    private Float price;

    public Order(){
        restaurantName = "Not found";
        quantity = 0;
        price = 0.0f;
    }

    public Order(String restaurantName, int quantity, Date date, Float price) {
        this.restaurantName = restaurantName;
        this.quantity = quantity;
        this.date = date;
        this.price = price;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getQuantity() {
        return String.format(Integer.toString(quantity), "delivered");
    }

    public String getDate() {
        if(date == null) return "not available";
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ENGLISH);
        return dateFormat.format(date);
    }

    public String getPrice() {
        return String.format(Float.toString(price), "EGP");
    }

    public String getUid() {
        return uid;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
