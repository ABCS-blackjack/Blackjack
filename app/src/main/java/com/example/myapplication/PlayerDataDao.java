package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface PlayerDataDao {
    @Query("SELECT numHands FROM PlayerData")
    int getNumHands();
    @Query("SELECT numWins FROM PlayerData")
    int getNumWins();
    @Query("SELECT numLosses FROM PlayerData")
    int getNumLosses();
    @Query("SELECT numTies FROM PlayerData")
    int getNumTies();
    @Query("SELECT numBusts FROM PlayerData")
    int getNumBusts();
    @Query("SELECT num21 FROM PlayerData")
    int getNum21();
    @Insert(onConflict = REPLACE)
    void insertPlayerData(PlayerData pd);
    @Update(onConflict = REPLACE)
    void updatePlayerData(PlayerData pd);
}
