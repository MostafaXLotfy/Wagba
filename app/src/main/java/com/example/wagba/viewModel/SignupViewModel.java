package com.example.wagba.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wagba.model.User;
import com.example.wagba.repository.UserRepository;

public class SignupViewModel extends AndroidViewModel {
    private final UserRepository _userRepository;

    public SignupViewModel(@NonNull Application application) {
        super(application);
        _userRepository = new UserRepository(application);
    }

    public LiveData<User> signup(User user, String password){
        return _userRepository.signup(user, password);
    }
}
