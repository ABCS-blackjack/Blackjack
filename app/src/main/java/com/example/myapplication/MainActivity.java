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
        Collections.shuffle(singleDeck.myDeck);

        final ImageView playerCard1 = findViewById(R.id.playerCard1);
        final ImageView dealerHand1 = findViewById(R.id.dealerCard1);
        final ImageView playerCard2 = findViewById(R.id.playerCard2);

        final TextView playerCount = findViewById(R.id.playerCount);

        Button hitButton = findViewById(R.id.buttonHit);
        Button standButton = findViewById(R.id.buttonStand);
        Button DDButton = findViewById(R.id.buttonDD);
        Button splitButton = findViewById(R.id.buttonSplit);
        final Button startButton = findViewById(R.id.buttonStart);

        ImageButton settingsActivity = findViewById(R.id.imageSettingButton);
        ImageButton analyzeActivity = findViewById(R.id.imageAnalyzeButton);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playerCard1.setImageResource(singleDeck.myDeck.get(0).getDrawable());
                playerValue = playerValue + singleDeck.myDeck.get(0).getValue();
                singleDeck.myDeck.remove(0);

                playerCard2.setImageResource(singleDeck.myDeck.get(0).getDrawable());
                playerValue = playerValue + singleDeck.myDeck.get(0).getValue();
                singleDeck.myDeck.remove(0);

                playerCount.setText(String.valueOf(playerValue));

                startButton.setVisibility(View.GONE);
            }
        });

        settingsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(nextPage);
            }
        });

        analyzeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(MainActivity.this, AnalyzeActivity.class);
                startActivity(nextPage);
            }
        });
    }
}