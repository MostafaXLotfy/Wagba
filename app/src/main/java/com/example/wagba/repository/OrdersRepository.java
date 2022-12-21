package com.example.wagba.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wagba.model.Order;
import com.example.wagba.utils.Constant;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class OrdersRepository {
    private static final String TAG = "OrdersRepository";
    private static MutableLiveData<List<Order>> _ordersLiveData;
    private static List<Order> _orders;
    private DatabaseReference _ordersRef;
    private Application _application;

    public OrdersRepository(Application application) {
        _application = application;
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        _ordersRef = db.getReference(Constant.ORDERS_END_POINT).child(userID);
        if(_orders == null){
            getData();
        }
    }

    //todo:: change this because it will go boom

    private void getData(){
        _orders = new ArrayList<>();
        _ordersLiveData = new MutableLiveData<>(_orders);
        _ordersRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Order order = snapshot.getValue(Order.class);
                _orders.add(order);
               _ordersLiveData.setValue(_orders);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(_application, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String submitOrder(Order order){
        String id = _ordersRef.push().getKey();
        order.setUid(id);
        _ordersRef.child(id).setValue(order).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(_application, e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return id;
    }

    public LiveData<List<Order>> getAllOrders() {
        return _ordersLiveData;
    }

}
