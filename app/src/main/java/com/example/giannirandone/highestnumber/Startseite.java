package com.example.giannirandone.highestnumber;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class Startseite extends AppCompatActivity implements View.OnClickListener
{

    Button btn_SpielStarten;
    Button btn_Einstellungen;
    ImageView imageView;

    //private AdView adView_Startseite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713"); //HIER KOMMT DIE BANNER-ID REIN

        btn_SpielStarten = (Button) findViewById(R.id.btn_SpielStarten);
        btn_SpielStarten.setOnClickListener(this);

        btn_Einstellungen = (Button) findViewById(R.id.btn_Einstellungen);
        btn_Einstellungen.setOnClickListener(this);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(this);

        //adView_Startseite = (AdView) findViewById(R.id.adView_Startseite);
        //AdRequest adRequest1 = new AdRequest.Builder().build();
        //adView_Startseite.loadAd(adRequest1);

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
            viewStartseite.setBackgroundColor(Color.WHITE);
        else if (colorCheckNumber == 1)
            viewStartseite.setBackgroundColor(Color.rgb(176,196,222));
    }

    public void onClick (View e)
    {
        if (e.equals(btn_SpielStarten))
            startActivity(new Intent(Startseite.this, Hauptklasse.class));

        if (e.equals(btn_Einstellungen))
            startActivity(new Intent(Startseite.this, Settings.class));

        if (e.equals(imageView))
            startActivity(new Intent(Startseite.this, Hauptklasse.class));
    }

}
