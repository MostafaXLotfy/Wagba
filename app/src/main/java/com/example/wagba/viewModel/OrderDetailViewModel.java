package com.example.wagba.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wagba.model.OrderDetail;
import com.example.wagba.repository.OrderDetailRepository;
import com.example.wagba.repository.RestaurantsRepository;

public class OrderDetailViewModel extends AndroidViewModel {
    private OrderDetailRepository _orderDetailRepository;
    private RestaurantsRepository _restaurantsRepository;

    public OrderDetailViewModel(@NonNull Application application) {
        super(application);
        _orderDetailRepository = new OrderDetailRepository(application);
        _restaurantsRepository = new RestaurantsRepository(application);
    }

    public LiveData<OrderDetail> getOrderDetails(String orderID) {
        LiveData<OrderDetail> orderDetailsLiveData =
                _orderDetailRepository.getOrderDetails(orderID);
        return orderDetailsLiveData;
    }


}
