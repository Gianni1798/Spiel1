package com.example.giannirandone.spiel1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GameOverAusgabeseite extends AppCompatActivity implements View.OnClickListener
{

    TextView textView_scoreAusgabe;
    TextView textView_NewHighScore;
    Button btn_restartGame;
    Button btn_backHome;
    int erreichtePunkte = 0;
    SharedPreferences preferences;
    SharedPreferences.Editor preferencesEditor;
    String locationHighScore = "SpeicherortHighscore";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_ausgabeseite);


        textView_scoreAusgabe = (TextView) findViewById(R.id.textView_scoreAusgabe);
        btn_restartGame = (Button) findViewById(R.id.btn_restartGame);
        btn_restartGame.setOnClickListener((View.OnClickListener)this);
        btn_backHome = (Button) findViewById(R.id.btn_backHome);
        btn_backHome.setOnClickListener((View.OnClickListener)this);

        Intent scoreAnnahme = getIntent();
        String scoreUebergabe = scoreAnnahme.getExtras().getString("score");
        textView_scoreAusgabe.setText(scoreUebergabe);

        //TODO


        preferences = getSharedPreferences("score",0);
        locationHighScore = preferences.getString("score","Du hast noch keinen Score erzielt!");

        preferencesEditor = preferences.edit();
        CharSequence textData = textView_scoreAusgabe.getText();

        if (textData!=null)
        {
            preferencesEditor.putString("score",textData.toString());
            preferencesEditor.commit();
        }

        Bundle extras = getIntent().getExtras();

        if (extras == null)
            erreichtePunkte = 0;
        else
            erreichtePunkte = Integer.valueOf(scoreAnnahme.getExtras().getString("score"));


        //showIfHighscore();

    }

    private void showIfHighscore()
    {
        if(erreichtePunkte > preferences.getInt(locationHighScore, 0));
        {
            textView_NewHighScore.setText("+++ NEW HIGHSCORE +++");
            preferencesEditor.putInt(locationHighScore, erreichtePunkte);
        }
    }


    @Override
    public void onBackPressed()
    {
        //Deaktiviert den Backward-Navigation-Button

    }

    @Override
    public void onClick(View v)
    {

        if ((v.equals(btn_restartGame)))
        {
            Intent i = new Intent (GameOverAusgabeseite.this, Hauptklasse.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }

        if (v.equals(btn_backHome))
        {
            Intent i = new Intent (GameOverAusgabeseite.this, Startseite.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }

    }



}
