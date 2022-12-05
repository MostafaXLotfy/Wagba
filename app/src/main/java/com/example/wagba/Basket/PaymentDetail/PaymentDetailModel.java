package com.example.wagba.Basket.PaymentDetail;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class PaymentDetailModel implements Parcelable {
    private float subTotal;
    private float tax;
    private float deliveryFees;
    private float total;

    public PaymentDetailModel(float subTotal, float deliveryFees) {
        this.subTotal = subTotal;
        this.deliveryFees = deliveryFees;
        this.tax = subTotal * 0.14f;
        this.total = this.subTotal + this.deliveryFees + this.tax;
    }


    protected PaymentDetailModel(Parcel in) {
        subTotal = in.readFloat();
        tax = in.readFloat();
        deliveryFees = in.readFloat();
        total = in.readFloat();
    }

    public static final Creator<PaymentDetailModel> CREATOR = new Creator<PaymentDetailModel>() {
        @Override
        public PaymentDetailModel createFromParcel(Parcel in) {
            return new PaymentDetailModel(in);
        }

        @Override
        public PaymentDetailModel[] newArray(int size) {
            return new PaymentDetailModel[size];
        }
    };

    public String getSubTotal() {
        return String.format(Float.toString(subTotal), "EGP");
    }

    public String getTax() {
        return String.format(Float.toString(tax), "EGP");
    }

    public String getDeliveryFees() {
        return String.format(Float.toString(deliveryFees), "EGP");
    }

    public String getTotal() {
        return String.format(Float.toString(total), "EGP");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(subTotal);
        parcel.writeFloat(tax);
        parcel.writeFloat(deliveryFees);
        parcel.writeFloat(total);
    }
}
