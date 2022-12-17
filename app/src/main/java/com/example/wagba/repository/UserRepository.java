package com.example.wagba.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wagba.model.User;
import com.example.wagba.service.UserDao;
import com.example.wagba.service.UserRoomDatabase;
import com.google.firebase.auth.FirebaseAuth;


public class UserRepository {
    private static final String TAG = "UserRepository";
    private UserDao _userDao;
    private FirebaseAuth _auth;
    private Application _application;
    LiveData<User> _userLiveData;
    public UserRepository(Application application){
        _application = application;
        UserRoomDatabase userRoomDatabase = UserRoomDatabase.getDataBase(application);
        _userDao = userRoomDatabase.userDao();
        _auth = FirebaseAuth.getInstance();
    }

    public LiveData<User> signup(User user, String password){
        _userLiveData = new MutableLiveData<>(null);
        _auth.createUserWithEmailAndPassword(user.getEmail(), password)
                .addOnSuccessListener(authResult -> {
                    _userDao.Insert(user);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(_application, e.toString(), Toast.LENGTH_SHORT).show();
                })
        ;
        return _userLiveData;
    }

    public LiveData<User> login(String email, String password){
        Log.d(TAG, "login: " + email);
//        _userLiveData =  new MutableLiveData<>(null);
        _auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    _userLiveData = _userDao.getUser();
                }).addOnFailureListener(e -> {
                    Toast.makeText(_application, e.toString(), Toast.LENGTH_SHORT).show();
                });
        return _userLiveData;
    }

    public void signOut(){
         _auth.signOut();
         _userDao.DeleteAll();
    }

    public LiveData<User> getCurrentUser() {
        if(_auth.getCurrentUser() == null){
            return null;
        }
        _userLiveData = _userDao.getUser();
        return _userLiveData;
    }
}
