package com.example.wagba.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.wagba.adapter.OrderDetailItemAdapter;
import com.example.wagba.databinding.FragmentBasketBinding;
import com.example.wagba.model.Basket;
import com.example.wagba.utils.ImageLoader;
import com.example.wagba.viewModel.BasketViewModel;

import java.text.MessageFormat;

public class BasketFragment extends Fragment {

    private static final String TAG = "BasketFragment";
    BasketViewModel _basketViewModel;
    OrderDetailItemAdapter _adapter;
    private FragmentBasketBinding binding;

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
        _basketViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(requireActivity().getApplication()).create(BasketViewModel.class);

        LiveData<Basket> basketLiveData =
                _basketViewModel.getBasket();

        basketLiveData.observe(requireActivity(), basket -> {
            if (basket == null) {
                setBasketInVisible();
            } else {
                setBasketVisible();
                binding.svBasket.setVisibility(View.VISIBLE);
                binding.tvEmptyBasket.setVisibility(View.INVISIBLE);

                _adapter = new OrderDetailItemAdapter(basket.getOrderItems());
                LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity());
                DividerItemDecoration dividerItemDecoration =
                        new DividerItemDecoration(requireActivity(), layoutManager.getOrientation());
                binding.cvDetails.rcOrderDetails.setAdapter(_adapter);
                binding.cvDetails.rcOrderDetails.setLayoutManager(layoutManager);
                binding.cvDetails.rcOrderDetails.addItemDecoration(dividerItemDecoration);
                updateUI(basket);
            }
            if (_adapter != null) _adapter.notifyDataSetChanged();
        });
        binding.btnOrder.setOnClickListener(this::onOrder);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    public void updateUI(Basket basket) {
        Log.d(TAG, "updateUI: " + basket.getRestaurantName());
        binding.tvName.setText(basket.getRestaurantName());
        ImageLoader.load(requireContext(), binding.ivImage, basket.getLogo());

        binding.cvPayment.tvSubtotalAmount.setText(MessageFormat.format("{0}",
                basket.getSubTotal()));
        binding.cvPayment.tvTotalAmount.setText(MessageFormat.format("{0}",
                basket.getTotal()));
        binding.cvPayment.tvTaxAmount.setText(MessageFormat.format("{0}",
                basket.getTax()));
        binding.cvPayment.tvDeliveryAmount.setText(MessageFormat.format("{0}",
                basket.getDeliveryFees()));
    }

    public void onOrder(View view) {
        int rb_id = binding.rgDeliveryLocation.getCheckedRadioButtonId();
        RadioButton rb = binding.getRoot().findViewById(rb_id);
        if (rb == null) {
            Toast.makeText(requireContext(), "you must chose the delivery location",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        String deliveryLocation = rb.getText().toString();
        _basketViewModel.submitOrder(deliveryLocation);
        setBasketInVisible();
    }

    private void setBasketVisible() {
        binding.svBasket.setVisibility(View.VISIBLE);
        binding.tvEmptyBasket.setVisibility(View.INVISIBLE);
    }

    private void setBasketInVisible() {
        binding.svBasket.setVisibility(View.INVISIBLE);
        binding.tvEmptyBasket.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}