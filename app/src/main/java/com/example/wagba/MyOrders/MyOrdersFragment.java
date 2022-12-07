package com.example.wagba.MyOrders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wagba.databinding.FragmentMyOrdersBinding;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyOrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyOrdersFragment extends Fragment {

    FragmentMyOrdersBinding binding;
    ArrayList<MyOrdersModel> myOrdersModels;
    public MyOrdersFragment() {
        // Required empty public constructor
    }

    public static MyOrdersFragment newInstance() {
        MyOrdersFragment fragment = new MyOrdersFragment();
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
        binding =  FragmentMyOrdersBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myOrdersModels = new ArrayList<>();
        myOrdersModels.add(new MyOrdersModel("Food Corner", 2, new Date(), 53.0f));
        myOrdersModels.add(new MyOrdersModel("Pizza Hut", 3, new Date(), 100.0f));
        myOrdersModels.add(new MyOrdersModel("Papa Jones", 2, new Date(), 120.0f));
        myOrdersModels.add(new MyOrdersModel("Karam El-Sham", 2, new Date(), 53.0f));
        MyOrdersAdapter myOrdersAdapter = new MyOrdersAdapter(myOrdersModels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(view.getContext(),layoutManager.getOrientation());

        binding.rcOrders.setAdapter(myOrdersAdapter);
        binding.rcOrders.setLayoutManager(layoutManager);
        binding.rcOrders.addItemDecoration(dividerItemDecoration);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}