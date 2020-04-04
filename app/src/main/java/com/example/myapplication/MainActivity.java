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

    Integer playerValue = 0;
    Integer dealerValue = 0;
    Card dealerBottomCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Deck singleDeck = new Deck(1);
        Collections.shuffle(singleDeck.myDeck);


        final ImageView playerCard1 = findViewById(R.id.playerCard1);
        final ImageView playerCard2 = findViewById(R.id.playerCard2);
        final ImageView dealerCard1 = findViewById(R.id.dealerCard1);
        final ImageView dealerCard2 = findViewById(R.id.dealerCard2);


        final TextView playerCount = findViewById(R.id.playerCount);
        final TextView dealerCount = findViewById(R.id.dealerCount);

        Button hitButton = findViewById(R.id.buttonHit);
        Button standButton = findViewById(R.id.buttonStand);
        Button DDButton = findViewById(R.id.buttonDD);
        Button splitButton = findViewById(R.id.buttonSplit);
        final Button startButton = findViewById(R.id.buttonStart);

        ImageButton settingsActivity = findViewById(R.id.imageSettingButton);
        ImageButton analyzeActivity = findViewById(R.id.imageAnalyzeButton);


        //when the player clicks the START button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerCard1.setImageResource(singleDeck.myDeck.get(0).getDrawable());
                playerValue = playerValue + singleDeck.myDeck.get(0).getValue();
                singleDeck.myDeck.remove(0);

                dealerCard1.setImageResource(singleDeck.myDeck.get(0).getDrawable());
                dealerValue = dealerValue + singleDeck.myDeck.get(0).getValue();
                singleDeck.myDeck.remove(0);

                playerCard2.setImageResource(singleDeck.myDeck.get(0).getDrawable());
                playerValue = playerValue + singleDeck.myDeck.get(0).getValue();
                singleDeck.myDeck.remove(0);

                //Dealers face down card is not shown until player stands
                dealerBottomCard = singleDeck.myDeck.get(0);
                //dealercard2.setImageResource(singleDeck.myDeck.get(0).getDrawable());
                dealerValue = dealerValue + singleDeck.myDeck.get(0).getValue();
                singleDeck.myDeck.remove(0);

                playerCount.setText(String.valueOf(playerValue));


                startButton.setVisibility(View.GONE);
            }
        });

        //when the player clicks the STAND button
        standButton.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {

               //turn the bottom card over
               dealerCard2.setImageResource(dealerBottomCard.getDrawable());


               while (dealerValue < 17) {

                   ImageView newDealerCard = findViewById(R.id.newDealerCard);
                   newDealerCard.setImageResource(singleDeck.myDeck.get(0).getDrawable());

                   Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
                   newDealerCard.startAnimation(animation1);

                   dealerValue = dealerValue + singleDeck.myDeck.get(0).getValue();
                   dealerCount.setText(String.valueOf(dealerValue));
                   singleDeck.myDeck.remove(0);
               }
               dealerCount.setText(String.valueOf(dealerValue));

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