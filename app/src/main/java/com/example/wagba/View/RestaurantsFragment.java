package com.example.wagba.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.wagba.adapter.RestaurantsAdapter;
import com.example.wagba.model.Restaurant;
import com.example.wagba.viewModel.RestaurantsViewModel;
import com.example.wagba.databinding.FragmentRestaurantsBinding;

import java.util.List;

public class RestaurantsFragment extends Fragment {
    private static final String TAG = "Restaurants Fragment";
    LiveData<List<Restaurant>> _allRestaurantsLiveData;
    private FragmentRestaurantsBinding binding;
    private RestaurantsViewModel restaurantsViewModel;
    private RestaurantsAdapter _adapter;

    public RestaurantsFragment() {
    }

    public static RestaurantsFragment newInstance() {
        RestaurantsFragment fragment = new RestaurantsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRestaurantsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        Log.d(TAG, "onCreateView: ");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        restaurantsViewModel = new ViewModelProvider(requireActivity())
                .get(RestaurantsViewModel.class);
        _allRestaurantsLiveData =
                restaurantsViewModel.getAllRestaurants();
        _adapter = new RestaurantsAdapter(_allRestaurantsLiveData.getValue());
        Log.d(TAG, "onCreate: here");
        _allRestaurantsLiveData.observe(requireActivity(), restaurantModels -> {
            _adapter.notifyDataSetChanged();
        });
        binding.rvRestaurants.setAdapter(_adapter);
        Log.d(TAG, "onViewCreated: ");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        Log.d(TAG, "onDestroy: ");
    }
}