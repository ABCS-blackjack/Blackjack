package com.example.myapplication;

public class Card {

    private Integer drawable;
    private int value;
    public Integer getDrawable() {return drawable;}
    public Integer getValue() {return value;}


    public Card() {
    }

    public Card(Integer drawable, int value) {
        this.drawable = drawable;
        this.value = value;
    }

}