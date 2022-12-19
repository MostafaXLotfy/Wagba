package com.example.wagba.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.wagba.model.OrderItem;
import com.example.wagba.model.RestaurantModel;
import com.example.wagba.repository.BasketRepository;
import com.example.wagba.utils.BasketState;

public class MealViewModel extends AndroidViewModel {
    private BasketRepository _basketRepository;


    public MealViewModel(Application application){
        super(application);
        _basketRepository = new BasketRepository(application);
    }

    public void newBasket(RestaurantModel restaurantModel){
        _basketRepository.newBasket(restaurantModel);
    }

    public void deleteBasket(){
        _basketRepository.deleteBasket();
    }

    public void addItemToBasket(OrderItem orderItem){
        _basketRepository.addOrderItem(orderItem);
    }

    public BasketState getBasketState(int restaurantID){
        return  _basketRepository.getBasketStatus(restaurantID);
    }
}
