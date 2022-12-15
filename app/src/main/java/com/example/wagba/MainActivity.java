package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.wagba.Authentication.LoginActivity;
import com.example.wagba.Restaurants.RestaurantsFragment;
import com.example.wagba.databinding.ActivityMainBinding;
import com.google.android.gms.signin.SignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(this, LoginActivity.class));
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fcv_search, new SearchFragment());
        ft.replace(R.id.fcv_content, new RestaurantsFragment());
        ft.replace(R.id.fcv_navigation, new BottomNavigationFragment());
        ft.commit();
    }
}