package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.wagba.View.LoginActivity;
import com.example.wagba.View.BasketFragment;
import com.example.wagba.View.OrdersFragment;
import com.example.wagba.View.ProfileFragment;
import com.example.wagba.View.RestaurantsFragment;
import com.example.wagba.databinding.ActivityMainBinding;
import com.example.wagba.utils.Constant;
import com.example.wagba.viewModel.MainViewModel;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel _mainViewModel;
    BasketFragment _basketFragment;
    OrdersFragment _OrdersFragment;
    RestaurantsFragment _restaurantsFragment;
    ProfileFragment _profileFragment;
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
         _basketFragment = BasketFragment.newInstance();
         _OrdersFragment = OrdersFragment.newInstance();
        _restaurantsFragment = RestaurantsFragment.newInstance();
        _profileFragment = ProfileFragment.newInstance();
        activeFragment = _restaurantsFragment;


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.fcv_content, _basketFragment, Constant.BASKET_FRAG_TAG)
                .hide(_basketFragment);
        ft.add(R.id.fcv_content, _OrdersFragment, Constant.MY_ORDERS_FRAG_TAG)
                .hide(_OrdersFragment);
        ft.add(R.id.fcv_content, _profileFragment, Constant.PROFILE_FRAG_TAG)
                .hide(_profileFragment);
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
            }else if (itemId == R.id.profile){
                ProfileFragment profileFragment = (ProfileFragment)
                        fm.findFragmentByTag(Constant.PROFILE_FRAG_TAG);
                fm.beginTransaction()
                        .hide(activeFragment)
                        .show(Objects.requireNonNull(profileFragment))
                        .commit();
                activeFragment = profileFragment;
                return true;
            }
            return false;
        });
    }
}