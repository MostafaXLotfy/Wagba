package com.example.wagba;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.wagba.Basket.BasketFragment;
import com.example.wagba.MyOrders.MyOrdersFragment;
import com.example.wagba.Restaurants.RestaurantsFragment;
import com.example.wagba.databinding.FragmentBottomNavigationBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BottomNavigationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottomNavigationFragment extends Fragment {

    FragmentBottomNavigationBinding binding;
    MyOrdersFragment myOrdersFragment;
    BasketFragment basketFragment;
    RestaurantsFragment restaurantsFragment;
    MainActivity activity;

    public BottomNavigationFragment() {
        // Required empty public constructor
    }

    public static BottomNavigationFragment newInstance() {
        BottomNavigationFragment fragment = new BottomNavigationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBottomNavigationBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        basketFragment = BasketFragment.newInstance();
        myOrdersFragment = MyOrdersFragment.newInstance();
        restaurantsFragment = RestaurantsFragment.newInstance();
        //todo:: refactor
        binding.fcvNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.profile) {
//                ((MainActivity) getActivity()).getSupportFragmentManager()
//                        .beginTransaction().replace(R.id.fcv_content)
                return true;
            } else if (itemId == R.id.cart) {
                replaceContentFragment(R.id.fcv_content, basketFragment);
                return true;
            } else if (itemId == R.id.restaurants) {
                replaceContentFragment(R.id.fcv_content, restaurantsFragment);
                return true;
            } else if (itemId == R.id.orders) {
                    replaceContentFragment(R.id.fcv_content, myOrdersFragment);
                return true;
            }
            return false;
        });
    }

    private void replaceContentFragment(int containerID, Fragment fragment ){
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(containerID, fragment);
        ft.commit() ;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}