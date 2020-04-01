package com.example.myapplication;

import java.util.ArrayList;

public class Deck extends Card {

	final private String[] values = {"ace", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king"};
	final private String[] suits = {"_spade", "_heart", "_diamond", "_club"};
	
	public ArrayList<Integer> myDeck;

	public Deck() {
		myDeck = new ArrayList<Integer> ();
		myDeck.add(R.drawable.ace_club);	
        myDeck.add(R.drawable.two_club);
        myDeck.add(R.drawable.three_club);
        myDeck.add(R.drawable.four_club);
        myDeck.add(R.drawable.five_club);
        myDeck.add(R.drawable.six_club);
        myDeck.add(R.drawable.seven_club);
        myDeck.add(R.drawable.eight_club);
        myDeck.add(R.drawable.nine_club);
        myDeck.add(R.drawable.ten_club);
        myDeck.add( R.drawable.jack_club);
        myDeck.add( R.drawable.queen_club);
        myDeck.add( R.drawable.king_club);

        myDeck.add( R.drawable.ace_spade);
        myDeck.add( R.drawable.two_spade);
        myDeck.add( R.drawable.three_spade);
        myDeck.add( R.drawable.four_spade);
        myDeck.add( R.drawable.five_spade);
        myDeck.add( R.drawable.six_spade);
        myDeck.add( R.drawable.seven_spade);
        myDeck.add( R.drawable.eight_spade);
        myDeck.add( R.drawable.nine_spade);
        myDeck.add( R.drawable.ten_spade);
        myDeck.add( R.drawable.jack_spade);
        myDeck.add( R.drawable.queen_spade);
        myDeck.add( R.drawable.king_spade);

        myDeck.add( R.drawable.ace_diamond);
        myDeck.add( R.drawable.two_diamond);
        myDeck.add( R.drawable.three_diamond);
        myDeck.add( R.drawable.four_diamond);
        myDeck.add( R.drawable.five_diamond);
        myDeck.add( R.drawable.six_diamond);
        myDeck.add( R.drawable.seven_diamond);
        myDeck.add( R.drawable.eight_diamond);
        myDeck.add( R.drawable.nine_diamond);
        myDeck.add( R.drawable.ten_diamond);
        myDeck.add( R.drawable.jack_diamond);
        myDeck.add( R.drawable.queen_diamond);
        myDeck.add( R.drawable.king_diamond);

        myDeck.add( R.drawable.ace_heart);
        myDeck.add( R.drawable.two_heart);
        myDeck.add( R.drawable.three_heart);
        myDeck.add( R.drawable.four_heart);
        myDeck.add( R.drawable.five_heart);
        myDeck.add( R.drawable.six_heart);
        myDeck.add( R.drawable.seven_heart);
        myDeck.add( R.drawable.eight_heart);
        myDeck.add( R.drawable.nine_heart);
        myDeck.add( R.drawable.ten_heart);
        myDeck.add( R.drawable.jack_heart);
        myDeck.add( R.drawable.queen_heart);
        myDeck.add( R.drawable.king_heart);

    }

//	public void createDeck(int numOfDecks) {
//		for (int c = 0; c < numOfDecks; c++) {
//			for (int i = 0; i < suits.length; i++) {
//				for (int j = 0; j < values.length; j++) {
//					Card newCard = new Card();
//					newCard.createCard(values[j], suits[i]);
//					myDeck.add(newCard);
//				}
//			}
//		}
//	}

}