package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface DealerDao {
    @Query("SELECT dealerBustChance FROM Dealer")
    Double getDealerBustChance();
    @Insert(onConflict = REPLACE)
    void insertDealer(Dealer d);
    @Update(onConflict = REPLACE)
    void updateDealer(Dealer d);
}
