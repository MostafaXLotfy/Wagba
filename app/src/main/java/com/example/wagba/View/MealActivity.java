package com.example.wagba.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.wagba.model.Meal;
import com.example.wagba.databinding.ActivityMealBinding;
import com.example.wagba.model.OrderItem;
import com.example.wagba.model.Restaurant;
import com.example.wagba.utils.BasketState;
import com.example.wagba.utils.Constant;
import com.example.wagba.viewModel.MealViewModel;

import java.text.MessageFormat;

public class MealActivity extends AppCompatActivity {
    private static final String TAG= "MealActivity";
    Meal meal;
    ActivityMealBinding binding;
    private MealViewModel _mealViewModel;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater inflater = getLayoutInflater();
        binding = ActivityMealBinding.inflate(inflater);
        View view = binding.getRoot();
        super.onCreate(savedInstanceState);
        setContentView(view);
        _mealViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication()).create(MealViewModel.class);
        initData();
        updateUI();
    }
    private void initData(){
        meal = getIntent().getParcelableExtra(Constant.MEAL_DATA);
    }
    private void updateUI(){
        binding.tvName.setText(meal.getName());
        binding.tvDescription.setText(meal.getDescription());
        binding.tvPrice.setText(MessageFormat.format("{0} EGP",
                Float.toString(meal.getPrice())));
        binding.btnAdd.setOnClickListener(view -> onIncrementQuantity());
        binding.btnRemove.setOnClickListener(view -> onDecrementQuantity());
        binding.btnAddToBasket.setOnClickListener(view -> onAddToBasket());
    }

    private void onIncrementQuantity(){
        int quantity =  getQuantity();
        quantity += 1;
        binding.tvQuantity.setText(MessageFormat.format("{0}", quantity));
    }

    private void onDecrementQuantity(){
        int quantity =  getQuantity();
        if(quantity == 1) return;
        quantity -= 1;
        binding.tvQuantity.setText(MessageFormat.format("{0}", quantity));
    }

    private String getSize(){
        int radioId = binding.rgSize.getCheckedRadioButtonId();
        RadioButton rb = binding.getRoot().findViewById(radioId);
        return rb == null ? null : rb.getText().toString();
    }

    private int getQuantity(){
        return Integer.parseInt(binding.tvQuantity.getText().toString());
    }

    private void onAddToBasket(){
        Restaurant restaurant = getIntent().getParcelableExtra(Constant.RESTAURANT_DATA);
        BasketState basketState = _mealViewModel.getBasketState(restaurant.getUid());
        Log.d(TAG, "onAddToBasket: " + restaurant.getUid());

        if(basketState == BasketState.EMPTY){
            _mealViewModel.newBasket(restaurant);
        }else if(basketState == BasketState.SAME_RESTAURANT){
            Log.d(TAG, "onAddToBasket: same rest");
        }else{
            Log.d(TAG, "onAddToBasket: not same rest");
            _mealViewModel.deleteBasket();
            _mealViewModel.newBasket(restaurant);
        }

        String size = this.getSize();
        if(size == null) {
            Toast.makeText(this, "you must chose the size",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        OrderItem orderItem = new OrderItem(meal.getName(), this.getQuantity(),
                this.getSize(), meal.getPrice());
        _mealViewModel.addItemToBasket(orderItem);
        finish();
    }

}