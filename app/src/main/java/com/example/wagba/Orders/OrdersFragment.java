package com.example.wagba.Orders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wagba.databinding.FragmentOrdersBinding;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrdersFragment extends Fragment {

    FragmentOrdersBinding binding;
    ArrayList<OrdersModel> ordersModels;
    public OrdersFragment() {
        // Required empty public constructor
    }

    public static OrdersFragment newInstance() {
        OrdersFragment fragment = new OrdersFragment();
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
        binding =  FragmentOrdersBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ordersModels = new ArrayList<>();
        ordersModels.add(new OrdersModel("Food Corner", 2, new Date(), 53.0f));
        ordersModels.add(new OrdersModel("Pizza Hut", 3, new Date(), 100.0f));
        ordersModels.add(new OrdersModel("Papa Jones", 2, new Date(), 120.0f));
        ordersModels.add(new OrdersModel("Karam El-Sham", 2, new Date(), 53.0f));
        OrdersAdapter ordersAdapter = new OrdersAdapter(ordersModels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(view.getContext(),layoutManager.getOrientation());

        binding.rcOrders.setAdapter(ordersAdapter);
        binding.rcOrders.setLayoutManager(layoutManager);
        binding.rcOrders.addItemDecoration(dividerItemDecoration);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}