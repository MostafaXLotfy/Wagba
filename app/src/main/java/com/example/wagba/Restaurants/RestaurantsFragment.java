package com.example.wagba.Restaurants;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wagba.databinding.FragmentRestaurantsBinding;

import java.util.ArrayList;

public class RestaurantsFragment extends Fragment {
    FragmentRestaurantsBinding binding;
    ArrayList<RestaurantsModel> restaurantsModels;
    public RestaurantsFragment() {
        // Required empty public constructor
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
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        restaurantsModels = new ArrayList<>();
        restaurantsModels.add(new RestaurantsModel("Pizza Hut", "pizza"));
        restaurantsModels.add(new RestaurantsModel("Papa Jones", "pizza"));
        restaurantsModels.add(new RestaurantsModel("Karm El-Sham", "Shawerma"));
        restaurantsModels.add(new RestaurantsModel("Food Corner", "Crepe"));
        RestaurantsAdapter adapter = new RestaurantsAdapter(restaurantsModels);
        binding.rvRestaurants.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}