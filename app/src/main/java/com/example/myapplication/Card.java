package com.example.myapplication;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel(Parcel.Serialization.BEAN)
public class Card {

    private Integer drawable;
    private int value;
    public Integer getDrawable() {return drawable;}
    public Integer getValue() {return value;}

    @ParcelConstructor
    public Card() {
    }

    public Card(Integer drawable, int value) {
        this.drawable = drawable;
        this.value = value;
    }

}