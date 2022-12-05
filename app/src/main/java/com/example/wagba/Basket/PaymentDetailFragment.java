package com.example.wagba.Basket;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wagba.R;
import com.example.wagba.databinding.FragmentPaymentDetailBinding;

import javax.security.auth.Destroyable;

public class PaymentDetailFragment extends Fragment {
    FragmentPaymentDetailBinding binding;


    public PaymentDetailFragment() {
        // Required empty public constructor
    }

    public static PaymentDetailFragment newInstance(String param1, String param2) {
        PaymentDetailFragment fragment = new PaymentDetailFragment();
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
        binding = FragmentPaymentDetailBinding.inflate(inflater,
                container, false);
        View view  = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}