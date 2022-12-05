package com.example.wagba.Basket.PaymentDetail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wagba.databinding.FragmentPaymentDetailBinding;


public class PaymentDetailFragment extends Fragment {
    FragmentPaymentDetailBinding binding;
    private final static String orderDetailsArg = "paymentDetail";
    PaymentDetailModel paymentDetailModel;


    public PaymentDetailFragment() {
        // Required empty public constructor
    }

    public static PaymentDetailFragment newInstance(PaymentDetailModel paymentDetailModel) {
        PaymentDetailFragment fragment = new PaymentDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(orderDetailsArg, paymentDetailModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            paymentDetailModel = (PaymentDetailModel) args.getParcelable(orderDetailsArg);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPaymentDetailBinding.inflate(inflater,
                container, false);
        View view  = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvTotalAmount.setText(paymentDetailModel.getTotal());
        binding.tvSubtotalAmount.setText(paymentDetailModel.getSubTotal());
        binding.tvTaxAmount.setText(paymentDetailModel.getTax());
        binding.tvDeliveryAmount.setText(paymentDetailModel.getDeliveryFees());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}