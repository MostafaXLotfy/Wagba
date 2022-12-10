package com.example.wagba.Basket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.wagba.Basket.orderItems.OrderItemsModel;
import com.example.wagba.Basket.orderItems.OrdersItemsAdapter;
import com.example.wagba.MainActivity;
import com.example.wagba.databinding.FragmentBasketBinding;

import java.util.ArrayList;

public class BasketFragment extends Fragment {

    private FragmentBasketBinding binding;
    private MainActivity activity;
    BasketModel basketModel;

    public BasketFragment() {
        // Required empty public constructor
    }

    public static BasketFragment newInstance() {
        BasketFragment fragment = new BasketFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        init_data();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBasketBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = (MainActivity) getActivity();
        populate_data();

    }

    public void init_data(){

        String restaurantName = "Food Corner";
        ArrayList<OrderItemsModel> orderItemsModels = new ArrayList<>();
        orderItemsModels.add(new OrderItemsModel(
                1,
                "Crispy Crepe",
                "Regular",
                35.0f));

        orderItemsModels.add(new OrderItemsModel(
                2,
                "Cheese Mix Crepe",
                "Regular",
                35.0f));

        orderItemsModels.add(new OrderItemsModel(
                1,
                "Hot Dog Crepe",
                "Regular",
                35.0f));
        orderItemsModels.add(new OrderItemsModel(
                2,
                "sus Crepe",
                "Regular",
                35.0f));

        float deliveryFees = 0.5f;
        basketModel = new BasketModel(restaurantName, deliveryFees, orderItemsModels);

    }
    public void populate_data(){
        binding.tvName.setText(basketModel.getRestaurantName());
        OrdersItemsAdapter adapter =
                new OrdersItemsAdapter(basketModel.getOrderItemsModels());
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(activity,layoutManager.getOrientation());

        binding.rcOrderDetails.setAdapter(adapter);
        binding.rcOrderDetails.setLayoutManager(layoutManager);
        binding.rcOrderDetails.addItemDecoration(dividerItemDecoration);
        binding.cvPayment.tvSubtotalAmount.setText(basketModel.getSubTotal());
        binding.cvPayment.tvTotalAmount.setText(basketModel.getTotal());
        binding.cvPayment.tvTaxAmount.setText(basketModel.getTax());
        binding.cvPayment.tvDeliveryAmount.setText(basketModel.getDeliveryFees());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}