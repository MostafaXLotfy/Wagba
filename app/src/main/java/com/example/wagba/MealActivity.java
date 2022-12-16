package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.wagba.model.MealsModel;
import com.example.wagba.databinding.ActivityMealBinding;

public class MealActivity extends AppCompatActivity {
    MealsModel mealsModel;
    ActivityMealBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater inflater = getLayoutInflater();
        binding = ActivityMealBinding.inflate(inflater);
        View view = binding.getRoot();
        super.onCreate(savedInstanceState);
        setContentView(view);
        init_data();
        populate_data();
    }
    public void init_data(){
        mealsModel = new MealsModel("Cheese Crepe", "Mozarilla, kery", 34, false);
    }
    public void populate_data(){
        binding.tvName.setText(mealsModel.getName());
        binding.tvDescription.setText(mealsModel.getDescription());
        binding.tvPrice.setText(mealsModel.getPrice());
    }

}