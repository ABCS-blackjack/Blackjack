package com.example.myapplication;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Room;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Entity
@Parcel(Parcel.Serialization.BEAN)
public class Player {

    @Ignore
    public Deck playersHand;
    @Ignore
    public Deck theDeck;
    @Ignore
    public boolean isWinner;

    @PrimaryKey
    public int playerID = 0;
    public int numGames = 0;
    public int numWins = 0;
    public int numLosses = 0;
    public int numTies = 0;
    public int numBusts = 0;
    public int num21 = 0;
    public int numHits;

    @ParcelConstructor
    Player() {
        theDeck = new Deck();
        playersHand = new Deck();
        numHits = 0;
    }
    Player(Deck theDeck) {
        this.theDeck = theDeck;
        playersHand = new Deck();
        numHits = 0;
    }



    public void playerHit(ImageView v, AnalyzeCount count) {
        numHits++;
        if (theDeck.myDeck.get(0).getValue() <= 6 && theDeck.myDeck.get(0).getValue() >= 2) {
            count.add();
        }else if (theDeck.myDeck.get(0).getValue() >= 10 || theDeck.myDeck.get(0).getValue() == 1) {
            count.sub();
        }

        playersHand.myDeck.add(theDeck.myDeck.get(0));
        v.setImageResource(theDeck.myDeck.get(0).getDrawable());
        v.setVisibility(View.VISIBLE);
        theDeck.myDeck.remove(0);
    }

    public void playerStand() {
    }

    public void playerDoubleDown(ImageView v, AnalyzeCount count) {
        if (theDeck.myDeck.get(0).getValue() <= 6 && theDeck.myDeck.get(0).getValue() >= 2) {
            count.add();
        }else if (theDeck.myDeck.get(0).getValue() >= 10 || theDeck.myDeck.get(0).getValue() == 1) {
            count.sub();
        }

        playersHand.myDeck.add(theDeck.myDeck.get(0));
        v.setImageResource(theDeck.myDeck.get(0).getDrawable());
        v.setVisibility(View.VISIBLE);
        theDeck.myDeck.remove(0);
    }

    public void playerSplit(int numOfHands) {
    }

    //get value of the players hand accounting for the value of aces being 1 or 11
    public int getPlayerHandValue() {
        int totalValue = 0;
        boolean existAce = false;

        for (Card i : playersHand.myDeck) {
            if (i.getValue() == 1 && totalValue <= 10) {
                totalValue = totalValue + 11;
                existAce = true;
            } else if (i.getValue() == 1 && totalValue > 10) {
                totalValue = totalValue + 1;
            } else {

                if (existAce == true && (i.getValue() + totalValue <= 21)) {
                    totalValue = totalValue + i.getValue();
                } else if (existAce == true && (i.getValue() + totalValue > 21)) {
                    totalValue = totalValue + i.getValue() - 10;
                    existAce = false;
                } else {
                    totalValue = totalValue + i.getValue();
                }
            }
        }

        return totalValue;
    }

    public boolean isPlayerBust() {
        if (this.getPlayerHandValue() <= 21) return false;
        else{
            return true;
        }
    }

    public void playerReset() {
        numHits = 0;
        playersHand = new Deck();
    }

    public void playerUpdateData() {
        numGames++;
        if (isPlayerBust()) numBusts++;
        if (getPlayerHandValue() == 21) num21++;
    }

    public boolean playerHas21() {
        if (playersHand.myDeck.size() == 2 && getPlayerHandValue() == 21) {
            return true;
        }
        return false;
    }

    public int getPlayerCard(int index) {
        if (index >= playersHand.myDeck.size()) {
            return 0;
        }
        return playersHand.myDeck.get(index).getDrawable();
    }

    public double bustChance () {
        double chance = 0;
        for (Card i : theDeck.myDeck) {
            if ((getPlayerHandValue() + i.getValue()) > 21) {
                chance++;
            }
        }
        chance = chance/theDeck.myDeck.size();
        return chance*100;
    }

}
