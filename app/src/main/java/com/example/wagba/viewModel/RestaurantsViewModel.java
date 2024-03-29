package com.example.wagba.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wagba.model.Restaurant;
import com.example.wagba.repository.RestaurantsRepository;

import java.util.List;

public class RestaurantsViewModel extends AndroidViewModel {
    private LiveData<List<Restaurant>> _allRestaurants;
    private RestaurantsRepository restaurantsRepository;
    public RestaurantsViewModel(@NonNull Application application) {
        super(application);
         restaurantsRepository = new RestaurantsRepository(application);
        _allRestaurants = restaurantsRepository.get_allRestaurants();
    }

    public LiveData<List<Restaurant>> getAllRestaurants(){
        return this._allRestaurants;
    }
}
