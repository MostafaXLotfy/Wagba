package com.example.wagba.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.wagba.adapter.MealsAdapter;
import com.example.wagba.model.MealsModel;
import com.example.wagba.databinding.ActivityRestaurantBinding;
import com.example.wagba.model.RestaurantModel;
import com.example.wagba.utils.Constant;
import com.example.wagba.viewModel.RestaurantViewModel;

import java.util.List;

public class RestaurantActivity extends AppCompatActivity {
    static private String TAG = "RestaurantActivity";
    private ActivityRestaurantBinding binding;
    private RestaurantViewModel _restaurantViewModel;
    private MealsAdapter _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        _restaurantViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication()).create(RestaurantViewModel.class);
        init_data();
        populate_layout();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());

        binding.rvMeals.setAdapter(_adapter);
        binding.rvMeals.addItemDecoration(dividerItemDecoration);

    }

    public void init_data(){
        RestaurantModel restaurantModel = getIntent().getParcelableExtra(Constant.RESTAURANT_DATA);

        LiveData<List<MealsModel>> mealsModelLiveData =
                _restaurantViewModel.getRestaurant(restaurantModel.getUid());
        _adapter = new MealsAdapter(mealsModelLiveData.getValue());
        mealsModelLiveData.observe(this, mealsModels -> {
            _adapter.notifyDataSetChanged();
        });
        binding.tvName.setText(restaurantModel.getName());
        binding.tvDescription.setText(restaurantModel.getDescription());
    }

    public void populate_layout(){
    }


}