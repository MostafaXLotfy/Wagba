package com.example.wagba.Basket.OrderDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wagba.databinding.FragmentOrderDetailsBinding;

import java.util.ArrayList;

public class OrderDetailsFragment extends Fragment {
    ArrayList<OrderDetailsModel> orderDetailsModels;

    FragmentOrderDetailsBinding binding;
    private static final String OrderDetailsModelsArg = "OrderDetailsModels";

    public OrderDetailsFragment() {
        // Required empty public constructor
    }

    public static OrderDetailsFragment newInstance(
            ArrayList<OrderDetailsModel> orderDetailsModels) {
        OrderDetailsFragment fragment = new OrderDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(OrderDetailsModelsArg, orderDetailsModels);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            orderDetailsModels = args.getParcelableArrayList(OrderDetailsModelsArg);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOrderDetailsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OrdersDetailsAdapter adapter = new OrdersDetailsAdapter(orderDetailsModels);
        binding.rcOrderDetails.setAdapter(adapter);
        binding.rcOrderDetails.setNestedScrollingEnabled(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}