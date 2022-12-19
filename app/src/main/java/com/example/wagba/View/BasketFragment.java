package com.example.wagba.View;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.wagba.adapter.OrdersItemsAdapter;
import com.example.wagba.MainActivity;
import com.example.wagba.databinding.FragmentBasketBinding;
import com.example.wagba.model.Basket;
import com.example.wagba.viewModel.BasketViewModel;

public class BasketFragment extends Fragment {

    private static final String TAG = "BasketFragment";
    private FragmentBasketBinding binding;
    private MainActivity activity;
    private BasketViewModel _basketViewModel;
    OrdersItemsAdapter _adapter;

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
            if(basket != null){
                _adapter = new OrdersItemsAdapter(basket.getOrderItems());
                LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity());
                DividerItemDecoration dividerItemDecoration =
                        new DividerItemDecoration(requireActivity(),layoutManager.getOrientation());
                binding.rcOrderDetails.setAdapter(_adapter);
                binding.rcOrderDetails.setLayoutManager(layoutManager);
                binding.rcOrderDetails.addItemDecoration(dividerItemDecoration);
                updateUI(basket);
            }
            if(_adapter != null) _adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    public void updateUI(Basket basket){
        Log.d(TAG, "updateUI: " + basket.getRestaurantName());
        binding.tvName.setText(basket.getRestaurantName());
        binding.cvPayment.tvSubtotalAmount.setText(Float.toString(basket.getSubTotal()));
        binding.cvPayment.tvTotalAmount.setText(Float.toString(basket.getTotal()));
        binding.cvPayment.tvTaxAmount.setText(Float.toString(basket.getTax()));
        binding.cvPayment.tvDeliveryAmount.setText(Float.toString(basket.getDeliveryFees()));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}