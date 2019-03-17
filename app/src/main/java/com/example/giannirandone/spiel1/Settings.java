package com.example.giannirandone.spiel1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    TextView settingsTextViewHighscoreAusgabe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsTextViewHighscoreAusgabe = (TextView) findViewById(R.id.settingsTextViewHighscoreAusgabe);

        SharedPreferences highscore = getSharedPreferences("score", 0);
        settingsTextViewHighscoreAusgabe.setText(Integer.toString(highscore.getInt("score", 0)));

        Log.i(Settings.class.getSimpleName(),"HIGHSCORE GESETZT");

    }


    public void onClick(View v)
    {


        Log.i(Settings.class.getSimpleName(), "onClick_SETTINGS");

    }

}

