package com.example.giannirandone.spiel1;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.Set;


public class Startseite extends AppCompatActivity implements View.OnClickListener
{


    Button btn_SpielStarten;
    Button btn_Einstellungen;

    Button button1;
    Button button2;
    Button button3;
    Button button4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_SpielStarten = (Button) findViewById(R.id.btn_SpielStarten);
        btn_SpielStarten.setOnClickListener(this);

        btn_Einstellungen = (Button) findViewById(R.id.btn_Einstellungen);
        btn_Einstellungen.setOnClickListener(this);

        SharedPreferences colorCheck = this.getSharedPreferences("number", 0);
        int colorCheckNumber = colorCheck.getInt("number", 0);
        loadBackgroundColor(colorCheckNumber);
    }

    protected void onStart()
    {
        super.onStart();
        SharedPreferences colorCheck = this.getSharedPreferences("number", 0);
        int colorCheckNumber = colorCheck.getInt("number", 0);
        loadBackgroundColor(colorCheckNumber);
    }


    public void loadBackgroundColor(int colorCheckNumber)
    {
        View viewStartseite = this.getWindow().getDecorView();

        if (colorCheckNumber == 0)
        {
            viewStartseite.setBackgroundColor(Color.WHITE);
        }else if (colorCheckNumber == 1)
        {
            viewStartseite.setBackgroundColor(Color.rgb(176,196,222));
        }
    }

    public void onClick (View e)
    {

        if (e.equals(btn_SpielStarten))
        {

            Intent i = new Intent(Startseite.this, Hauptklasse.class);
            //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            //finish();

        }

        if (e.equals(btn_Einstellungen))
        {

            startActivity(new Intent(Startseite.this, Settings.class));

        }

    }

}
