package com.example.wagba.Basket;

import com.example.wagba.Basket.OrderDetails.OrderDetailsModel;

import java.util.ArrayList;

public class BasketModel {
    private String RestaurantName;
    private ArrayList<OrderDetailsModel> orderDetailsModels;

    private float subTotal;
    private float tax;
    private float deliveryFees;
    private float total;

    public String getRestaurantName() {
        return RestaurantName;
    }

    public ArrayList<OrderDetailsModel> getOrderDetailsModels() {
        return orderDetailsModels;
    }


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

    public BasketModel(
            String restaurantName,
            float deliveryFees,
            ArrayList<OrderDetailsModel> orderDetailsModels
    ) {
        RestaurantName = restaurantName;
        this.orderDetailsModels = orderDetailsModels;
        OrderDetailsModel model;
        for (int i = 0; i < orderDetailsModels.size(); i++){
            model = orderDetailsModels.get(i);
            this.subTotal += model.getTotalPrice();
        }
        this.tax = subTotal * 0.14f;
        this.deliveryFees = deliveryFees;
        this.total = subTotal + tax + this.deliveryFees;

    }
}
