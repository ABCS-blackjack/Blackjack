package com.example.myapplication;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel(Parcel.Serialization.BEAN)
public class AnalyzeCount {
    public Integer value;

    @ParcelConstructor
    AnalyzeCount() {}

    AnalyzeCount(Integer value) {
        this.value = value;
    }
    public void add() {value++;}
    public void sub() {value--;}
    public Integer getValue() {return value;}
}