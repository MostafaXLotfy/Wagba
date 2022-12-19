package com.example.wagba.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wagba.model.Basket;
import com.example.wagba.repository.BasketRepository;

public class BasketViewModel extends AndroidViewModel {
    private BasketRepository _basketRepository;
    public BasketViewModel(@NonNull Application application) {
        super(application);
        _basketRepository = new BasketRepository(application);
    }
    public LiveData<Basket> getBasket(){
        return _basketRepository.getBasket();
    }
}
