package com.example.myapplication;

import java.util.ArrayList;

public class Deck extends Card {

	final private String[] values = {"ace", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king"};
	final private String[] suits = {"_spade", "_heart", "_diamond", "_club"};
	ArrayList<Card> myDeck;

	public Deck() {
		myDeck = new ArrayList<Card>();
	}

	public void createDeck(int numOfDecks) {

		for (int c = 0; c < numOfDecks; c++) {
			for (int i = 0; i < suits.length; i++) {
				for (int j = 0; j < values.length; j++) {
					Card newCard = new Card();
					newCard.createCard(values[j], suits[i]);
					myDeck.add(newCard);
				}
			}
		}
	}
}