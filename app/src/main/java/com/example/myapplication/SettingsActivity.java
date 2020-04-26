package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import org.parceler.Parcel;
import org.parceler.Parcels;

public class SettingsActivity extends AppCompatActivity {

    Bundle saveState;

    Switch probabilitySwitch;
    Switch doubleDownSwitch;
    Switch splitSwitch;

    final String ex1 = "Switch1";
    final String ex2 = "Switch2";
    final String ex3 = "Switch3";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toast.makeText(this, "setting on create", Toast.LENGTH_SHORT).show();

        probabilitySwitch = findViewById(R.id.switch1);
        doubleDownSwitch = findViewById(R.id.switch2);
        splitSwitch = findViewById(R.id.switch3);


        final ImageButton mainActivity = findViewById(R.id.imageHomeButton);
        mainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent nextPage = new Intent(SettingsActivity.this, MainActivity.class);
//                startActivity(nextPage);
                finish();
                return;
            }
        });

        SharedPreferences sharedPreferencesProp = getSharedPreferences(" ", MODE_PRIVATE);
        final SharedPreferences.Editor editorProp = sharedPreferencesProp.edit();
        probabilitySwitch.setChecked(sharedPreferencesProp.getBoolean(ex1, false));
        probabilitySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editorProp.putBoolean(getString(R.string.Probability),true);
                    saveState.putBoolean("probability", true);
                }else{
                    editorProp.putBoolean(getString(R.string.Probability),false);
                    saveState.putBoolean("probability", true);
                }
                editorProp.apply();
            }
        });

        SharedPreferences sharedPreferencesDD = getSharedPreferences(" ", MODE_PRIVATE);
        final SharedPreferences.Editor editorDD = sharedPreferencesDD.edit();
        doubleDownSwitch.setChecked(sharedPreferencesDD.getBoolean(ex2, false));
        doubleDownSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

        SharedPreferences sharedPreferencesSplit = getSharedPreferences(" ", MODE_PRIVATE);
        final SharedPreferences.Editor editorSplit = sharedPreferencesSplit.edit();
        splitSwitch.setChecked(sharedPreferencesDD.getBoolean(ex3, false));
        splitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(this, "setting restore", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(this, "setting save state", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "setting resume", Toast.LENGTH_SHORT).show();
        if (saveState != null) {
            probabilitySwitch.setChecked(saveState.getBoolean("probability"));
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "setting pause", Toast.LENGTH_SHORT).show();

    }
}
