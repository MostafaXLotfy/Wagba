package com.example.wagba.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wagba.MainActivity;
import com.example.wagba.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        binding = ActivityLoginBinding.inflate(inflater);
        View root = binding.getRoot();
        setContentView(root);
        auth = FirebaseAuth.getInstance();

//        Intent registerIntent = new Intent(this, SignupActivity.class);
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
        String email = binding.etEmail.toString();
        if(TextUtils.isEmpty(email)){
            binding.etEmail.setError("Email is Required");
            return;
        }
        String password = binding.etPassword.toString();
        if(TextUtils.isEmpty(password)){
           binding.etPassword.setError("Email is Required");
           return;
        }
        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(this, actionResult->{
                    startActivity(new Intent(this, MainActivity.class));
                }).addOnFailureListener(this, e->{
                    Toast.makeText(this, "failed to login",
                            Toast.LENGTH_SHORT).show();
                });

    }
}