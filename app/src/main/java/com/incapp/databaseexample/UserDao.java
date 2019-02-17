package com.incapp.databaseexample;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("SELECT * FROM user WHERE phone = :phone " +
            "AND password = :password")
    User getUser(String phone, String password);
    
    @Query("SELECT * FROM user")
    List<User> getAllUser();
}
