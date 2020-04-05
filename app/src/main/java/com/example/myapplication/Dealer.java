package com.example.myapplication;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

public class Dealer extends Activity {

    public int dealerHandValue;
    public Deck theDeck;

    Dealer() {
        theDeck = new Deck();
        dealerHandValue = 0;
    };

    Dealer(Deck theDeck) { this.theDeck = theDeck; };

    public void dealerHit(ImageView v) {
        v.setImageResource(theDeck.myDeck.get(0).getDrawable());
        dealerHandValue = dealerHandValue + theDeck.myDeck.get(0).getValue();
        theDeck.myDeck.remove(0);
    };

    public void finishHand() {


    };

    public int getDealerHandValue() {return dealerHandValue;};
}
