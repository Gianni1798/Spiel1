package com.example.giannirandone.spiel1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.w3c.dom.Text;

public class GameOverAusgabeseite extends AppCompatActivity
{

    TextView textView_scoreAusgabe;
    TextView textView_NewHighScore;
    TextView textView_actualHighscore;
    TextView textView_score;
    TextView textView_labelHighscore;
    TextView textView_missingPointsToNewHS;
    TextView textView_missingPointsToNewHSNumber;
    Button btn_restartGame;
    Button btn_backHome;
    Vibrator GameOverVibrator;
    //AdView adView_GOA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_ausgabeseite);


        textView_scoreAusgabe = (TextView) findViewById(R.id.textView_scoreAusgabe);
        textView_NewHighScore = (TextView) findViewById(R.id.textView_NewHighScore);
        textView_actualHighscore = (TextView) findViewById(R.id.textView_actualHighscore);
        textView_score = (TextView) findViewById(R.id.textView_score);
        textView_labelHighscore = (TextView) findViewById(R.id.textView_labelHighscore);
        textView_missingPointsToNewHS = (TextView) findViewById(R.id.textView_missingPointsToNewHS);
        textView_missingPointsToNewHSNumber = (TextView) findViewById(R.id.textView_missingPointsToNewHSNumber);
        btn_restartGame = (Button) findViewById(R.id.btn_restartGame);
        btn_backHome = (Button) findViewById(R.id.btn_backHome);
        //adView_GOA = (AdView) findViewById(R.id.adView_GOA);

        //MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111");
        //AdRequest adRequest2 = new AdRequest.Builder().build();
        //adView_GOA.loadAd(adRequest2);

        GameOverVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        btn_restartGame.setOnClickListener(v -> {
            Intent i = new Intent (GameOverAusgabeseite.this, Hauptklasse.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });

        btn_backHome.setOnClickListener(v -> {
            Intent i = new Intent (GameOverAusgabeseite.this, Startseite.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
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
        Ist der neue ein Highscore, bleibt er im Speicher liegen, wenn nicht,
        wird der vorherigst abgespeicherte Wert wieder im Speicher niedergelegt.
        */
        textView_actualHighscore.setTextColor(Color.BLACK);
        textView_scoreAusgabe.setTextColor(Color.BLACK);
        textView_score.setTextColor(Color.BLACK);
        textView_actualHighscore.setTextColor(Color.BLACK);
        textView_labelHighscore.setTextColor(Color.BLACK);

        if (scoreAusgabeNEW>scoreAusgabeOLD)
        {
            textView_score.setTextColor(Color.GRAY);
            textView_actualHighscore.setTextColor(Color.GRAY);
            textView_labelHighscore.setTextColor(Color.GRAY);
            textView_NewHighScore.setText("NEW HIGHSCORE!");
            textView_NewHighScore.setTextColor(Color.RED);
            textView_scoreAusgabe.setTextColor(Color.RED);
            GameOverVibrator.vibrate(1000);
        }
        else
        {
            editor.putInt("score", scoreAusgabeOLD);
            editor.commit();
            int HS = score.getInt("score", 0);

            textView_missingPointsToNewHS.setText("Missing Points to new Highscore:");
            textView_missingPointsToNewHSNumber.setText(Integer.toString(HS + 1 - Integer.valueOf(scoreUebergabe)));

            GameOverVibrator.vibrate(50);
        }

        textView_actualHighscore.setText(Integer.toString(score.getInt("score", 0)));

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
        View viewGOA = this.getWindow().getDecorView();

        if (colorCheckNumber == 0)
        {
            viewGOA.setBackgroundColor(Color.WHITE);
        }else if (colorCheckNumber == 1)
        {
            viewGOA.setBackgroundColor(Color.rgb(176,196,222));
        }
    }

    @Override
    public void onBackPressed()
    {
        //Deaktiviert den Backward-Navigation-Button
    }


}
