package com.example.wagba.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wagba.model.MealsModel;
import com.example.wagba.model.RestaurantModel;
import com.example.wagba.repository.RestaurantsMealsRepository;

import java.util.List;

public class RestaurantViewModel extends AndroidViewModel {
    private static final String TAG = "RestaurantViewModel";
    private RestaurantsMealsRepository restaurantsMealsRepository;
    private LiveData<List<MealsModel>> _mealsModelLiveData;
    public RestaurantViewModel(@NonNull Application application) {
        super(application);
        restaurantsMealsRepository = new RestaurantsMealsRepository(application);
    }

    public LiveData<List<MealsModel>> getRestaurant(String restaurantID){
        if(_mealsModelLiveData == null){
            _mealsModelLiveData = restaurantsMealsRepository
                    .getRestaurant(restaurantID);
        }
        if(_mealsModelLiveData != null){
            Log.d(TAG, "getRestaurant: ");

        }
        return _mealsModelLiveData;
    }
}
