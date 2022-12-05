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

import com.example.wagba.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private MainActivity activity;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = (MainActivity) getActivity();

        binding.btnRegister.setOnClickListener(view1 -> {
            //todo:: use try catch
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.fcv_content, RegisterFragment.newInstance());
            ft.commit();
        });

        binding.btnLogin.setOnClickListener(view1 -> {
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            RestaurantsFragment restaurantsFragment = RestaurantsFragment.newInstance();
            ft.replace(R.id.fcv_content, restaurantsFragment);
            BottomNavigationFragment bottomNavigationFragment =
                    BottomNavigationFragment.newInstance();
            ft.replace(R.id.fcv_navigation, bottomNavigationFragment);
            SearchFragment  fragment =
                    SearchFragment.newInstance();
            ft.replace(R.id.fcv_search, fragment);
            ft.commit();

        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}