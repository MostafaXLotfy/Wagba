package com.example.wagba.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wagba.model.Basket;
import com.example.wagba.model.Order;
import com.example.wagba.model.OrderDetail;
import com.example.wagba.repository.BasketRepository;
import com.example.wagba.repository.OrderDetailRepository;
import com.example.wagba.repository.OrdersRepository;

import java.util.Objects;

public class BasketViewModel extends AndroidViewModel {
    private BasketRepository _basketRepository;
    private OrdersRepository _ordersRepository;
    private OrderDetailRepository _orderDetailRepository;
    public BasketViewModel(@NonNull Application application) {
        super(application);
        _basketRepository = new BasketRepository(application);
        _ordersRepository = new OrdersRepository(application);
        _orderDetailRepository = new OrderDetailRepository(application);
    }
    public LiveData<Basket> getBasket(){
        return _basketRepository.getBasket();
    }

    //todo:: think how to submit the user id
    public void submitOrder(String deliveryLocation){
        Basket basket = _basketRepository.getBasket().getValue();
        Order order = new Order(Objects.requireNonNull(basket));
        String id = _ordersRepository.submitOrder(order);
        OrderDetail orderDetail = new OrderDetail(id, basket, deliveryLocation);
        _orderDetailRepository.submitOrderDetails(orderDetail);
        _basketRepository.deleteBasket();
    }
}
