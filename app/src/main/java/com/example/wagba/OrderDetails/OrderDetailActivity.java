package com.example.wagba.OrderDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.wagba.model.OrderItem;
import com.example.wagba.databinding.ActivityOrderDetailBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class OrderDetailActivity extends AppCompatActivity {
    static final ArrayList<String> trackingStatus = new ArrayList<>(Arrays.asList(
            "Ordered",
            "Accepted",
            "Preparing",
            "Delivering",
            "Delivered"
    ));
    OrderDetailModel orderDetailModel;
    ActivityOrderDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        binding = ActivityOrderDetailBinding.inflate(inflater);
        View view = binding.getRoot();
        setContentView(view);
        init_data();
        populate_data();

    }

    public void init_data() {

        String restaurantName = "Food Corner";
        ArrayList<OrderItem> orderItems = new ArrayList<>();
//        orderItems.add(new OrderItem(
//                1,
//                "Crispy Crepe",
//                "Regular",
//                35.0f));
//
//        orderItems.add(new OrderItem(
//                2,
//                "Cheese Mix Crepe",
//                "Regular",
//                35.0f));
//
//        orderItems.add(new OrderItem(
//                1,
//                "Hot Dog Crepe",
//                "Regular",
//                35.0f));
//        orderItems.add(new OrderItem(
//                2,
//                "sus Crepe",
//                "Regular",
//                35.0f));
//
//        float deliveryFees = 0.5f;
//        String name = "Mostafa Lotfy";
//        String phoneNumber = "0111xxxxxxx";
//        String deliveryLocation = "Gate 3";


//        orderDetailModel = new OrderDetailModel(restaurantName,
//                deliveryFees, orderItems, name, phoneNumber, deliveryLocation);
    }

    public void populate_fields() {
    }

    public void populate_data() {
        binding.tvName.setText(orderDetailModel.getRestaurantName());
        OrderDetailItemAdapter adapter = new OrderDetailItemAdapter(
                orderDetailModel.getOrderItemsModels());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, layoutManager.getOrientation());

        binding.cvDetails.rcOrderDetails.setAdapter(adapter);
        binding.cvDetails.rcOrderDetails.setLayoutManager(layoutManager);
        binding.cvDetails.rcOrderDetails.addItemDecoration(dividerItemDecoration);

        binding.cvPayment.tvSubtotalAmount.setText(orderDetailModel.getSubTotal());
        binding.cvPayment.tvTotalAmount.setText(orderDetailModel.getTotal());
        binding.cvPayment.tvTaxAmount.setText(orderDetailModel.getTax());
        binding.cvPayment.tvDeliveryAmount.setText(orderDetailModel.getDeliveryFees());


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}