package com.example.wagba.OrderDetails;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.wagba.model.Order;
import com.example.wagba.model.OrderDetail;
import com.example.wagba.databinding.ActivityOrderDetailBinding;
import com.example.wagba.utils.Constant;
import com.example.wagba.utils.OrderStatus;
import com.example.wagba.viewModel.OrderDetailViewModel;

import moe.feng.common.stepperview.VerticalStepperItemView;


public class OrderDetailActivity extends AppCompatActivity {
    private final static String TAG = "OrderDetailActivity";
    private VerticalStepperItemView mSteppers[];
    private ActivityOrderDetailBinding binding;
    private OrderDetailViewModel _orderDetailViewModel;
    private OrderDetailItemAdapter _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        binding = ActivityOrderDetailBinding.inflate(inflater);
        View view = binding.getRoot();
        setContentView(view);
        initData();
    }

    public void initData(){
        _orderDetailViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication()).create(OrderDetailViewModel.class);
        String orderID = getIntent().getStringExtra(Constant.ORDER_ID);
        initTracking();
        _orderDetailViewModel.getOrderDetails(orderID).observe(this, orderDetail -> {
            if(_adapter == null) {
                initRecyclerView(orderDetail);
                updateUI(orderDetail);
            }
        });
    }

    public void initRecyclerView(OrderDetail orderDetail){
        OrderDetailItemAdapter adapter = new OrderDetailItemAdapter(
                orderDetail.getOrderItems());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, layoutManager.getOrientation());

        binding.cvDetails.rcOrderDetails.setAdapter(adapter);
        binding.cvDetails.rcOrderDetails.setLayoutManager(layoutManager);
        binding.cvDetails.rcOrderDetails.addItemDecoration(dividerItemDecoration);
    }


    public void updateUI(OrderDetail orderDetail) {
        binding.tvName.setText(orderDetail.getRestaurantName());
        binding.cvPayment.tvSubtotalAmount.setText(Float.toString(orderDetail.getSubTotal()));
        binding.cvPayment.tvTotalAmount.setText(Float.toString(orderDetail.getTotal()));
        binding.cvPayment.tvTaxAmount.setText(Float.toString(orderDetail.getTax()));
        binding.cvPayment.tvDeliveryAmount.setText(Float.toString(orderDetail.getDeliveryFees()));

        OrderStatus status = orderDetail.getStatus();
        if(status != OrderStatus.Ordered){
            mSteppers[status.ordinal() - 1].nextStep();
        }
    }


    public void initTracking(){
        mSteppers = new VerticalStepperItemView[5];
        mSteppers[0] = binding.cvTracking.stepper0;
        mSteppers[1] = binding.cvTracking.stepper1;
        mSteppers[2] = binding.cvTracking.stepper2;
        mSteppers[3] = binding.cvTracking.stepper3;
        mSteppers[4] = binding.cvTracking.stepper4;
        VerticalStepperItemView.bindSteppers(mSteppers);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}