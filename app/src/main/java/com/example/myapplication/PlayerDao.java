package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlayerDao {
    @Query("SELECT numGamesPlayed FROM Player")
    int getNumGamesPlayed();
    @Query("SELECT numBusts FROM Player")
    int getNumBusts();
    @Query("SELECT num21 FROM Player")
    int getNum21();
    @Insert
    void insertPlayer(Player p);
    @Update
    void updatePlayer(Player p);
}
