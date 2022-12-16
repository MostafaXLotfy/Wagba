package com.example.wagba.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wagba.model.RestaurantModel;
import com.example.wagba.utils.Constant;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsRepository {
    private static final String  TAG = "RestaurantsRepository";
    private DatabaseReference restaurantsRef;
    private List<RestaurantModel> _allRestaurants;
    private MutableLiveData<List<RestaurantModel>> _allRestaurantsLiveData;

    public RestaurantsRepository(Application application) {
        restaurantsRef = FirebaseDatabase.getInstance().
                getReference(Constant.RESTAURANTS_END_POINT);
        _allRestaurants = new ArrayList<>();
        _allRestaurantsLiveData = new MutableLiveData<>(_allRestaurants);
        getData();
    }

    private void getData() {
        restaurantsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot,
                                     @Nullable String previousChildName) {
                _allRestaurants.add(snapshot.getValue(RestaurantModel.class));
                _allRestaurantsLiveData.setValue(_allRestaurants);
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

    public LiveData<List<RestaurantModel>> SearchRestaurant(String searchQuery) {
        return _allRestaurantsLiveData;
    }

    public LiveData<List<RestaurantModel>> get_allRestaurants() {
        return _allRestaurantsLiveData;
    }

}
