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

public class GameOverAusgabeseite extends AppCompatActivity
{

    TextView textView_scoreAusgabe;
    TextView textView_NewHighScore;
    Button btn_restartGame;
    Button btn_backHome;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_ausgabeseite);


        textView_scoreAusgabe = (TextView) findViewById(R.id.textView_scoreAusgabe);
        textView_NewHighScore = (TextView) findViewById(R.id.textView_NewHighScore);
        btn_restartGame = (Button) findViewById(R.id.btn_restartGame);
        btn_backHome = (Button) findViewById(R.id.btn_backHome);

        btn_restartGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent (GameOverAusgabeseite.this, Hauptklasse.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });

        btn_backHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent (GameOverAusgabeseite.this, Startseite.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });

        //Übergebener, erzielter Score wird angenommen und ausgegeben
        Intent scoreAnnahme = getIntent();
        String scoreUebergabe = scoreAnnahme.getExtras().getString("score");
        textView_scoreAusgabe.setText(scoreUebergabe);

        int scoreUebergabeInt = Integer.valueOf(scoreUebergabe);

        //Ruft im Speicher abgelegten Highscore ab
        SharedPreferences loadScoreOLD = this.getSharedPreferences("score", 0);
        int scoreAusgabeOLD = loadScoreOLD.getInt("score", 0);

        //Speichert gerade erzielten Score im Speicher ab
        SharedPreferences score = getSharedPreferences("score", 0);
        SharedPreferences.Editor editor = score.edit();
        editor.putInt("score", scoreUebergabeInt);
        editor.commit();

        //Abgespeicherter Score wird abgerufen
        SharedPreferences loadScoreNEW = this.getSharedPreferences("score", 0);
        int scoreAusgabeNEW = loadScoreNEW.getInt("score", 0);

        /*
        Im Folgenden:
        Prüft auf Highscore. Alter Wert wird mit neuem niederlegten Wert verglichen.
        Ist der neue ein Highscore, bleibt er im Speicher gespeichert, wenn nicht,
        wird der vorherigst abgespeicherte Wert wieder im Speicher abgespeichert
        */
        if (scoreAusgabeNEW>scoreAusgabeOLD)
        {
            textView_NewHighScore.setText("NEW HIGHSCORE!");
        }
        else
        {
            editor.putInt("score", scoreAusgabeOLD);
            editor.commit();
        }
    }


    @Override
    public void onBackPressed()
    {
        //Deaktiviert den Backward-Navigation-Button
    }


}
