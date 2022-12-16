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
import com.example.wagba.View.RestaurantsFragment;
import com.example.wagba.utils.Constant;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BottomNavigationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottomNavigationFragment extends Fragment {

    FragmentBottomNavigationBinding binding;
    Fragment activeFragment;
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

        init();
        FragmentManager fm = activity.getSupportFragmentManager();

        //todo:: refactor
        binding.fcvNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.cart) {
                BasketFragment basketFragment = (BasketFragment)
                        fm.findFragmentByTag(Constant.BASKET_FRAG_TAG);
                fm.beginTransaction().hide(activeFragment).
                        show(Objects.requireNonNull(basketFragment)).commit();
                activeFragment = basketFragment;
                return true;
            } else if (itemId == R.id.restaurants) {
                RestaurantsFragment restaurantsFragment = (RestaurantsFragment)
                        fm.findFragmentByTag(Constant.RESTAURANTS_FRAG_TAG);
                fm.beginTransaction().
                        hide(activeFragment)
                        .show(Objects.requireNonNull(restaurantsFragment))
                        .commit();
                activeFragment = restaurantsFragment;
                return true;
            } else if (itemId == R.id.orders) {
                MyOrdersFragment myOrdersFragment = (MyOrdersFragment)
                        fm.findFragmentByTag(Constant.MY_ORDERS_FRAG_TAG);
                fm.beginTransaction()
                        .hide(activeFragment)
                        .show(Objects.requireNonNull(myOrdersFragment))
                        .commit();
                activeFragment = myOrdersFragment;
                return true;
            }
            return false;
        });
    }

    void init() {
        BasketFragment basketFragment = BasketFragment.newInstance();
        MyOrdersFragment myOrderFragment = MyOrdersFragment.newInstance();
        activeFragment = RestaurantsFragment.newInstance();
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.fcv_content, basketFragment, Constant.BASKET_FRAG_TAG)
                .hide(basketFragment);
        ft.add(R.id.fcv_content, myOrderFragment, Constant.MY_ORDERS_FRAG_TAG)
                .hide(myOrderFragment);
        ft.add(R.id.fcv_content, activeFragment, Constant.RESTAURANTS_FRAG_TAG);
        ft.commit();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}