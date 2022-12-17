package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.wagba.View.LoginActivity;
import com.example.wagba.View.BasketFragment;
import com.example.wagba.View.OrdersFragment;
import com.example.wagba.View.RestaurantsFragment;
import com.example.wagba.databinding.ActivityMainBinding;
import com.example.wagba.utils.Constant;
import com.example.wagba.viewModel.MainViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel _mainViewModel;
    BasketFragment basketFragment = BasketFragment.newInstance();
    OrdersFragment myOrderFragment = OrdersFragment.newInstance();
    RestaurantsFragment restaurantsFragment;
    Fragment activeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        _mainViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication()).create(MainViewModel.class);


        if(_mainViewModel.getCurrentUser() == null){
            startActivity(new Intent(this, LoginActivity.class));
        }
        init_navigation();
    }

    void init_navigation() {
         basketFragment = BasketFragment.newInstance();
         myOrderFragment = OrdersFragment.newInstance();
        restaurantsFragment = RestaurantsFragment.newInstance();
        activeFragment = restaurantsFragment;


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.fcv_content, basketFragment, Constant.BASKET_FRAG_TAG)
                .hide(basketFragment);
        ft.add(R.id.fcv_content, myOrderFragment, Constant.MY_ORDERS_FRAG_TAG)
                .hide(myOrderFragment);
        ft.add(R.id.fcv_content, activeFragment, Constant.RESTAURANTS_FRAG_TAG);
        ft.commit();


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
                OrdersFragment ordersFragment = (OrdersFragment)
                        fm.findFragmentByTag(Constant.MY_ORDERS_FRAG_TAG);
                fm.beginTransaction()
                        .hide(activeFragment)
                        .show(Objects.requireNonNull(ordersFragment))
                        .commit();
                activeFragment = ordersFragment;
                return true;
            }
            return false;
        });
    }
}