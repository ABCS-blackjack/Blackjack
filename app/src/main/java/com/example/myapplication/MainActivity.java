package com.example.myapplication;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView testImage = findViewById(R.id.playerCard);
        Button test = findViewById(R.id.buttonTest);

                final Deck singleDeck = new Deck();
                singleDeck.createDeck(1);
                Collections.shuffle (singleDeck.myDeck);

                //Bitmap currCard = ;


                        test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        //      Drawable.createFromPath(singleDeck.getValue());
                        testImage.setImageResource(R.drawable.nine_club);
                }
        });

        }
        /*
        public static void main(String[] args) {

                Deck singleDeck = new Deck();
                singleDeck.createDeck(1);
                Collections.shuffle (singleDeck.myDeck);
                for (Card i:singleDeck.myDeck)
                {System.out.println (i.getValue() + i.getSuit());}
        }
        */

}