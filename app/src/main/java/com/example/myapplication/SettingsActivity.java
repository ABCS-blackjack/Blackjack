package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
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

        final String ex1 = "Switch1";
        final String ex2 = "Switch2";
        final String ex3 = "Switch3";

        Switch PropSwitch = findViewById(R.id.switch1);
        SharedPreferences sharedPreferencesProp = getSharedPreferences(" ", MODE_PRIVATE);
        final SharedPreferences.Editor editorProp = sharedPreferencesProp.edit();
        PropSwitch.setChecked(sharedPreferencesProp.getBoolean(ex1, false));
        PropSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editorProp.putBoolean(getString(R.string.Probability),true);
                }else{
                    editorProp.putBoolean(getString(R.string.Probability),false);
                }
                editorProp.apply();
            }
        });

        Switch DDswitch = findViewById(R.id.switch2);
        SharedPreferences sharedPreferencesDD = getSharedPreferences(" ", MODE_PRIVATE);
        final SharedPreferences.Editor editorDD = sharedPreferencesDD.edit();
        DDswitch.setChecked(sharedPreferencesDD.getBoolean(ex2, false));
        DDswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editorDD.putBoolean(getString(R.string.double_down),true);
                }else{
                    editorDD.putBoolean(getString(R.string.double_down),false);
                }
                editorDD.apply();
            }
        });

        Switch SplitSwitch = findViewById(R.id.switch3);
        SharedPreferences sharedPreferencesSplit = getSharedPreferences(" ", MODE_PRIVATE);
        final SharedPreferences.Editor editorSplit = sharedPreferencesSplit.edit();
        SplitSwitch.setChecked(sharedPreferencesDD.getBoolean(ex3, false));
        SplitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editorSplit.putBoolean(getString(R.string.split),true);
                }else{
                    editorSplit.putBoolean(getString(R.string.split),false);
                }
                editorSplit.apply();
            }
        });
    }
}
