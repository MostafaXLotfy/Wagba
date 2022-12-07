package com.example.wagba.Restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.wagba.Restaurant.Meals.MealsAdapter;
import com.example.wagba.Restaurant.Meals.MealsModel;
import com.example.wagba.databinding.ActivityRestaurantBinding;

import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity {
    private ActivityRestaurantBinding binding;
    RestaurantModel restaurantModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init_data();
        populate_layout();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());

        binding.rvMeals.addItemDecoration(dividerItemDecoration);

    }

    public void init_data(){
        ArrayList<MealsModel> mealsModels = new ArrayList<>();
        mealsModels.add(new MealsModel("Cheese Crepe", "Mozarilla, Keary", 30.0f, true));
        mealsModels.add(new MealsModel("Crispy Crepe", "Crispy, Mozarilla", 30.0f, true));
        mealsModels.add(new MealsModel("Hot Dog Crepe", "Hot Dog, Mozarilla", 30.0f, true));
        mealsModels.add(new MealsModel("Hot Dog Crepe", "Hot Dog, Mozarilla", 30.0f, true));
        mealsModels.add(new MealsModel("Hot Dog Crepe", "Hot Dog, Mozarilla", 30.0f, true));
        mealsModels.add(new MealsModel("Hot Dog Crepe", "Hot Dog, Mozarilla", 30.0f, true));
        restaurantModel = new RestaurantModel("Food Corner", "Crepe, Sandwiches", mealsModels);
    }

    public void populate_layout(){
        binding.tvName.setText(restaurantModel.getName());
        binding.tvDescription.setText(restaurantModel.getDescription());
        MealsAdapter adapter = new MealsAdapter(restaurantModel.getMealsModels());
        binding.rvMeals.setAdapter(adapter);
    }


}