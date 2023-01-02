package com.example.wagba;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.wagba.View.BasketFragment;
import com.example.wagba.View.LoginActivity;
import com.example.wagba.View.OrdersFragment;
import com.example.wagba.View.ProfileFragment;
import com.example.wagba.View.RestaurantsFragment;
import com.example.wagba.databinding.ActivityMainBinding;
import com.example.wagba.utils.Constant;
import com.example.wagba.viewModel.MainViewModel;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Fragment activeFragment;
    private ActivityMainBinding binding;
    private MainViewModel _mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        _mainViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainViewModel.class);


        if (_mainViewModel.getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        init_navigation();
    }

    void init_navigation() {
        activeFragment = RestaurantsFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fcv_content, activeFragment
                        , Constant.RESTAURANTS_FRAG_TAG)
                .commit();
        binding.fcvNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.cart) {
                showBasketFragment();
                return true;
            } else if (itemId == R.id.restaurants) {
                showRestaurantsFragment();
                return true;
            } else if (itemId == R.id.orders) {
                showOrdersFragment();
                return true;
            } else if (itemId == R.id.profile) {
                showProfileFragment();
                return true;
            }
            return false;
        });
    }

    private void showRestaurantsFragment() {
        FragmentManager fm = getSupportFragmentManager();
        RestaurantsFragment restaurantsFragment = (RestaurantsFragment)
                fm.findFragmentByTag(Constant.RESTAURANTS_FRAG_TAG);
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(activeFragment);
        if (restaurantsFragment == null) {
            restaurantsFragment = RestaurantsFragment.newInstance();
            ft.add(R.id.fcv_content, restaurantsFragment, Constant.RESTAURANTS_FRAG_TAG);
        } else {
            ft.show(Objects.requireNonNull(restaurantsFragment));
        }
        activeFragment = restaurantsFragment;
        ft.commit();
    }

    private void showBasketFragment() {
        FragmentManager fm = getSupportFragmentManager();
        BasketFragment basketFragment = (BasketFragment)
                fm.findFragmentByTag(Constant.BASKET_FRAG_TAG);
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(activeFragment);
        if (basketFragment == null) {
            basketFragment = BasketFragment.newInstance();
            ft.add(R.id.fcv_content, basketFragment, Constant.BASKET_FRAG_TAG);
        } else {
            ft.show(Objects.requireNonNull(basketFragment));

        }
        ft.commit();

        activeFragment = basketFragment;
    }


    private void showProfileFragment() {
        FragmentManager fm = getSupportFragmentManager();
        ProfileFragment profileFragment = (ProfileFragment)
                fm.findFragmentByTag(Constant.PROFILE_FRAG_TAG);
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(activeFragment);
        if (profileFragment == null) {
            profileFragment = ProfileFragment.newInstance();
            ft.add(R.id.fcv_content, profileFragment, Constant.PROFILE_FRAG_TAG);
        } else {
            ft.show(Objects.requireNonNull(profileFragment));
        }
        ft.commit();

        activeFragment = profileFragment;

    }

    private void showOrdersFragment() {
        FragmentManager fm = getSupportFragmentManager();
        OrdersFragment ordersFragment = (OrdersFragment)
                fm.findFragmentByTag(Constant.MY_ORDERS_FRAG_TAG);
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(activeFragment);
        if (ordersFragment == null) {
            ordersFragment = OrdersFragment.newInstance();
            ft.add(R.id.fcv_content, ordersFragment, Constant.MY_ORDERS_FRAG_TAG);
        } else {
            ft.show(Objects.requireNonNull(ordersFragment));

        }
        activeFragment = ordersFragment;
        ft.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}