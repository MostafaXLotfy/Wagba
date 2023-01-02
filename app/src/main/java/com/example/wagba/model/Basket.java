package com.example.wagba.model;
import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;


import java.util.ArrayList;
import java.util.List;

public class Basket {
    @Embedded
    private Restaurant restaurant;

    @Relation(
            parentColumn = "restaurantID",
            entityColumn = "orderItemID",
            entity = OrderItem.class
    )
    private List<OrderItem> orderItems;

    @Relation(
            parentColumn = "restaurantID",
            entityColumn = "paymentID",
            entity = Payment.class
    )
    private Payment payment;
    @Ignore
    private int quantity;



    public String getRestaurantName() {
        return restaurant.getName();
    }

    public List<OrderItem> getOrderItemsModels() {
        return orderItems;
    }


    public Basket(){

    }

    public Basket(Restaurant restaurant, List<OrderItem> orderItems, Payment payment){
        this.restaurant = restaurant;
        this.orderItems = orderItems;
        this.payment = payment;
    }

    public float getSubTotal() {
        return payment.getSubTotal();
    }

    public float getTax() {
        return payment.getTax();
    }

    public float getDeliveryFees() {
        return payment.getDeliveryFees();
    }

    public float getTotal() {
        return payment.getTotal();
    }

    public Restaurant getRestaurantModel() {
        return restaurant;
    }

    public Payment getPayment() {
        return payment;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
        for(OrderItem orderItem: orderItems){
            calculatePayment(orderItem);
        }
    }

    public void setPaymentModel(Payment payment) {
        this.payment = payment;
    }

    public void setRestaurantModel(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.payment = new Payment((float) this.restaurant.getDeliveryFees());
        this.orderItems = new ArrayList<>();
    }


    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void addOrderItem(OrderItem orderItem){
        orderItem.setUid(Integer.toString(orderItems.size() + 1));
        orderItem.setRestaurantID(restaurant.getUid());
        this.orderItems.add(orderItem);
        this.calculatePayment(orderItem);
        this.quantity += orderItem.getQuantity();
    }

    private void calculatePayment(OrderItem orderItem){
        this.payment.calculateItemPrice(orderItem);
    }

    public String getLogo(){
        return restaurant.getLogo();
    }

}
