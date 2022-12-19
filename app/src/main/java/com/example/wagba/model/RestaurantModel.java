package com.example.wagba.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.wagba.utils.Constant;

import java.util.List;

@Entity(tableName = "restaurant_table")
public class RestaurantModel implements Parcelable {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "restaurantID")
    private int uid;
    private String name;
    private String description;
    private double deliveryFees;

    public RestaurantModel(){}

    @Ignore
    public RestaurantModel(String name, String description, float deliveryFees) {
        this.name = name;
        this.description = description;
        this.deliveryFees = deliveryFees;
    }

    protected RestaurantModel(Parcel in) {
        uid = in.readInt();
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(uid);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeDouble(deliveryFees);
    }

}
