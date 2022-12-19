package com.example.wagba.model;
import androidx.room.Embedded;
import androidx.room.Relation;


import java.util.ArrayList;
import java.util.List;

public class Basket {
    @Embedded
    private RestaurantModel restaurantModel;

    @Relation(
            parentColumn = "restaurantID",
            entityColumn = "orderItemID",
            entity = OrderItem.class
    )
//    @Ignore
    private List<OrderItem> orderItems;

    @Relation(
            parentColumn = "restaurantID",
            entityColumn = "paymentID",
            entity = Payment.class
    )
    private Payment payment;



    public String getRestaurantName() {
        return restaurantModel.getName();
    }

    public List<OrderItem> getOrderItemsModels() {
        return orderItems;
    }

    public Basket(){

    }

    public Basket(RestaurantModel restaurantModel, List<OrderItem> orderItems, Payment payment){
        this.restaurantModel = restaurantModel;
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

    public RestaurantModel getRestaurantModel() {
        return restaurantModel;
    }

    public Payment getPayment() {
        return payment;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
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

    public void setRestaurantModel(RestaurantModel restaurantModel) {
        this.restaurantModel = restaurantModel;
        this.payment = new Payment((float) this.restaurantModel.getDeliveryFees());
//        this.payment.setUid("1");
        this.orderItems = new ArrayList<>();
    }


    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void addOrderItem(OrderItem orderItem){
        orderItem.setUid(orderItems.size());
        orderItem.setRestaurantID(restaurantModel.getUid());
        this.orderItems.add(orderItem);
        this.calculatePayment(orderItem);
    }

    private void calculatePayment(OrderItem orderItem){
        this.payment.calculateItemPrice(orderItem);
    }
}
