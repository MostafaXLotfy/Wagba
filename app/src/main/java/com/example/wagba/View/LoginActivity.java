package com.example.wagba.View;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.wagba.MainActivity;
import com.example.wagba.databinding.ActivityLoginBinding;
import com.example.wagba.viewModel.LoginViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    static private final String TAG = "LoginActivity";
    ActivityLoginBinding binding;
    private FirebaseAuth auth;
    private LoginViewModel _loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        binding = ActivityLoginBinding.inflate(inflater);
        View root = binding.getRoot();
        setContentView(root);
        auth = FirebaseAuth.getInstance();
        _loginViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication()).create(LoginViewModel.class);

        binding.btnRegister.setOnClickListener(view ->{
            startActivity(new Intent(this, SignupActivity.class));
        });
        binding.btnLogin.setOnClickListener(view ->{
            login();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void login(){
        String email = binding.etEmail.getText().toString();
        if(TextUtils.isEmpty(email)){
            binding.etEmail.setError("Email is Required");
            return;
        }
        String password = binding.etPassword.getText().toString();
        if(TextUtils.isEmpty(password)){
           binding.etPassword.setError("Email is Required");
           return;
        }
        _loginViewModel.login(email, password).observe(this, user -> {
            if(user != null){
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        });
    }
}