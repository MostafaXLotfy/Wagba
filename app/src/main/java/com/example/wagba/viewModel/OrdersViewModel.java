package com.example.wagba.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wagba.model.Order;
import com.example.wagba.repository.OrdersRepository;

import java.util.List;

public class OrdersViewModel extends AndroidViewModel {
    private static final String TAG = "ORDERS_VIEW_MODEL";
    private OrdersRepository _ordersRepository;
    private LiveData<List<Order>> _ordersLiveData;
    public OrdersViewModel(@NonNull Application application) {
        super(application);
        _ordersRepository = new OrdersRepository(application);
        _ordersLiveData = _ordersRepository.getAllOrders();
        Log.d(TAG, "OrdersViewModel: ");
        
    }

    public LiveData<List<Order>> getAllOrders(){
        return this._ordersLiveData;
    }
}
