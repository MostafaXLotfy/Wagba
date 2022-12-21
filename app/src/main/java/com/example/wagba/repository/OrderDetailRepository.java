package com.example.wagba.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wagba.model.OrderDetail;
import com.example.wagba.model.Restaurant;
import com.example.wagba.utils.Constant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderDetailRepository {
    private Application _application;
    private DatabaseReference _ordersDetailsRef;
    private DatabaseReference _restaurantsRef;
    public OrderDetailRepository(Application application){
        _application = application;
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        _ordersDetailsRef = db.getReference(Constant.ORDERS_DETAILS_END_POINT)
                .child(userID);
        _restaurantsRef = db.getReference(Constant.RESTAURANTS_END_POINT);
    }

    public void submitOrderDetails(OrderDetail orderDetail){
        _ordersDetailsRef.child(orderDetail.getUid()).setValue(orderDetail);
    }

    public LiveData<OrderDetail> getOrderDetails(String id){
        MutableLiveData<OrderDetail> orderDetailMutableLiveData =
                new MutableLiveData<>();
        _ordersDetailsRef.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                OrderDetail orderDetail = snapshot.getValue(OrderDetail.class);
                String restaurantId = snapshot.child(Constant.RESTAURANT_ID).getValue(String.class);
                _restaurantsRef.child(restaurantId)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Restaurant restaurant = snapshot.getValue(Restaurant.class);
                                orderDetail.setRestaurant(restaurant);
                                orderDetailMutableLiveData.setValue(orderDetail);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(_application, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return orderDetailMutableLiveData;
    }
}
