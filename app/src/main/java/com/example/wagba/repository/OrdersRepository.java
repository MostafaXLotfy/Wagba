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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrdersRepository {
    private static final String TAG = "OrdersRepository";
    private static MutableLiveData<List<Order>> _ordersLiveData;
    private static List<Order> _orders;
    private DatabaseReference _ordersRef;
    private DatabaseReference _ordersToUsersRef;
    private Application _application;

    public OrdersRepository(Application application) {
        _application = application;
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        _ordersToUsersRef = db.getReference(Constant.ORDERS_TO_USERS_END_POINT).child(userID);
        _ordersRef = db.getReference(Constant.ORDERS_END_POINT);
        if (_orders == null) {
            _orders = new ArrayList<>();
            _ordersLiveData = new MutableLiveData<>(_orders);
            this.getAllOrdersFromFirebase();
        }
    }


    private void getAllOrdersFromFirebase() {
        _ordersToUsersRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String id = Objects.requireNonNull(snapshot.getKey());
                getOrderFromFirebase(id);
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

            }
        });

    }

    private void getOrderFromFirebase(@NonNull String id) {
        _ordersRef.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Order order = snapshot.getValue(Order.class);
                _orders.add(order);
                _ordersLiveData.setValue(_orders);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(_application, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public String submitOrder(Order order) {
        String id = Objects.requireNonNull(_ordersToUsersRef.push().getKey());
        _ordersToUsersRef.child(id)
                .setValue("");
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
