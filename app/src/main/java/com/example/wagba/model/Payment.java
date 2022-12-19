package com.example.wagba.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "payment_table")
public class Payment {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "paymentID")
    private int uid;

    @NonNull
    @ColumnInfo(name = "restaurantID")
    private int restaurantID;

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
