package com.example.wagba.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wagba.model.Meal;
import com.example.wagba.repository.RestaurantRepository;

import java.util.List;

public class RestaurantViewModel extends AndroidViewModel {
    private static final String TAG = "RestaurantViewModel";
    private RestaurantRepository restaurantRepository;
    private LiveData<List<Meal>> _mealsModelLiveData;
    public RestaurantViewModel(@NonNull Application application) {
        super(application);
        restaurantRepository = new RestaurantRepository(application);
    }

    public LiveData<List<Meal>> getRestaurant(String restaurantID){
        if(_mealsModelLiveData == null){
            _mealsModelLiveData = restaurantRepository
                    .getRestaurant(restaurantID);
        }
        if(_mealsModelLiveData != null){
            Log.d(TAG, "getRestaurant: ");

        }
        return _mealsModelLiveData;
    }
}
