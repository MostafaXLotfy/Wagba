package com.example.wagba.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wagba.MainActivity;
import com.example.wagba.databinding.ActivitySignupBinding;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        binding = ActivitySignupBinding.inflate(inflater);
        View root = binding.getRoot();
        setContentView(root);
        auth = FirebaseAuth.getInstance();
        binding.btnRegister.setOnClickListener(view->{
            signup();
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void signup() {
        String email = binding.etEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            binding.etEmail.setError("Email is Required");
            return;
        }
        String password = binding.etPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            binding.etPassword.setError("Password is Required");
            return;
        }

        String confirmPassword = binding.etConfirmPassword.getText().toString();
        if (TextUtils.isEmpty(confirmPassword) || TextUtils.equals(confirmPassword, password)) {
            binding.etConfirmPassword.setError("Confirm Password must be equal to password");
            return;
        }
        String phone = binding.etPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            binding.etPhone.setError("Phone Number is Required");
            return;
        }
        String username = binding.etUsername.getText().toString();
        if (TextUtils.isEmpty(username)) {
            binding.etUsername.setError("username is required");
            return;
        }
        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(this, authResult -> {
                    startActivity(new Intent(this, MainActivity.class));
                    Toast.makeText(this,
                            "Signup is successful", Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(e -> {
                    Toast.makeText(this,
                            "Failed To Signup ", Toast.LENGTH_SHORT).show();
                });
    }


}