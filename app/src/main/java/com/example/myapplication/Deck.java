package com.example.myapplication;

import java.util.ArrayList;

public class Deck extends Card {
	
	public ArrayList<Card> myDeck;

        public Deck() {
        }

        public Deck(int numOfDecks) {
                for (int i = 0; i < numOfDecks; i++) {
                        myDeck = new ArrayList<>();
                        myDeck.add(new Card(R.drawable.ace_club, 1));
                        myDeck.add(new Card(R.drawable.two_club,2));
                        myDeck.add(new Card(R.drawable.three_club,3));
                        myDeck.add(new Card(R.drawable.four_club,4));
                        myDeck.add(new Card(R.drawable.five_club,5));
                        myDeck.add(new Card(R.drawable.six_club,6));
                        myDeck.add(new Card(R.drawable.seven_club,7));
                        myDeck.add(new Card(R.drawable.eight_club,8));
                        myDeck.add(new Card(R.drawable.nine_club,9));
                        myDeck.add(new Card(R.drawable.ten_club,10));
                        myDeck.add(new Card(R.drawable.jack_club,10));
                        myDeck.add(new Card(R.drawable.queen_club,10));
                        myDeck.add(new Card(R.drawable.king_club,10));

                        myDeck.add(new Card(R.drawable.ace_spade, 1));
                        myDeck.add(new Card(R.drawable.two_spade,2));
                        myDeck.add(new Card(R.drawable.three_spade,3));
                        myDeck.add(new Card(R.drawable.four_spade,4));
                        myDeck.add(new Card(R.drawable.five_spade,5));
                        myDeck.add(new Card(R.drawable.six_spade,6));
                        myDeck.add(new Card(R.drawable.seven_spade,7));
                        myDeck.add(new Card(R.drawable.eight_spade,8));
                        myDeck.add(new Card(R.drawable.nine_spade,9));
                        myDeck.add(new Card(R.drawable.ten_spade,10));
                        myDeck.add(new Card(R.drawable.jack_spade,10));
                        myDeck.add(new Card(R.drawable.queen_spade,10));
                        myDeck.add(new Card(R.drawable.king_spade,10));

                        myDeck.add(new Card(R.drawable.ace_heart, 1));
                        myDeck.add(new Card(R.drawable.two_heart,2));
                        myDeck.add(new Card(R.drawable.three_heart,3));
                        myDeck.add(new Card(R.drawable.four_heart,4));
                        myDeck.add(new Card(R.drawable.five_heart,5));
                        myDeck.add(new Card(R.drawable.six_heart,6));
                        myDeck.add(new Card(R.drawable.seven_heart,7));
                        myDeck.add(new Card(R.drawable.eight_heart,8));
                        myDeck.add(new Card(R.drawable.nine_heart,9));
                        myDeck.add(new Card(R.drawable.ten_heart,10));
                        myDeck.add(new Card(R.drawable.jack_heart,10));
                        myDeck.add(new Card(R.drawable.queen_heart,10));
                        myDeck.add(new Card(R.drawable.king_heart,10));

                        myDeck.add(new Card(R.drawable.ace_diamond, 1));
                        myDeck.add(new Card(R.drawable.two_diamond,2));
                        myDeck.add(new Card(R.drawable.three_diamond,3));
                        myDeck.add(new Card(R.drawable.four_diamond,4));
                        myDeck.add(new Card(R.drawable.five_diamond,5));
                        myDeck.add(new Card(R.drawable.six_diamond,6));
                        myDeck.add(new Card(R.drawable.seven_diamond,7));
                        myDeck.add(new Card(R.drawable.eight_diamond,8));
                        myDeck.add(new Card(R.drawable.nine_diamond,9));
                        myDeck.add(new Card(R.drawable.ten_diamond,10));
                        myDeck.add(new Card(R.drawable.jack_diamond,10));
                        myDeck.add(new Card(R.drawable.queen_diamond,10));
                        myDeck.add(new Card(R.drawable.king_diamond,10));
                }
        }

}