package com.incapp.databaseexample;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private static volatile AppDatabase appDatabase;

    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            synchronized (AppDatabase.class) {
                if (appDatabase == null) {
                    appDatabase = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "my_db"
                    ).allowMainThreadQueries()
                            .build();
                }
            }
        }
        return appDatabase;
    }
}
