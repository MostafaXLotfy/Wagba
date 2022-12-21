package com.example.wagba.service;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.wagba.model.Basket;
import com.example.wagba.model.OrderItem;
import com.example.wagba.model.Payment;
import com.example.wagba.model.Restaurant;

import java.util.List;

@Dao
public abstract class BasketDao {
    @Insert
    abstract void insertRestaurant(Restaurant restaurant);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void insertOrderItem(OrderItem orderItem);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertPayment(Payment payment);

    @Update
    public abstract void updatePayment(Payment payment);

    @Transaction
    public void insertBasket(Basket basket) {
        Restaurant restaurant = basket.getRestaurantModel();

        List<OrderItem> orderItems = basket.getOrderItems();
        Payment payment = basket.getPayment();

        String id = restaurant.getUid();

        insertRestaurant(restaurant);
        for (OrderItem orderItem : orderItems) {
            orderItem.setRestaurantID(id);
            insertOrderItem(orderItem);
        }
        payment.setUid("1");
        payment.setRestaurantID(id);
        insertPayment(payment);
    }

    @Query("SELECT * FROM RESTAURANT_TABLE LIMIT 1")
    abstract Restaurant getRestaurant();

    @Query("SELECT * FROM ORDER_ITEM_TABLE AS O where O.restaurantID =:restaurantID")
    abstract List<OrderItem> getOrderItems(String restaurantID);

    @Query("SELECT * FROM PAYMENT_TABLE AS P WHERE P.restaurantID =:restaurantID ")
    abstract Payment getPayment(String restaurantID);

    @Transaction
    public Basket getBasket(){
        Restaurant restaurant = getRestaurant();
        if(restaurant == null) return null;
        String restaurantID = restaurant.getUid();
        List<OrderItem> orderItems = getOrderItems(restaurantID);
        Payment payment = getPayment(restaurantID);
        return new Basket(restaurant, orderItems, payment);
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
