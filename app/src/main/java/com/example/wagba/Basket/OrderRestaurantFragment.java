package com.example.wagba.Basket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.wagba.databinding.FragmentOrderRestaurantBinding;

public class OrderRestaurantFragment extends Fragment {
    FragmentOrderRestaurantBinding binding;

    public OrderRestaurantFragment() {
        // Required empty public constructor
    }

    public static OrderRestaurantFragment newInstance() {
        OrderRestaurantFragment fragment = new OrderRestaurantFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderRestaurantBinding.inflate(inflater, container,
                false);
        View view = binding.getRoot();
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}