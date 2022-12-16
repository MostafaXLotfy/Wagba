package com.example.wagba.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class RestaurantModel implements Parcelable {
    private String uid;
    private String name;
    private String description;
    private double deliveryFees;

    public RestaurantModel(){}

    public RestaurantModel(String name, String description, float deliveryFees) {
        this.name = name;
        this.description = description;
        this.deliveryFees = deliveryFees;
    }

    protected RestaurantModel(Parcel in) {
        uid = in.readString();
        name = in.readString();
        description = in.readString();
        deliveryFees = in.readDouble();
    }

    public static final Creator<RestaurantModel> CREATOR = new Creator<RestaurantModel>() {
        @Override
        public RestaurantModel createFromParcel(Parcel in) {
            return new RestaurantModel(in);
        }

        @Override
        public RestaurantModel[] newArray(int size) {
            return new RestaurantModel[size];
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
    }

}
