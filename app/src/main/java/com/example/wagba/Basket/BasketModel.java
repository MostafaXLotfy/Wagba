package com.example.wagba.Basket;

import com.example.wagba.Basket.orderItems.OrderItemsModel;

import java.util.ArrayList;

public class BasketModel {
    private String RestaurantName;
    private ArrayList<OrderItemsModel> orderItemsModels;

    private float subTotal;
    private float tax;
    private float deliveryFees;
    private float total;

    public String getRestaurantName() {
        return RestaurantName;
    }

    public ArrayList<OrderItemsModel> getOrderDetailsModels() {
        return orderItemsModels;
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
            ArrayList<OrderItemsModel> orderItemsModels
    ) {
        RestaurantName = restaurantName;
        this.orderItemsModels = orderItemsModels;
        OrderItemsModel model;
        for (int i = 0; i < orderItemsModels.size(); i++){
            model = orderItemsModels.get(i);
            this.subTotal += model.getTotalPrice();
        }
        this.tax = subTotal * 0.14f;
        this.deliveryFees = deliveryFees;
        this.total = subTotal + tax + this.deliveryFees;

    }
}
