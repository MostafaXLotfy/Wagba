package com.example.wagba.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wagba.adapter.MyOrdersAdapter;
import com.example.wagba.databinding.FragmentMyOrdersBinding;
import com.example.wagba.model.Order;
import com.example.wagba.viewModel.OrdersViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrdersFragment extends Fragment {

    FragmentMyOrdersBinding binding;
    OrdersViewModel viewModel;
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
        viewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(requireActivity().getApplication()).create(OrdersViewModel.class);
        LiveData<List<Order>> ordersLiveData = viewModel.getAllOrders();
        MyOrdersAdapter myOrdersAdapter = new MyOrdersAdapter(ordersLiveData.getValue());
        ordersLiveData.observe(requireActivity(), orders -> {
            myOrdersAdapter.notifyDataSetChanged();
        });
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