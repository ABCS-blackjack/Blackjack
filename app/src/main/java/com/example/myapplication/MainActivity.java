package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CREATE OBJECTS
        final Deck singleDeck = new Deck(1);
        Collections.shuffle(singleDeck.myDeck);
        final Player player1 = new Player(singleDeck);
        final Dealer dealer1 = new Dealer(singleDeck);

        //STARTING CARD PLACEMENTS
        final ImageView playerCard0 = findViewById(R.id.playerCardPos0);
        final ImageView playerCard1 = findViewById(R.id.playerCardPos1);
        final ImageView playerCard2 = findViewById(R.id.playerCardPos2);
        final ImageView playerCard3 = findViewById(R.id.playerCardPos3);
        final ImageView playerCard4 = findViewById(R.id.playerCardPos4);
        final ImageView playerCard5 = findViewById(R.id.playerCardPos5);
        final ImageView playerCard6 = findViewById(R.id.playerCardPos6);

        final ImageView dealerCard0 = findViewById(R.id.dealerCardPos0);
        final ImageView dealerCard1 = findViewById(R.id.dealerCardPos1);
        final ImageView dealerCard2 = findViewById(R.id.dealerCardPos2);
        final ImageView dealerCard3 = findViewById(R.id.dealerCardPos3);
        final ImageView dealerCard4 = findViewById(R.id.dealerCardPos4);
        final ImageView dealerCard5 = findViewById(R.id.dealerCardPos5);
        final ImageView dealerCard6 = findViewById(R.id.dealerCardPos6);
        

        //HAND VALUES
        final TextView playerCount = findViewById(R.id.playerCount);
        final TextView dealerCount = findViewById(R.id.dealerCount);

        //BUTTONS
        Button hitButton = findViewById(R.id.buttonHit);
        final Button standButton = findViewById(R.id.buttonStand);
        Button DDButton = findViewById(R.id.buttonDD);
        Button splitButton = findViewById(R.id.buttonSplit);
        final Button startButton = findViewById(R.id.buttonStart);

        //OTHER ACTIVITIES
        ImageButton settingsActivity = findViewById(R.id.imageSettingButton);
        ImageButton analyzeActivity = findViewById(R.id.imageAnalyzeButton);


        //when the player clicks the START button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1.playerHit(playerCard0);
                player1.playerHit(playerCard1);
                playerCount.setText(Integer.toString (player1.getPlayerHandValue()));

                dealer1.dealerHit(dealerCard0);
                dealerCount.setText(Integer.toString(dealer1.getDealerHandValue()));


                startButton.setVisibility(View.GONE);
            }
        });

        //Whe player uses hit
        hitButton.setOnClickListener(new View.OnClickListener() {
            int cardPosition = 2;
            @Override
            public void onClick(View v) {

                    switch (cardPosition) {
                        case 2:
                            player1.playerHit(playerCard2);
                            playerCount.setText(Integer.toString(player1.getPlayerHandValue()));
                            cardPosition++;
                            break;
                        case 3:
                            player1.playerHit(playerCard3);
                            playerCount.setText(Integer.toString(player1.getPlayerHandValue()));
                            cardPosition++;
                            break;
                        case 4:
                            player1.playerHit(playerCard4);
                            playerCount.setText(Integer.toString(player1.getPlayerHandValue()));
                            cardPosition++;
                            break;
                        case 5:
                            player1.playerHit(playerCard5);
                            playerCount.setText(Integer.toString(player1.getPlayerHandValue()));
                            cardPosition++;
                            break;
                        case 6:
                            player1.playerHit(playerCard6);
                            playerCount.setText(Integer.toString(player1.getPlayerHandValue()));
                            cardPosition++;
                            break;
                    }
                if (player1.getPlayerHandValue() > 21) {
                    //fixme: hand end
                }

            }
        });

        //when the player clicks the STAND button
        standButton.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
                player1.playerStand(dealer1);
           }
        });

        //when the player clicks the settings icon
        settingsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(nextPage);
            }
        });

        //when the player clicks the graph icon
        analyzeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(MainActivity.this, AnalyzeActivity.class);
                startActivity(nextPage);
            }
        });
    }
}