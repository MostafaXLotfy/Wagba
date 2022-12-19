package com.example.wagba.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Meal implements Parcelable {

//    @PropertyName("mealID")
    private int uid;
    private String name;
    private String description;
    private float price;
    private boolean available;

    public Meal(){

    }

    protected Meal(Parcel in) {
        name = in.readString();
        description = in.readString();
        price = in.readFloat();
        available = in.readByte() != 0;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return this.price;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String isAvailable() {
        return available ? "Available": "Not Available";
    }


    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeFloat(price);
        parcel.writeByte((byte) (available ? 1 : 0));
    }

}
