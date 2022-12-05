package com.example.wagba.Orders;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class OrdersModel {
    private String restaurantName;
    private int quantity;
    private Date date;
    private Float price;

    public OrdersModel(String restaurantName, int quantity, Date date, Float price) {
        this.restaurantName = restaurantName;
        this.quantity = quantity;
        this.date = date;
        this.price = price;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getQuantity() {
        return String.format(Integer.toString(quantity), "delivered");
    }

    public String getDate() {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ENGLISH);
        return dateFormat.format(date);
    }

    public String getPrice() {
        return String.format(Float.toString(price), "EGP");
    }
}
