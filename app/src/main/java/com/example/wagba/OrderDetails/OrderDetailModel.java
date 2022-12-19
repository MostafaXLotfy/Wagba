package com.example.wagba.OrderDetails;

import com.example.wagba.model.OrderItem;
import com.example.wagba.model.Payment;

import java.util.ArrayList;

public class OrderDetailModel {
    private String RestaurantName;
    private ArrayList<OrderItem> orderItems;
    private Payment payment;

    private String deliveryLocation;
    private String Name;
    private String phoneNumber;


    public String getRestaurantName() {
        return RestaurantName;
    }

    public ArrayList<OrderItem> getOrderItemsModels() {
        return orderItems;
    }


    public String getSubTotal() {
        float subTotal = payment.getSubTotal();
        return String.format(Float.toString(subTotal), "EGP");
    }

    public String getTax() {
        float tax = payment.getTax();
        return String.format(Float.toString(tax), "EGP");
    }

    public String getDeliveryFees() {
        float deliveryFees = payment.getDeliveryFees();
        return String.format(Float.toString(deliveryFees), "EGP");
    }

    public String getTotal() {
        float total = payment.getTotal();
        return String.format(Float.toString(total), "EGP");
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }


    public String getName() {
        return Name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public OrderDetailModel(
            String restaurantName,
            float deliveryFees,
            ArrayList<OrderItem> orderItems,
            String Name,
            String PhoneNumber,
            String deliveryLocation
    ) {
        RestaurantName = restaurantName;
        this.orderItems = orderItems;
        OrderItem model;
        float subTotal = 0;
        for (int i = 0; i < orderItems.size(); i++){
            model = orderItems.get(i);
            subTotal += model.getTotalPrice();
        }
        payment = new Payment(subTotal, deliveryFees);
        this.Name = Name;
        this.deliveryLocation = deliveryLocation;
        this.phoneNumber = PhoneNumber;

    }
}
