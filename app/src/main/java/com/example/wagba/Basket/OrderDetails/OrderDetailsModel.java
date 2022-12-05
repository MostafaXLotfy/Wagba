package com.example.wagba.Basket.OrderDetails;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderDetailsModel implements Parcelable {
    private int quantity;
    private String mealName;
    private String size;
    private float price;

    protected OrderDetailsModel(Parcel in) {
        quantity = in.readInt();
        mealName = in.readString();
        size = in.readString();
        price = in.readFloat();
    }


    public static final Creator<OrderDetailsModel> CREATOR = new Creator<OrderDetailsModel>() {
        @Override
        public OrderDetailsModel createFromParcel(Parcel in) {
            return new OrderDetailsModel(in);
        }

        @Override
        public OrderDetailsModel[] newArray(int size) {
            return new OrderDetailsModel[size];
        }
    };

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

    public OrderDetailsModel(int quantity, String mealName, String size, float price) {
        this.quantity = quantity;
        this.mealName = mealName;
        this.size = size;
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(quantity);
        parcel.writeString(mealName);
        parcel.writeString(size);
        parcel.writeFloat(price);
    }
}
