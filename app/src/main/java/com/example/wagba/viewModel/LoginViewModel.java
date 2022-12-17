package com.example.wagba.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wagba.model.User;
import com.example.wagba.repository.UserRepository;

public class LoginViewModel extends AndroidViewModel {
    private final UserRepository _userRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        _userRepository = new UserRepository(application);
    }
    public LiveData<User> login(String email, String password){
        return _userRepository.login(email, password);
    }

    public LiveData<User> getCurrentUser(){
        return _userRepository.getCurrentUser();
    }

    public void signOut(){
        _userRepository.signOut();
    }
}
