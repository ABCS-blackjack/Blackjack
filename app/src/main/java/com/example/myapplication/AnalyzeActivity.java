package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AnalyzeActivity extends AppCompatActivity {

    private TextView gamesText;
    private TextView recordText;
    private TextView bustText;
    private TextView num21Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);

        gamesText = findViewById(R.id.gamesText);
        recordText = findViewById(R.id.recordText);
        bustText = findViewById(R.id.bustText);
        num21Text = findViewById(R.id.num21Text);

        BlackjackDatabase db = BlackjackDatabase.getDatabase(getApplicationContext());

        gamesText.setText("Hand Number: " + Integer.toString(db.playerDao().getNumGames()));
        recordText.setText("Record (W-L-T): " + Integer.toString(db.playerDao().getNumWins()) +
                "-" + Integer.toString(db.playerDao().getNumLosses()) +
                "-" + Integer.toString(db.playerDao().getNumTies()));
        bustText.setText("Times Busted: " + Integer.toString(db.playerDao().getNumBusts()));
        num21Text.setText("Blackjacks: " + Integer.toString(db.playerDao().getNum21()));

        ImageButton mainActivity = findViewById(R.id.imageHomeButton);
        ImageButton refresh = findViewById(R.id.refresh);
        mainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent nextPage = new Intent(AnalyzeActivity.this, MainActivity.class);
//                startActivity(nextPage);
                finish();
                return;
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlackjackDatabase tempDB = BlackjackDatabase.getDatabase(getApplicationContext());
                Player player = new Player();
                player.bustChanceString = tempDB.playerDao().getBustChanceString();
                tempDB.playerDao().insertPlayer(player);
                gamesText.setText("Hand Number: " + Integer.toString(tempDB.playerDao().getNumGames()));
                recordText.setText("Record (W-L-T): " + Integer.toString(tempDB.playerDao().getNumWins()) +
                        "-" + Integer.toString(tempDB.playerDao().getNumLosses()) +
                        "-" + Integer.toString(tempDB.playerDao().getNumTies()));
                bustText.setText("Times Busted: " + Integer.toString(tempDB.playerDao().getNumBusts()));
                num21Text.setText("Blackjacks: " + Integer.toString(tempDB.playerDao().getNum21()));
            }
        });

        GraphView graph = findViewById(R.id.probabilityGraph);
        graph.setTitle("Probability");
        graph.setTitleTextSize(65);

        Double dealerBustChance = db.dealerDao().getDealerBustChance();
        String bustChanceString = db.playerDao().getBustChanceString();
        String[] bustChanceArray = bustChanceString.split(", ");
        int xValue = bustChanceArray.length;
        Double yValue = 0.0;

        if (!bustChanceArray[0].equals("0.0")) {
            LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>(new DataPoint[]{});
            LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[]{});

            for (int i = 1; i <= xValue; i++) {
                String doubleString = bustChanceArray[i - 1];
                if (i - 1 == 0) {
                    yValue = Double.parseDouble(doubleString.substring(1, doubleString.length() - 1));
                } else if (i - 1 == xValue - 1) {
                    yValue = Double.parseDouble(doubleString.substring(0, doubleString.length() - 2));
                } else {
                    yValue = Double.parseDouble(bustChanceArray[i - 1]);
                }
                series1.appendData(new DataPoint(i, yValue), false, i, false);
                series2.appendData(new DataPoint(i, dealerBustChance), false, i, false);
            }

            series1.setTitle("Player Bust %");
            series1.setColor(Color.RED);
            series1.setDrawDataPoints(true);
            series1.setDataPointsRadius(15);

            series2.setTitle("Dealer Bust %");
            series2.setColor(Color.BLACK);
            series2.setDrawDataPoints(true);
            series2.setDataPointsRadius(15);

            graph.addSeries(series1);
            graph.addSeries(series2);

            LegendRenderer legend = graph.getLegendRenderer();
            legend.setAlign(LegendRenderer.LegendAlign.BOTTOM);
            legend.setVisible(true);
        }

        Viewport view = graph.getViewport();
        view.setMinX(1);
        view.setMaxX(xValue);
        view.setMinY(0);
        view.setMaxY(100);

        view.setXAxisBoundsManual(true);
        view.setYAxisBoundsManual(true);

        GridLabelRenderer label = graph.getGridLabelRenderer();
        label.setNumHorizontalLabels(xValue);
    }
}
