package com.example.myapplication;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel(Parcel.Serialization.BEAN)
public class Dealer {

    private Deck theDeck;
    private Deck dealersHand;
    private Card dealerBottomCard;
    public boolean isWinner;

    @ParcelConstructor
    Dealer() {
        theDeck = new Deck();
        dealersHand = new Deck();
        dealerBottomCard = new Card();
    }

    Dealer(Deck theDeck) {
        this.theDeck = theDeck;
        dealerBottomCard = new Card();
        dealersHand = new Deck();
    }


    public void dealerHit(ImageView v, AnalyzeCount count) {
        if (theDeck.myDeck.get(0).getValue() <= 6 && theDeck.myDeck.get(0).getValue() >= 2) {
            count.add();
        }else if (theDeck.myDeck.get(0).getValue() >= 10 || theDeck.myDeck.get(0).getValue() == 1) {
            count.sub();
        }

        dealersHand.myDeck.add(theDeck.myDeck.get(0));
        v.setImageResource(theDeck.myDeck.get(0).getDrawable());
        v.setVisibility(View.VISIBLE);
        theDeck.myDeck.remove(0);
    }


    public void dealerHitBottom(ImageView v) {
        dealersHand.myDeck.add(theDeck.myDeck.get(0));
        dealerBottomCard = dealersHand.myDeck.get(1);
        theDeck.myDeck.remove(0);
    }


    public Card getDealerBottomCard() {
        return dealerBottomCard;
    }


    public void finishHand() {
    }


    public void dealerReset() {
        dealersHand = new Deck();
        dealerBottomCard = new Card();
    }


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
    }


    public boolean dealerHas21() {
        if (dealersHand.myDeck.size() == 2 && getDealerHandValue() == 21) {
            return true;
        }
        return false;
    }

    public double bustChance() {
        double chance = 0;

        switch (dealersHand.myDeck.get(0).getValue()) {
            case 2:
                chance = 35.30;
                break;
            case 3:
                chance = 37.56;
                break;
            case 4:
                chance = 40.28;
                break;
            case 5:
                chance = 42.89;
                break;
            case 6:
                chance = 42.08;
                break;
            case 7:
                chance = 25.99;
                break;
            case 8:
                chance = 23.86;
                break;
            case 9:
                chance = 23.34;
                break;
            case 10:
                chance = 21.43;
                break;
            case 1:
                chance = 11.65;
                break;
        }
        return chance;
    }


}