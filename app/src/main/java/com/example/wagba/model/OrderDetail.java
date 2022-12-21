package com.example.wagba.model;

import com.example.wagba.utils.Constant;
import com.example.wagba.utils.OrderStatus;
import com.google.firebase.database.PropertyName;

import java.util.List;

public class OrderDetail {
    @PropertyName(Constant.ORDER_ID)
    private String uid;
    private List<OrderItem> orderItems;
    private Restaurant restaurant;
    private Payment payment;
    private String deliveryLocation;
    private OrderStatus status;



    public OrderDetail(){
    }

    public OrderDetail(String id, Basket basket, String deliveryLocation){
        uid = id;
        restaurant = basket.getRestaurantModel();
        payment = basket.getPayment();
        orderItems = basket.getOrderItems();
        this.deliveryLocation = deliveryLocation;
        status = OrderStatus.Ordered;
    }


    public String getRestaurantID() {
        return restaurant.getUid();
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    public String getRestaurantName() {
        return restaurant.getName();
    }

    public float getSubTotal() {
        return payment.getSubTotal();
    }

    public float getTotal() {
        return payment.getTotal();
    }

    public float getTax() {
        return payment.getTax();
    }

    public float getDeliveryFees() {
        return payment.getDeliveryFees();
    }
}
