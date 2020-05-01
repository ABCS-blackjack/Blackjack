package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.jjoe64.graphview.RectD;

import org.parceler.Parcel;
import org.parceler.Parcels;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {


    //STARTING CARD PLACEMENTS
    private ImageView playerCard0;
    private ImageView playerCard1;
    private ImageView playerCard2;
    private ImageView playerCard3;
    private ImageView playerCard4;
    private ImageView playerCard5;
    private ImageView playerCard6;

    private ImageView playerCardDown;

    private ImageView dealerCard0;
    private ImageView dealerCard1;
    private ImageView dealerCard2;
    private ImageView dealerCard3;
    private ImageView dealerCard4;
    private ImageView dealerCard5;
    private ImageView dealerCard6;

    //HAND VALUES
    private TextView playerCount;
    private TextView dealerCount;
    private TextView shoeCurrentCount;
    private TextView analyzeCount;
    private TextView winLossTieCount;

    //BUTTONS
    private Button hitButton;
    private Button standButton;
    private Button doubleDownButton;
    private Button splitButton;
    private Button startButton;
    private Button redealButton;
    private Button reshuffleButton;

    //OTHER ACTIVITIES
    private ImageButton settingsActivity;
    private ImageButton analyzeActivity;
    private ImageButton showShoeButton;

    //DECK OBJECT
    private Deck singleDeck;
    final private int NUMBER_OF_DECKS = 6;

    //PLAYER AND DEALER OBJECT
    private Player player1;
    private Dealer dealer1;

    //PARCELABLES
    Parcelable deckParcel;
    Parcelable playerParcel;
    Parcelable dealerParcel;
    Parcelable countParcel;

    //SAVE
    int pCard0;
    int pCard1;
    int pCard2;
    int pCard3;
    int pCard4;
    int pCard5;
    int pCard6;

    int dCard0;
    int dCard1;
    int dCard2;
    int dCard3;
    int dCard4;
    int dCard5;
    int dCard6;

    //MISC
    private int playerCardPos = 2;
    private int dealerCardPos = 2;
    private AnalyzeCount currentCount = new AnalyzeCount(0);
    private int win = 0, loss = 0;
    Snackbar popUp;
    private View myPopUp;
    
    private Bundle mySaveState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySaveState = savedInstanceState;

        //CREATE OBJECTS
        singleDeck = new Deck(NUMBER_OF_DECKS);
        Collections.shuffle(singleDeck.myDeck);
        shoeCurrentCount = findViewById(R.id.shoeCount);
        shoeCurrentCount.setText("Cards left: " + Integer.toString(singleDeck.myDeck.size()));  //fixme:test
        player1 = new Player(singleDeck);
        dealer1 = new Dealer(singleDeck);


        //SET CARD FACES
        playerCard0 = findViewById(R.id.playerCardPos0);
        playerCard1 = findViewById(R.id.playerCardPos1);
        playerCard2 = findViewById(R.id.playerCardPos2);
        playerCard3 = findViewById(R.id.playerCardPos3);
        playerCard4 = findViewById(R.id.playerCardPos4);
        playerCard5 = findViewById(R.id.playerCardPos5);
        playerCard6 = findViewById(R.id.playerCardPos6);

        playerCardDown = findViewById(R.id.playerDoubleDownCard);

        dealerCard0 = findViewById(R.id.dealerCardPos0);
        dealerCard1 = findViewById(R.id.dealerCardPos1);
        dealerCard2 = findViewById(R.id.dealerCardPos2);
        dealerCard3 = findViewById(R.id.dealerCardPos3);
        dealerCard4 = findViewById(R.id.dealerCardPos4);
        dealerCard5 = findViewById(R.id.dealerCardPos5);
        dealerCard6 = findViewById(R.id.dealerCardPos6);

        //SET CARD COUNT STARTING AT 0
        playerCount = findViewById(R.id.playerCount);
        dealerCount = findViewById(R.id.dealerCount);
        analyzeCount = findViewById(R.id.analyzeCount);
        analyzeCount.setText("Count: " + Integer.toString(currentCount.getValue()));
        winLossTieCount = findViewById(R.id.winLossTie);

        //SET BUTTONS
        hitButton = findViewById(R.id.buttonHit);
        standButton = findViewById(R.id.buttonStand);
        doubleDownButton = findViewById(R.id.buttonDD);
        splitButton = findViewById(R.id.buttonSplit);
        startButton = findViewById(R.id.buttonStart);
        redealButton = findViewById(R.id.buttonRedeal);
        reshuffleButton = findViewById(R.id.buttonReshuffle);

        myPopUp = findViewById(R.id.dealerPopUp);

        //SET THE PAGE BUTTONS
        settingsActivity = findViewById(R.id.imageSettingButton);
        analyzeActivity = findViewById(R.id.imageAnalyzeButton);
        showShoeButton = findViewById(R.id.gameShoeButton);

        //when the player clicks the START button
        startButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                int playerLeft = singleDeck.myDeck.get(0).getValue();
                int playerRight = singleDeck.myDeck.get(1).getValue();

                pCard0 = singleDeck.myDeck.get(0).getDrawable();
                pCard1 = singleDeck.myDeck.get(1).getDrawable();
                dCard0 = singleDeck.myDeck.get(2).getDrawable();
                dCard1 = R.drawable.gray_back;



                player1.playerHit(playerCard0, currentCount);
                player1.playerHit(playerCard1, currentCount);
                playerCount.setText(Integer.toString(player1.getPlayerHandValue()));

                dealer1.dealerHit(dealerCard0, currentCount);
                dealerCount.setText(Integer.toString(dealer1.getDealerHandValue()));
                dealer1.dealerHitBottom(dealerCard1);

                if (dealer1.dealerHas21() || player1.playerHas21()) {
                    endHand();
                }else {
                    startButton.setVisibility(View.GONE);
                    hitButton.setVisibility(View.VISIBLE);
                    standButton.setVisibility(View.VISIBLE);
                    if (playerLeft == playerRight) {
                        splitButton.setVisibility(View.VISIBLE);
                    }
                    if (player1.getPlayerHandValue() <= 11) {
                        doubleDownButton.setVisibility(View.VISIBLE);
                    }
                }
                analyzeCount.setText("Count: " + Integer.toString(currentCount.getValue()));
                shoeCurrentCount.setText("Cards left: " + Integer.toString(singleDeck.myDeck.size()));  //fixme:test
/*
                //SharedPreferences sharedPreferencesPro = getSharedPreferences(" ", MODE_PRIVATE);
                SharedPreferences sharedPreferencesDD = getSharedPreferences(" ", MODE_PRIVATE);
                SharedPreferences sharedPreferencesSpl = getSharedPreferences(" ", MODE_PRIVATE);
                //final boolean probVal = sharedPreferencesPro.getBoolean(getString(R.string.Probability), false);
                final boolean DDVal = sharedPreferencesDD.getBoolean(getString(R.string.double_down), false);
                final boolean SplVal = sharedPreferencesSpl.getBoolean(getString(R.string.split), false);

                if ((DDVal == true) && (SplVal == true)) {
                    doubleDownButton.setVisibility(View.VISIBLE);
                    splitButton.setVisibility(View.VISIBLE);
                } else if ((DDVal == true) && (SplVal == false)) {
                    doubleDownButton.setVisibility(View.VISIBLE);
                    splitButton.setVisibility(View.GONE);
                } else if ((DDVal == false) && (SplVal == true)) {
                    doubleDownButton.setVisibility(View.GONE);
                    splitButton.setVisibility(View.VISIBLE);
                } else {
                    doubleDownButton.setVisibility(View.GONE);
                    splitButton.setVisibility(View.GONE);
                }
                */
            }

        });


        //When player uses hit
        hitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                doubleDownButton.setVisibility(View.GONE);
                splitButton.setVisibility(View.GONE);

                switch (playerCardPos) {
                    case 2:
                        pCard2 = singleDeck.myDeck.get(0).getDrawable();
                        player1.playerHit(playerCard2, currentCount);
                        analyzeCount.setText("Count: " + Integer.toString(currentCount.getValue()));
                        playerCount.setText(Integer.toString(player1.getPlayerHandValue()));
                        playerCardPos++;
                        break;
                    case 3:
                        pCard3 = singleDeck.myDeck.get(0).getDrawable();
                        player1.playerHit(playerCard3, currentCount);
                        analyzeCount.setText("Count: " + Integer.toString(currentCount.getValue()));
                        playerCount.setText(Integer.toString(player1.getPlayerHandValue()));
                        playerCardPos++;
                        break;
                    case 4:
                        pCard4 = singleDeck.myDeck.get(0).getDrawable();
                        player1.playerHit(playerCard4, currentCount);
                        analyzeCount.setText("Count: " + Integer.toString(currentCount.getValue()));
                        playerCount.setText(Integer.toString(player1.getPlayerHandValue()));
                        playerCardPos++;
                        break;
                    case 5:
                        pCard5 = singleDeck.myDeck.get(0).getDrawable();
                        player1.playerHit(playerCard5, currentCount);
                        analyzeCount.setText("Count: " + Integer.toString(currentCount.getValue()));
                        playerCount.setText(Integer.toString(player1.getPlayerHandValue()));
                        playerCardPos++;
                        break;
                    case 6:
                        pCard6 = singleDeck.myDeck.get(0).getDrawable();
                        player1.playerHit(playerCard6, currentCount);
                        playerCardPos++;
                        break;
                }
                if (player1.isPlayerBust()) {
                    dCard1 = dealer1.getDealerBottomCard().getDrawable();
                    dealerCard1.setImageResource(dealer1.getDealerBottomCard().getDrawable());
                    endHand();
                }
            }

        });

        splitButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Split functionality in development!", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        //When the player clicks the DOUBLE DOWN button
        doubleDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hitButton.setVisibility(View.GONE);
                standButton.setVisibility(View.GONE);
                splitButton.setVisibility(View.GONE);
                doubleDownButton.setVisibility(View.GONE);

                player1.playerDoubleDown(playerCardDown, currentCount);
                analyzeCount.setText("Count: " + Integer.toString(currentCount.getValue()));
                shoeCurrentCount.setText("Cards left: " + Integer.toString(singleDeck.myDeck.size()));  //fixme:test
                playerCount.setText(Integer.toString(player1.getPlayerHandValue()));
                standButton.performClick();


            }
        });

        //when the player clicks the STAND button
        standButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                hitButton.setVisibility(View.GONE);
                standButton.setVisibility(View.GONE);
                splitButton.setVisibility(View.GONE);
                doubleDownButton.setVisibility(View.GONE);

                dCard1 = dealer1.getDealerBottomCard().getDrawable();
                dealerCard1.setImageResource(dealer1.getDealerBottomCard().getDrawable());

                if (dealer1.getDealerBottomCard().getValue() <= 6 && dealer1.getDealerBottomCard().getValue() >= 2) {
                    currentCount.add();
                }else if (dealer1.getDealerBottomCard().getValue() >= 10 || dealer1.getDealerBottomCard().getValue() == 1) {
                    currentCount.sub();
                }
                analyzeCount.setText("Count: " + Integer.toString(currentCount.getValue()));
                dealerCount.setText(Integer.toString(dealer1.getDealerHandValue()));

                while (dealer1.getDealerHandValue() < 17) {
                    switch (dealerCardPos) {
                        case 2:
                            dCard2 = singleDeck.myDeck.get(0).getDrawable();
                            dealer1.dealerHit(dealerCard2, currentCount);
                            dealerCount.setText(Integer.toString(dealer1.getDealerHandValue()));
                            dealerCardPos++;
                            break;
                        case 3:
                            dCard3 = singleDeck.myDeck.get(0).getDrawable();
                            dealer1.dealerHit(dealerCard3, currentCount);
                            dealerCount.setText(Integer.toString(dealer1.getDealerHandValue()));
                            dealerCardPos++;
                            break;
                        case 4:
                            dCard4 = singleDeck.myDeck.get(0).getDrawable();
                            dealer1.dealerHit(dealerCard4, currentCount);
                            dealerCount.setText(Integer.toString(dealer1.getDealerHandValue()));
                            dealerCardPos++;
                            break;
                        case 5:
                            dCard5 = singleDeck.myDeck.get(0).getDrawable();
                            dealer1.dealerHit(dealerCard5, currentCount);
                            dealerCount.setText(Integer.toString(dealer1.getDealerHandValue()));
                            dealerCardPos++;
                            break;
                        case 6:
                            dCard6 = singleDeck.myDeck.get(0).getDrawable();
                            dealer1.dealerHit(dealerCard6, currentCount);
                            dealerCount.setText(Integer.toString(dealer1.getDealerHandValue()));
                            dealerCardPos++;
                            break;
                    }
                }
                endHand();
            }
        });

        // restart the board after a hand is finished
        redealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBoard();
                player1.playerReset();
                dealer1.dealerReset();
                redealButton.setVisibility(View.GONE);
                startButton.performClick();
            }
        });

        //reshuffle the deck
        reshuffleButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                clearBoard();
                player1.playerReset();
                dealer1.dealerReset();
                shoeReset();
                reshuffleButton.setVisibility(View.GONE);
                startButton.performClick();


            }
        });


        //shows what is left in the shoe when clicked
        showShoeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

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

    private void shoeReset() {
        //CREATE OBJECTS
        singleDeck = new Deck(NUMBER_OF_DECKS);
        Collections.shuffle(singleDeck.myDeck);
        shoeCurrentCount = findViewById(R.id.shoeCount);
        shoeCurrentCount.setText("Cards left: " + Integer.toString(singleDeck.myDeck.size()));  //fixme:test
        player1 = new Player(singleDeck);
        dealer1 = new Dealer(singleDeck);
        currentCount = new AnalyzeCount(0);
    }

    private void clearBoard() {
        //SET CARD FACES
        playerCard0.setImageResource(R.drawable.gray_back);
        playerCard1.setImageResource(R.drawable.gray_back);
        playerCard2.setVisibility(View.GONE);
        playerCard3.setVisibility(View.GONE);
        playerCard4.setVisibility(View.GONE);
        playerCard5.setVisibility(View.GONE);
        playerCard6.setVisibility(View.GONE);

        playerCardDown.setVisibility(View.GONE);

        dealerCard0.setImageResource(R.drawable.gray_back);
        dealerCard1.setImageResource(R.drawable.gray_back);
        dealerCard2.setVisibility(View.GONE);
        dealerCard3.setVisibility(View.GONE);
        dealerCard4.setVisibility(View.GONE);
        dealerCard5.setVisibility(View.GONE);
        dealerCard6.setVisibility(View.GONE);


        playerCardPos = 2;
        dealerCardPos = 2;
        //SET CARD COUNT STARTING AT
        playerCount.setText("0");
        dealerCount.setText("0");
        analyzeCount.setText("0");

    }

    private void endHand() {

        if(player1.getPlayerHandValue() > 21) {
            popUp.make(myPopUp, "Dealer is the Winner", Snackbar.LENGTH_SHORT).show();
            loss++;

        }
        else if (dealer1.getDealerHandValue() > 21) {
            popUp.make(myPopUp, "You're the Winner", Snackbar.LENGTH_SHORT).show();
            win++;

        }
        else if (player1.getPlayerHandValue() == dealer1.getDealerHandValue()) {
            popUp.make(myPopUp, "Tie", Snackbar.LENGTH_SHORT).show();

        }
        else {
            if (player1.getPlayerHandValue() > dealer1.getDealerHandValue()) {
                popUp.make(myPopUp, "You're the Winner", Snackbar.LENGTH_SHORT).show();
                win++;
            } else {
                popUp.make(myPopUp, "Dealer is the Winner", Snackbar.LENGTH_SHORT).show();
                loss++;
            }

        }

        dealerCard1.setImageResource(dealer1.getDealerBottomCard().getDrawable());
        if (dealer1.getDealerBottomCard().getValue() <= 6 && dealer1.getDealerBottomCard().getValue() >= 2) {
            currentCount.add();
        }else if (dealer1.getDealerBottomCard().getValue() >= 10 || dealer1.getDealerBottomCard().getValue() == 1) {
            currentCount.sub();
        }
        winLossTieCount.setText(" " +win + "/" + loss);
        analyzeCount.setText("Count: " + Integer.toString(currentCount.getValue()));
        dealerCount.setText(Integer.toString(dealer1.getDealerHandValue()));

        redealButton.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.GONE);
        hitButton.setVisibility(View.GONE);
        standButton.setVisibility(View.GONE);
        splitButton.setVisibility(View.GONE);
        doubleDownButton.setVisibility(View.GONE);
    }

    /**Saves values in current view to bundle outState*/
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Toast.makeText(this, "saving state", Toast.LENGTH_SHORT).show();

        deckParcel = Parcels.wrap(singleDeck.myDeck);
        outState.putParcelable("theDeck", deckParcel);

        playerParcel = Parcels.wrap(player1);
        outState.putParcelable("thePlayer", playerParcel);

        dealerParcel = Parcels.wrap(dealer1);
        outState.putParcelable("theDealer", dealerParcel);

        countParcel = Parcels.wrap(currentCount);
        outState.putParcelable("trueCount", countParcel);

        outState.putBundle("myBundle", mySaveState);

        outState.putInt("pCard0", pCard0);
        outState.putInt("pCard1", pCard1);
        outState.putInt("pCard2", pCard2);
        outState.putInt("pCard3", pCard3);
        outState.putInt("pCard4", pCard4);
        outState.putInt("pCard5", pCard5);
        outState.putInt("pCard6", pCard6);

        outState.putInt("dCard0", dCard0);
        outState.putInt("dCard1", dCard1);
        outState.putInt("dCard2", dCard2);
        outState.putInt("dCard3", dCard3);
        outState.putInt("dCard4", dCard4);
        outState.putInt("dCard5", dCard5);
        outState.putInt("dCard6", dCard6);

        outState.putInt("startButton", startButton.getVisibility());
        outState.putInt("hitButton", hitButton.getVisibility());
        outState.putInt("standButton", standButton.getVisibility());
        outState.putInt("doubleDownButton", doubleDownButton.getVisibility());
        outState.putInt("splitButton", splitButton.getVisibility());
        outState.putInt("redealButton", redealButton.getVisibility());
        outState.putInt("reshuffleButton", reshuffleButton.getVisibility());
        
        outState.putInt("pCardVis2", playerCard2.getVisibility());
        outState.putInt("pCardVis3", playerCard3.getVisibility());
        outState.putInt("pCardVis4", playerCard4.getVisibility());
        outState.putInt("pCardVis5", playerCard5.getVisibility());
        outState.putInt("pCardVis6", playerCard6.getVisibility());

        outState.putInt("dCardVis2", dealerCard2.getVisibility());
        outState.putInt("dCardVis3", dealerCard3.getVisibility());
        outState.putInt("dCardVis4", dealerCard4.getVisibility());
        outState.putInt("dCardVis5", dealerCard5.getVisibility());
        outState.putInt("dCardVis6", dealerCard6.getVisibility());

        outState.putInt("winCount", win);
        outState.putInt("lossCount", loss);

        outState.putString("playerCount", playerCount.getText().toString());
        outState.putString("dealerCount", dealerCount.getText().toString());
        outState.putString("shoeCount", shoeCurrentCount.getText().toString());
        outState.putString("analyzeCount", analyzeCount.getText().toString());

        super.onSaveInstanceState(outState);

    }

    /**Restores values in current bundle to the current view*/
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //Toast.makeText(this, "restoring state", Toast.LENGTH_SHORT).show();

        deckParcel = savedInstanceState.getParcelable("theDeck");
        singleDeck.myDeck = Parcels.unwrap(deckParcel);

        playerParcel = savedInstanceState.getParcelable("thePlayer");
        player1 = Parcels.unwrap(playerParcel);

        dealerParcel = savedInstanceState.getParcelable("theDealer");
        dealer1 = Parcels.unwrap(dealerParcel);

        countParcel = savedInstanceState.getParcelable("trueCount");
        currentCount = Parcels.unwrap(countParcel);

        pCard0 = savedInstanceState.getInt("pCard0");
        playerCard0.setImageResource(pCard0);
        pCard1 = savedInstanceState.getInt("pCard1");
        playerCard1.setImageResource(pCard1);
        pCard2 = savedInstanceState.getInt("pCard2");
        playerCard2.setImageResource(pCard2);
        pCard3 = savedInstanceState.getInt("pCard3");
        playerCard3.setImageResource(pCard3);
        pCard4 = savedInstanceState.getInt("pCard4");
        playerCard4.setImageResource(pCard4);
        pCard5 = savedInstanceState.getInt("pCard5");
        playerCard5.setImageResource(pCard5);
        pCard6 = savedInstanceState.getInt("pCard6");
        playerCard6.setImageResource(pCard6);

        dCard0 = savedInstanceState.getInt("dCard0");
        dealerCard0.setImageResource(dCard0);
        dCard1 = savedInstanceState.getInt("dCard1");
        dealerCard1.setImageResource(dCard1);
        dCard2 = savedInstanceState.getInt("dCard2");
        dealerCard2.setImageResource(dCard2);
        dCard3 = savedInstanceState.getInt("dCard3");
        dealerCard3.setImageResource(dCard3);
        dCard4 = savedInstanceState.getInt("dCard4");
        dealerCard4.setImageResource(dCard4);
        dCard5 = savedInstanceState.getInt("dCard5");
        dealerCard5.setImageResource(dCard5);
        dCard6 = savedInstanceState.getInt("dCard6");
        dealerCard6.setImageResource(dCard6);

        startButton.setVisibility(savedInstanceState.getInt("startButton"));
        hitButton.setVisibility(savedInstanceState.getInt("hitButton"));
        standButton.setVisibility(savedInstanceState.getInt("standButton"));
        doubleDownButton.setVisibility(savedInstanceState.getInt("doubleDownButton"));
        splitButton.setVisibility(savedInstanceState.getInt("splitButton"));
        redealButton.setVisibility(savedInstanceState.getInt("redealButton"));
        reshuffleButton.setVisibility(savedInstanceState.getInt("reshuffleButton"));
        
        playerCard2.setVisibility(savedInstanceState.getInt("pCardVis2"));
        playerCard3.setVisibility(savedInstanceState.getInt("pCardVis3"));
        playerCard4.setVisibility(savedInstanceState.getInt("pCardVis4"));
        playerCard5.setVisibility(savedInstanceState.getInt("pCardVis5"));
        playerCard6.setVisibility(savedInstanceState.getInt("pCardVis6"));

        dealerCard2.setVisibility(savedInstanceState.getInt("dCardVis2"));
        dealerCard3.setVisibility(savedInstanceState.getInt("dCardVis3"));
        dealerCard4.setVisibility(savedInstanceState.getInt("dCardVis4"));
        dealerCard5.setVisibility(savedInstanceState.getInt("dCardVis5"));
        dealerCard6.setVisibility(savedInstanceState.getInt("dCardVis6"));

        win = savedInstanceState.getInt("win");
        loss = savedInstanceState.getInt("loss");
        winLossTieCount.setText(win + "/" + loss);



        playerCount.setText(savedInstanceState.getString("playerCount"));
        dealerCount.setText(savedInstanceState.getString("dealerCount"));
        shoeCurrentCount.setText(savedInstanceState.getString("shoeCount"));
        analyzeCount.setText(savedInstanceState.getString("analyzeCount"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Toast.makeText(this, "resuming state", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //Toast.makeText(this, "on pause", Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(this, "on start", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        //Toast.makeText(this, "on stop", Toast.LENGTH_SHORT).show();
    }
};


