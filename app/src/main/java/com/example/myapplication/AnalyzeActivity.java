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
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public class AnalyzeActivity extends AppCompatActivity {

    private TextView gamesText;
    private TextView bustText;
    private TextView num21Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);

        gamesText = findViewById(R.id.gamesText);
        bustText = findViewById(R.id.bustText);
        num21Text = findViewById(R.id.num21Text);

        BlackjackDatabase db = BlackjackDatabase.getDatabase(getApplicationContext());

        gamesText.setText("Games Played: " + Integer.toString(db.playerDao().getNumGames()));
        bustText.setText("Busts: " + Integer.toString(db.playerDao().getNumBusts()));
        num21Text.setText("Blackjacks: " + Integer.toString(db.playerDao().getNum21()));

        ImageButton mainActivity = findViewById(R.id.imageHomeButton);
        mainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent nextPage = new Intent(AnalyzeActivity.this, MainActivity.class);
//                startActivity(nextPage);
                finish();
                return;
            }
        });

        int xValue = db.playerDao().getNumHits();

        GraphView graph = findViewById(R.id.probabilityGraph);


        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 0),
                new DataPoint(1, 2.5),
                new DataPoint(2, 8),
                new DataPoint(3, 22.75),
                new DataPoint(4, 50.43),
                new DataPoint(5, 92.7),
        });
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 0),
                new DataPoint(1, 2),
                new DataPoint(2, 5),
                new DataPoint(3, 12),
                new DataPoint(4, 28),
                new DataPoint(5, 33),
        });
        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>(new DataPoint[]{});
        for (int i = 1; i <= xValue; i++) {
            series3.appendData(new DataPoint(i, i*20), false, i, false);
        }
        series3.setDrawDataPoints(true);

        series1.setTitle("Bust %");
        series2.setTitle("21 %");
        series1.setColor(Color.RED);
        series2.setColor(Color.BLACK);
        series3.setColor(Color.BLUE);

        //graph.addSeries(series1);
        //graph.addSeries(series2);
        //if (!graph.getSeries().isEmpty()) graph.removeAllSeries();
        graph.addSeries(series3);

        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(xValue);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(100);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
    }
}
