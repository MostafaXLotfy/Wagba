package com.example.wagba.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wagba.model.Order;
import com.example.wagba.model.RestaurantModel;
import com.example.wagba.utils.Constant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrdersRepository {
    private static final String TAG = "OrdersRepository";
    private MutableLiveData<List<Order>> _ordersLiveData;
    private List<Order> _orders;
    private DatabaseReference _ordersRef;
    private DatabaseReference _restaurantsRef;

    public OrdersRepository(Application application) {
        getData();
    }

    private void getData() {
        _ordersRef = FirebaseDatabase
                .getInstance().getReference(Constant.ORDERS_END_POINT);
        _restaurantsRef = FirebaseDatabase
                .getInstance().getReference(Constant.RESTAURANTS_END_POINT);
        _orders = new ArrayList<>();
        List<String> restaurantsIDs = new ArrayList<>();
        _ordersLiveData = new MutableLiveData<>(_orders);

        _ordersRef.get().addOnSuccessListener(dataSnapshot -> {
            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                _orders.add(ds.getValue(Order.class));
                restaurantsIDs.add(ds.child(Constant.RESTAURANT_ID).getValue(String.class));
            }
            for (int i = 0; i < _orders.size(); i++) {
                int finalI = i;
                _restaurantsRef.child(restaurantsIDs.get(i))
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                RestaurantModel restaurantModel = snapshot.getValue(RestaurantModel.class);
                                _orders.get(finalI).setRestaurantName(restaurantModel.getName());
                                _ordersLiveData.setValue(_orders);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
            }
            _ordersLiveData.setValue(_orders);
            Log.d(TAG, "getData: ");
        });
    }

    public LiveData<List<Order>> getAllOrders() {
        return _ordersLiveData;
    }

}
