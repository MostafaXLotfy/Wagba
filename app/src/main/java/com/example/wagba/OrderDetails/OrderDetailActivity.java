package com.example.wagba.OrderDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.wagba.Basket.orderItems.OrderItemsModel;
import com.example.wagba.Basket.orderItems.OrdersItemsAdapter;
import com.example.wagba.databinding.ActivityOrderDetailBinding;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {
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
        String name = "Mostafa Lotfy";
        String phoneNumber = "0111xxxxxxx";
        String deliveryLocation = "Gate 3";


        orderDetailModel = new OrderDetailModel(restaurantName,
                deliveryFees, orderItemsModels,  name, phoneNumber, deliveryLocation);
    }

    public void populate_fields(){
    }
    public void populate_data(){
        binding.tvName.setText(orderDetailModel.getRestaurantName());
        OrderDetailItemAdapter adapter = new OrderDetailItemAdapter(
                orderDetailModel.getOrderItemsModels());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this,layoutManager.getOrientation());

        binding.rcOrderDetails.setAdapter(adapter);
        binding.rcOrderDetails.setLayoutManager(layoutManager);
        binding.rcOrderDetails.addItemDecoration(dividerItemDecoration);

        binding.tvSubtotalAmount.setText(orderDetailModel.getSubTotal());
        binding.tvTotalAmount.setText(orderDetailModel.getTotal());
        binding.tvTaxAmount.setText(orderDetailModel.getTax());
        binding.tvDeliveryAmount.setText(orderDetailModel.getDeliveryFees());

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}