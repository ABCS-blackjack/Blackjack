package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

        Integer playerValue = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Deck singleDeck = new Deck(1);
        Collections.shuffle (singleDeck.myDeck);

        final ImageView playerHand = findViewById(R.id.playerCard);
        final TextView playerCount = findViewById(R.id.playerCount);
        Button test = findViewById(R.id.buttonTest);

        test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        playerHand.setImageResource(singleDeck.myDeck.get(0).getDrawable());
                        playerValue = playerValue + singleDeck.myDeck.get(0).getValue();
                        playerCount.setText(String.valueOf(playerValue));
                        singleDeck.myDeck.remove(0);

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