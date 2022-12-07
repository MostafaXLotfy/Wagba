package com.example.wagba.Basket;

import com.example.wagba.Basket.orderItems.OrderItemsModel;
import com.example.wagba.OrderDetails.PaymentModel;

import java.util.ArrayList;

public class BasketModel {
    private String RestaurantName;
    private ArrayList<OrderItemsModel> orderItemsModels;
    private PaymentModel paymentModel;


    public String getRestaurantName() {
        return RestaurantName;
    }

    public ArrayList<OrderItemsModel> getOrderItemsModels() {
        return orderItemsModels;
    }


    public String getSubTotal() {
        float subTotal = paymentModel.getSubTotal();
        return String.format(Float.toString(subTotal), "EGP");
    }

    public String getTax() {
        float tax = paymentModel.getTax();
        return String.format(Float.toString(tax), "EGP");
    }

    public String getDeliveryFees() {
        float deliveryFees = paymentModel.getDeliveryFees();
        return String.format(Float.toString(deliveryFees), "EGP");
    }

    public String getTotal() {
        float total = paymentModel.getTotal();
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
        float subTotal = 0;
        for (int i = 0; i < orderItemsModels.size(); i++){
            model = orderItemsModels.get(i);
            subTotal += model.getTotalPrice();
        }
        paymentModel = new PaymentModel(subTotal, deliveryFees);
    }
}
