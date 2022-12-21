package com.example.wagba.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wagba.model.User;
import com.example.wagba.service.UserDao;
import com.example.wagba.service.UserRoomDatabase;
import com.example.wagba.utils.Constant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UserRepository {
    private static final String TAG = "UserRepository";
    private static User _user;
    private static LiveData<User> _userLiveData;
    private UserDao _userDao;
    private FirebaseAuth _auth;
    private Application _application;

    public UserRepository(Application application) {
        _application = application;
        UserRoomDatabase userRoomDatabase = UserRoomDatabase.getDataBase(application);
        _userDao = userRoomDatabase.userDao();
        _auth = FirebaseAuth.getInstance();
    }

    public LiveData<User> signup(User user, String password) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = db.getReference(Constant.USERS_END_POINT);
        MutableLiveData<User> userLiveData = new MutableLiveData<>();
        _userLiveData = userLiveData;
        _auth.createUserWithEmailAndPassword(user.getEmail(), password)
                .addOnSuccessListener(authResult -> {
                    String uid = authResult.getUser().getUid();
                    _user = user;
                    user.setUid(uid);
                    usersRef.child(uid).setValue(user);
                    userLiveData.setValue(user);
                    _userDao.Insert(user);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(_application, e.toString(), Toast.LENGTH_SHORT).show();
                })
        ;
        return _userLiveData;
    }

    public LiveData<User> login(String email, String password) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = db.getReference(Constant.USERS_END_POINT);
        MutableLiveData<User> userLiveData = new MutableLiveData<>();
        _userLiveData = userLiveData;

        _auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    String id = authResult.getUser().getUid();
                    usersRef.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            _user = snapshot.getValue(User.class);
                            userLiveData.setValue(_user);
                            _userDao.Insert(_user);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(_application, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }).addOnFailureListener(e -> {
                    Toast.makeText(_application, e.toString(), Toast.LENGTH_SHORT).show();
                });
        return _userLiveData;
    }

    public void signOut() {
        _auth.signOut();
        _userDao.DeleteAll();
    }

    public LiveData<User> getCurrentUser() {
        if (_userLiveData != null) return _userLiveData;
        else if (_auth.getCurrentUser() == null) return null;
        else {
            Log.d(TAG, "getCurrentUser: ");
            _userLiveData =  _userDao.getUser();
            _user = _userLiveData.getValue();
        }
        return  _userLiveData;
    }

    public String getUserID(){
        return _user.getUid();
    }
}
