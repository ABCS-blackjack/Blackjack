package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Player.class}, version = 1)
public abstract class BlackjackDatabase extends RoomDatabase {
    public abstract PlayerDao playerDao();
}
