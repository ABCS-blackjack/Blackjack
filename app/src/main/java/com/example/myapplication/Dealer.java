package com.example.myapplication;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

public class Dealer {

    public int dealerHandValue;
    public Deck theDeck;
    private Deck dealersHand;
    private Card dealerBottomCard;

    Dealer() {
        theDeck = new Deck();
        dealersHand = new Deck();
        dealerBottomCard = new Card();
        dealerHandValue = 0;
    };

    Dealer(Deck theDeck) {
        this.theDeck = theDeck;
        dealersHand = new Deck();
    };

    public void dealerHit(ImageView v) {
        dealersHand.myDeck.add(theDeck.myDeck.get(0));
        v.setImageResource(theDeck.myDeck.get(0).getDrawable());
        //dealerHandValue = dealerHandValue + theDeck.myDeck.get(0).getValue();
        theDeck.myDeck.remove(0);
    };

    public void dealerHitBottom(ImageView v) {
        dealersHand.myDeck.add(theDeck.myDeck.get(0));
        dealerBottomCard = theDeck.myDeck.get(0);
        //dealerHandValue = dealerHandValue + theDeck.myDeck.get(0).getValue();
        theDeck.myDeck.remove(0);
    };

    public Card getDealerBottomCard() {
        return dealerBottomCard;
    };

    public void finishHand() {
    };


    public int getDealerHandValue() {
        int totalValue = 0;
        boolean existAce = false;

        for (Card i : dealersHand.myDeck) {
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
    };

    public boolean dealerHas21() {
        if (dealersHand.myDeck.size() == 2 && getDealerHandValue() == 21) {
            return true;
        }
        return false;
    };

}