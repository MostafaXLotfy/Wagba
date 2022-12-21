package com.example.wagba.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wagba.model.Restaurant;
import com.example.wagba.utils.Constant;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantsRepository {
    private static final String  TAG = "RestaurantsRepository";
    private DatabaseReference restaurantsRef;
    private List<Restaurant> _allRestaurants;
    private MutableLiveData<List<Restaurant>> _allRestaurantsLiveData;
    private Application _application;

    public RestaurantsRepository(Application application) {
        _application = application;
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
                Restaurant restaurant = snapshot.getValue(Restaurant.class);
                _allRestaurants.add(restaurant);
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
                Toast.makeText(_application, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public LiveData<List<Restaurant>> get_allRestaurants() {
        return _allRestaurantsLiveData;
    }

    public LiveData<Restaurant> getRestaurant(String restaurantID){
        MutableLiveData<Restaurant> restaurantMutableLiveData = new MutableLiveData<>();
        restaurantsRef.child(restaurantID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                restaurantMutableLiveData.setValue(snapshot.getValue(Restaurant.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(_application, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return restaurantMutableLiveData;
    }

}
