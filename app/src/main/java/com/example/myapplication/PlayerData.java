package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class PlayerData {

    @PrimaryKey
    public int playerDataID;
    public int numHands;
    public int numWins;
    public int numLosses;
    public int numTies;
    public int numBusts;
    public int num21;

    PlayerData() {
        playerDataID = 0;
        numHands = 0;
        numWins = 0;
        numLosses = 0;
        numTies = 0;
        numBusts = 0;
        num21 = 0;
    }

    public void playerUpdateData(Player p) {
        numHands++;
        if (p.isPlayerBust()) numBusts++;
        if (p.getPlayerHandValue() == 21) num21++;
    }
}
