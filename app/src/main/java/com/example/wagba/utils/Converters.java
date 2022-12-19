package com.example.wagba.utils;

import androidx.room.TypeConverter;

import com.example.wagba.model.OrderItem;
import com.example.wagba.model.Payment;
import com.example.wagba.model.RestaurantModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters {
    @TypeConverter
    public List<OrderItem> ordersFromString(String value) {
        Type listType = new TypeToken<List<OrderItem>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public RestaurantModel restaurantFromString(String value){
        return new Gson().fromJson(value, RestaurantModel.class);
    }

    @TypeConverter
    public Payment paymentFromString(String value){
        return new Gson().fromJson(value, Payment.class);
    }

    @TypeConverter
    public String stringFromOrders(ArrayList<OrderItem> list) {
        return new Gson().toJson(list);
    }

    @TypeConverter
    public String stringFromRestaurant(RestaurantModel restaurantModel) {
        return new Gson().toJson(restaurantModel);
    }

    @TypeConverter
    public String stringFromPayment(Payment payment) {
        return new Gson().toJson(payment);
    }
}
