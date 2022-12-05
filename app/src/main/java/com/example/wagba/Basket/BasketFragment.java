package com.example.wagba.Basket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.wagba.Basket.OrderDetails.OrderDetailsFragment;
import com.example.wagba.Basket.OrderDetails.OrderDetailsModel;
import com.example.wagba.Basket.OrderRestaurant.OrderRestaurantFragment;
import com.example.wagba.Basket.PaymentDetail.PaymentDetailFragment;
import com.example.wagba.Basket.PaymentDetail.PaymentDetailModel;
import com.example.wagba.MainActivity;
import com.example.wagba.R;
import com.example.wagba.databinding.FragmentBasketBinding;

import java.util.ArrayList;

public class BasketFragment extends Fragment {

    private FragmentBasketBinding binding;
    private MainActivity activity;
    private String restaurantName;
    private ArrayList<OrderDetailsModel> orderDetailsModels;
    private PaymentDetailModel paymentDetailModel;

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
        restaurantName = "Food Corner";
        orderDetailsModels = new ArrayList<>();
        orderDetailsModels.add(new OrderDetailsModel(
                1,
                "Crispy Crepe",
                "Regular",
                35.0f));

        orderDetailsModels.add(new OrderDetailsModel(
                2,
                "Cheese Mix Crepe",
                "Regular",
                35.0f));

        orderDetailsModels.add(new OrderDetailsModel(
                1,
                "Hot Dog Crepe",
                "Regular",
                35.0f));
        orderDetailsModels.add(new OrderDetailsModel(
                2,
                "sus Crepe",
                "Regular",
                35.0f));

        paymentDetailModel = new PaymentDetailModel(100.0f, 10.0f);



        OrderRestaurantFragment orderRestaurantFragment =
                OrderRestaurantFragment.newInstance(restaurantName);
        OrderDetailsFragment orderDetailsFragment = OrderDetailsFragment.newInstance(orderDetailsModels);
        PaymentDetailFragment paymentDetailFragment = PaymentDetailFragment.newInstance(paymentDetailModel);

        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.frag_order_restaurant, orderRestaurantFragment);
        ft.replace(R.id.frag_order_details, orderDetailsFragment);
        ft.replace(R.id.frag_payment_details, paymentDetailFragment);
        ft.commit();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}