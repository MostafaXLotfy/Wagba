package com.example.wagba.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@Entity(tableName = "payment_table")
public class Payment {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "paymentID")
    //exclude from firebase
    @Exclude
    private String uid;

    @NonNull
    @ColumnInfo(name = "restaurantID")
    @Exclude
    private String restaurantID;

    private float subTotal;
    private float tax;
    private float deliveryFees;
    private float total;

    public Payment(){

    }

    @Ignore
    Payment(float deliveryFees){
        this.deliveryFees = deliveryFees;
        this.total += this.deliveryFees;
    }

    @Ignore
    public Payment(float subTotal, float deliveryFees) {
        this.subTotal = subTotal;
        this.tax = subTotal * 0.14f;
        this.deliveryFees = deliveryFees;
        this.total = subTotal + tax + this.deliveryFees;
    }


    public float getSubTotal() {
        return subTotal;
    }

    public float getTax() {
        return tax;
    }

    public float getDeliveryFees() {
        return deliveryFees;
    }

    public float getTotal() {
        return total;
    }



    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public void setDeliveryFees(float deliveryFees) {
        this.deliveryFees = deliveryFees;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void calculateItemPrice(OrderItem item){
        this.subTotal += item.getTotalPrice();
        this.tax = (float)(this.subTotal * 0.14);
        this.total = this.subTotal + this.tax;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }
}
