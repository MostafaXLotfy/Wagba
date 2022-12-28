package com.example.wagba.View;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.wagba.MainActivity;
import com.example.wagba.databinding.ActivitySignupBinding;
import com.example.wagba.model.User;
import com.example.wagba.viewModel.SignupViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    private SignupViewModel _signupViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        binding = ActivitySignupBinding.inflate(inflater);
        View root = binding.getRoot();
        setContentView(root);
        _signupViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication()).create(SignupViewModel.class);

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
        String phone = binding.etPhone.getText().toString();
        String password = binding.etPassword.getText().toString();
        String confirmPassword = binding.etConfirmPassword.getText().toString();
        String username = binding.etUsername.getText().toString();


        if (TextUtils.isEmpty(email)) {
            binding.etEmail.setError("Email is Required");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            binding.etPassword.setError("Password is Required");
            return;
        }

        if (TextUtils.isEmpty(confirmPassword) || !TextUtils.equals(confirmPassword, password)) {
            binding.etConfirmPassword.setError("Confirm Password must be equal to password");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            binding.etPhone.setError("Phone Number is Required");
            return;
        }
        if (TextUtils.isEmpty(username)) {
            binding.etUsername.setError("username is required");
            return;
        }

        int rb_id = binding.rgGender.getCheckedRadioButtonId();
        RadioButton rb = binding.getRoot().findViewById(rb_id);
        if(rb == null){
            Toast.makeText(this, "gender can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        String gender = rb.getText().toString();

        User user = new User("1", username, email, phone, gender);
        _signupViewModel.signup(user, password).observe(this, user1 -> {
            if(user1 != null){
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        });
    }


}