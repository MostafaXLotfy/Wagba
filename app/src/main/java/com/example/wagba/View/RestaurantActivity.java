package com.example.wagba.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wagba.adapter.MealsAdapter;
import com.example.wagba.model.Meal;
import com.example.wagba.databinding.ActivityRestaurantBinding;
import com.example.wagba.model.Restaurant;
import com.example.wagba.utils.Constant;
import com.example.wagba.utils.ImageLoader;
import com.example.wagba.viewModel.RestaurantViewModel;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class RestaurantActivity extends AppCompatActivity {
    static private final String TAG = "RestaurantActivity";
    private ActivityRestaurantBinding binding;
    private RestaurantViewModel _restaurantViewModel;
    private MealsAdapter _adapter;
    private Restaurant _restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init_data();
        populateUi();
    }

    public void init_data(){
        _restaurantViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication()).create(RestaurantViewModel.class);
        _restaurant = getIntent().getParcelableExtra(Constant.RESTAURANT_DATA);

        LiveData<List<Meal>> mealsModelLiveData =
                _restaurantViewModel.getRestaurant(_restaurant.getUid());
        _adapter = new MealsAdapter(mealsModelLiveData.getValue(), _restaurant);
        mealsModelLiveData.observe(this, mealsModels -> {
            Log.d(TAG, "init_data: " + mealsModels.size());
            _adapter.notifyDataSetChanged();
        });
    }

    private void populateUi(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        binding.rvMeals.setAdapter(_adapter);
        binding.rvMeals.addItemDecoration(dividerItemDecoration);
        binding.tvName.setText(_restaurant.getName());
        binding.tvDescription.setText(_restaurant.getDescription());
        String logoPath = _restaurant.getLogo();
        Context context = binding.getRoot().getContext();
        ImageView imageView = binding.ivLogo;
        ImageLoader.load(context, imageView, logoPath);
    }
}