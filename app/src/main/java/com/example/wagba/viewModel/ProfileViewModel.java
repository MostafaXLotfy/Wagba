package com.example.wagba.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wagba.model.User;
import com.example.wagba.repository.BasketRepository;
import com.example.wagba.repository.UserRepository;

public class ProfileViewModel extends AndroidViewModel {
    private UserRepository _userRepository;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        _userRepository = new UserRepository(application);
    }

    public LiveData<User> getCurrentUser() {
        return _userRepository.getCurrentUser();
    }

    public void signOut() {
        _userRepository.signOut();
        new BasketRepository(getApplication()).deleteBasket();
    }

}
