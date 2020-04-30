package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface PlayerDao {
    @Query("SELECT count(playerID) FROM Player")
    int playerCheck();
    @Query("SELECT numGames FROM Player")
    int getNumGames();
    @Query("SELECT numBusts FROM Player")
    int getNumBusts();
    @Query("SELECT num21 FROM Player")
    int getNum21();
    @Query("UPDATE Player SET numGames = numGames+1 WHERE playerID = 0")
    void incrementNumGames();
    @Query("UPDATE Player SET numBusts = numBusts+1 WHERE playerID = 0")
    void incrementNumBusts();
    @Query("UPDATE Player SET num21 = num21+1 WHERE playerID = 0")
    void incrementNum21();
    @Insert(onConflict = REPLACE)
    void insertPlayer(Player p);
    @Update(onConflict = REPLACE)
    void updatePlayer(Player p);
}
