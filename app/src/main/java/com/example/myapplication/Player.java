package com.example.myapplication;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Player extends Dealer {

    private int playerHandValue;
    public Deck playersHand;
    public Deck theDeck;



    Player() {

        playersHand = new Deck();
        playerHandValue = 0;
    };

    Player(Deck theDeck) { this.theDeck = theDeck; };

    public void playerHit(ImageView v) {
        v.setImageResource(theDeck.myDeck.get(0).getDrawable());
        playerHandValue = playerHandValue + theDeck.myDeck.get(0).getValue();
        theDeck.myDeck.remove(0);
    };

    public void playerStand() {
    };

    public void playerDoubleDown() {};

    public void playerSplit() {};

    public int getPlayerHandValue() {return playerHandValue;};

}
