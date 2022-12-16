package com.example.wagba.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wagba.model.MealsModel;
import com.example.wagba.model.RestaurantModel;
import com.example.wagba.utils.Constant;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RestaurantsMealsRepository {
    private static final String TAG = "RestaurantsMealsRepo";
    DatabaseReference mealRef;
    List<MealsModel> _restaurantsMeals;
    MutableLiveData<List<MealsModel>> _mealsModelLiveData;

    public RestaurantsMealsRepository(Application application){
    }

    private void getData(String restaurantID){
        _restaurantsMeals = new ArrayList<>();
        _mealsModelLiveData = new MutableLiveData<>(_restaurantsMeals);
        mealRef = FirebaseDatabase.getInstance()
                .getReference(Constant.MEALS_END_POINT).child(restaurantID);

        mealRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                _restaurantsMeals.add(snapshot.getValue(MealsModel.class));
                _mealsModelLiveData.setValue(_restaurantsMeals);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //todo:: implement available
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

    public LiveData<List<MealsModel>> getRestaurant(String restaurantID){
        if(_mealsModelLiveData == null) {
            getData(restaurantID);
        }
        return _mealsModelLiveData;
    }

}
