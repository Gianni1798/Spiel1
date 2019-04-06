package com.example.giannirandone.spiel1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Settings extends AppCompatActivity {

    TextView settingsTextViewHighscoreAusgabe;
    Switch switch_Color;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsTextViewHighscoreAusgabe = (TextView) findViewById(R.id.settingsTextViewHighscoreAusgabe);
        switch_Color = (Switch) findViewById(R.id.switch_Color);

        SharedPreferences highscore = getSharedPreferences("score", 0);
        settingsTextViewHighscoreAusgabe.setText(Integer.toString(highscore.getInt("score", 0)));

        Log.i(Settings.class.getSimpleName(),"HIGHSCORE GESETZT");

        View viewSettings = this.getWindow().getDecorView();


        switch_Color.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                if (b == true)
                {
                    viewSettings.setBackgroundColor(Color.GRAY);
                    Toast.makeText(Settings.this, "Color changed to gray", Toast.LENGTH_SHORT).show();

                }else
                {
                    viewSettings.setBackgroundColor(Color.WHITE);
                    Toast.makeText(Settings.this, "Color changed to white", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }



    public void onClick(View v)
    {

        Log.i(Settings.class.getSimpleName(), "onClick_SETTINGS");


    }

}

