package com.example.myapplication;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
                        //Drawable.createFromPath(singleDeck.getValue());
                        testImage.setImageResource(R.drawable.nine_club);
                }
        });


                ImageButton settingsActivity = findViewById(R.id.imageSettingButton);
                settingsActivity.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Intent nextPage = new Intent(MainActivity.this, SettingsActivity.class);
                                startActivity(nextPage);
                        }
                });

                ImageButton analyzeActivity = findViewById(R.id.imageAnalyzeButton);
                analyzeActivity.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Intent nextPage = new Intent(MainActivity.this, AnalyzeActivity.class);
                                startActivity(nextPage);
                        }
                });
        }
}