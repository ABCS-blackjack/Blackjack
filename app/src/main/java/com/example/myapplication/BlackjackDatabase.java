package com.example.myapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Player.class, PlayerData.class, Dealer.class}, version = 8)
public abstract class BlackjackDatabase extends RoomDatabase {
    public abstract PlayerDao playerDao();
    public abstract PlayerDataDao playerDataDao();
    public abstract DealerDao dealerDao();
    private static volatile BlackjackDatabase instance;
    //private static final int numThreads = 4;
    //static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(numThreads);

    static BlackjackDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (BlackjackDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            BlackjackDatabase.class, "BlackjackDB").allowMainThreadQueries()
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return instance;
    }
}
