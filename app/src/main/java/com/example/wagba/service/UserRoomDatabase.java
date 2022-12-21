package com.example.wagba.service;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.wagba.model.OrderItem;
import com.example.wagba.model.Payment;
import com.example.wagba.model.Restaurant;
import com.example.wagba.model.User;
import com.example.wagba.utils.Converters;

@Database(entities = {
        User.class,
        Restaurant.class,
        OrderItem.class,
        Payment.class,
        },
        version = 15, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class UserRoomDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract BasketDao BasketDao();
    private static UserRoomDatabase INSTANCE;

    public static UserRoomDatabase getDataBase(final Context context){
        synchronized (UserRoomDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                UserRoomDatabase.class, "user_database")
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        // Migration is not part of this codelab.
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return INSTANCE;
    }
}
