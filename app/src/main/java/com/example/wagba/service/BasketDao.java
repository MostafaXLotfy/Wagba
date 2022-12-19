package com.example.wagba.service;

import androidx.annotation.TransitionRes;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.DeleteTable;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.wagba.model.Basket;
import com.example.wagba.model.OrderItem;
import com.example.wagba.model.Payment;
import com.example.wagba.model.RestaurantModel;

import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class BasketDao {
    @Insert
    abstract void insertRestaurant(RestaurantModel restaurantModel);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void insertOrderItem(OrderItem orderItem);


    @Insert
    abstract void insertPayment(Payment payment);

    @Update
    public abstract void updatePayment(Payment payment);

    @Transaction
    public void insertBasket(Basket basket) {
        RestaurantModel restaurantModel = basket.getRestaurantModel();

        List<OrderItem> orderItems = basket.getOrderItems();
        Payment payment = basket.getPayment();

        int id = restaurantModel.getUid();

        insertRestaurant(restaurantModel);
        for (OrderItem orderItem : orderItems) {
            orderItem.setRestaurantID(id);
            insertOrderItem(orderItem);
        }
        payment.setRestaurantID(id);
        insertPayment(payment);
    }

    @Query("SELECT * FROM RESTAURANT_TABLE LIMIT 1")
    abstract RestaurantModel getRestaurant();

    @Query("SELECT * FROM ORDER_ITEM_TABLE AS O where O.restaurantID =:restaurantID")
    abstract List<OrderItem> getOrderItems(int restaurantID);

    @Query("SELECT * FROM PAYMENT_TABLE AS P WHERE P.restaurantID =:restaurantID ")
    abstract Payment getPayment(int restaurantID);

    @Transaction
    public Basket getBasket(){
        RestaurantModel restaurantModel = getRestaurant();
        if(restaurantModel == null) return null;
        int restaurantID = restaurantModel.getUid();
        List<OrderItem> orderItems = getOrderItems(restaurantID);
        Payment payment = getPayment(restaurantID);
        return new Basket(restaurantModel, orderItems, payment);
    };



    @Transaction
    public void deleteBasket(){
        deleteOrders();
        deleteRestaurant();
        deletePayment();
    }
    @Query("DELETE FROM RESTAURANT_TABLE")
    abstract void deleteRestaurant();

    @Query("DELETE FROM PAYMENT_TABLE")
    abstract void deletePayment();

    @Query("DELETE FROM ORDER_ITEM_TABLE")
    abstract void deleteOrders();



}
