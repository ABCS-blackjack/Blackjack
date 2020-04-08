package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final ImageButton mainActivity = findViewById(R.id.imageHomeButton);
        mainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(nextPage);
            }
        });

        final boolean[] switchState1 = new boolean[1];
        final boolean[] switchState2 = new boolean[1];
        final boolean[] switchState3 = new boolean[1];
        final Button mainDD = findViewById(R.id.buttonDD);
        final Button mainSplit = findViewById(R.id.buttonSplit);

        final SharedPreferences preferences = getSharedPreferences("PREF", 0);
        switchState1[0] = preferences.getBoolean("switch1", false);
        switchState2[0] = preferences.getBoolean("switch2", false);
        switchState3[0] = preferences.getBoolean("switch3", false);

        final Switch switch_1 = findViewById(R.id.switch1);
        final Switch switch_2 = findViewById(R.id.switch2);
        final Switch switch_3 = findViewById(R.id.switch3);

        switch_1.setChecked(switchState1[0]);
        switch_2.setChecked(switchState2[0]);
        switch_3.setChecked(switchState3[0]);

        switch_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchState1[0] = !switchState1[0];
                switch_1.setChecked(switchState1[0]);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switch1", switchState1[0]);
                editor.apply();
            }
        });

        switch_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchState2[0] = !switchState2[0];
                switch_2.setChecked(switchState2[0]);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switch2", switchState2[0]);
                editor.apply();
            }
        });

        switch_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchState3[0] = !switchState3[0];
                switch_3.setChecked(switchState3[0]);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switch3", switchState3[0]);
                editor.apply();
            }
        });
    }
}
