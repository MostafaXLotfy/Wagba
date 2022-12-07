package com.example.wagba.OrderDetails;

public class PaymentModel {

    private float subTotal;
    private float tax;
    private float deliveryFees;
    private float total;

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

    public PaymentModel(float subTotal, float deliveryFees) {
        this.subTotal = subTotal;
        this.tax = subTotal * 0.14f;
        this.deliveryFees = deliveryFees;
        this.total = subTotal + tax + this.deliveryFees;
    }
}
