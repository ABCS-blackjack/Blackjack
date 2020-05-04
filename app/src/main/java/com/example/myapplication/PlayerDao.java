package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface PlayerDao {
    @Query("SELECT count(playerID) FROM Player")
    int playerCheck();
    @Query("SELECT bustChanceString FROM Player")
    String getBustChanceString();
    @Insert(onConflict = REPLACE)
    void insertPlayer(Player p);
    @Update(onConflict = REPLACE)
    void updatePlayer(Player p);
}
