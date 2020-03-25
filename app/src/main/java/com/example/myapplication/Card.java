package com.example.myapplication;

public class Card {

    private String value;
    private String suit;

    public Card() {
        value = "empty";
        suit = "empty";
    }

    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }



    public void createCard(String v, String s) {
        value = v;
        suit = s;

    }

}