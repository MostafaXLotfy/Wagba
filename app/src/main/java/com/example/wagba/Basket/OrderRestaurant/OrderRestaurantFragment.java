package com.example.wagba.Basket.OrderRestaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wagba.databinding.FragmentOrderRestaurantBinding;

public class OrderRestaurantFragment extends Fragment {
    FragmentOrderRestaurantBinding binding;
    private final static String restaurantNameArg = "restaurantNameArg";
    private String restaurantName;

    public OrderRestaurantFragment() {
        // Required empty public constructor
    }

    public static OrderRestaurantFragment newInstance(String restaurantName) {
        OrderRestaurantFragment fragment = new OrderRestaurantFragment();
        Bundle args = new Bundle();
        args.putString(restaurantNameArg, restaurantName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            restaurantName = args.getString(restaurantName);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderRestaurantBinding.inflate(inflater, container,
                false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvName.setText(restaurantName);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}