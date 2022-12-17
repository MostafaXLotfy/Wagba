package com.example.wagba.service;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.wagba.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void Insert(User user);

    @Query("Delete FROM USER_TABLE")
    void DeleteAll();

    @Query("SELECT * FROM USER_TABLE LIMIT 1")
    LiveData<User> getUser();
}
