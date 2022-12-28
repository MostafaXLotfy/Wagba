package com.example.wagba.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "restaurant_table")
public class Restaurant implements Parcelable {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "restaurantID")
    private String uid;
    private String name;
    private String description;
    private double deliveryFees;
    @Ignore
    private String logo;

    public Restaurant(){}

    @Ignore
    public Restaurant(String name, String description, float deliveryFees) {
        this.name = name;
        this.description = description;
        this.deliveryFees = deliveryFees;
    }

    protected Restaurant(Parcel in) {
        uid = in.readString();
        name = in.readString();
        description = in.readString();
        deliveryFees = in.readDouble();
        logo = in.readString();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeliveryFees(double deliveryFees) {
        this.deliveryFees = deliveryFees;
    }

    public double getDeliveryFees() {
        return deliveryFees;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(uid);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeDouble(deliveryFees);
        parcel.writeString(logo);
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
