package com.example.giannirandone.spiel1;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class Startseite extends AppCompatActivity implements View.OnClickListener
{


    Button btn_SpielStarten;
    Button btn_Einstellungen;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    int zufallszahl;
    String zufallszahlString;
    int zufallszahl2;
    String zufallszahlString2;
    int zufallszahl3;
    String zufallszahlString3;
    int zufallszahl4;
    String zufallszahlString4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_SpielStarten = (Button) findViewById(R.id.btn_SpielStarten);
        btn_SpielStarten.setOnClickListener(this);

        btn_Einstellungen = (Button) findViewById(R.id.btn_Einstellungen);
        btn_Einstellungen.setOnClickListener(this);
    }


    public void onClick (View e)
    {



        if (e.equals(btn_SpielStarten))
        {

            Intent i = new Intent(Startseite.this, Hauptklasse.class);
            startActivity(i);

        }

        if (e.equals(btn_Einstellungen))
        {

            startActivity(new Intent(Startseite.this, Einstellungen.class));

        }






    }



}
