package com.example.giannirandone.spiel1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class Hauptklasse extends AppCompatActivity implements View.OnClickListener
{

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button btn_SpielStarten;
    int zufallszahl1;
    String zufallszahlString;
    int zufallszahl2;
    String zufallszahlString2;
    int zufallszahl3;
    String zufallszahlString3;
    int zufallszahl4;
    String zufallszahlString4;
    TextView textView_score;
    int score;
    String scoreString;
    TextView textView_time;
    int counter = 60;
    TextView textView1;
    TextView textView2;
    TextView countLifeAusgabe;
    Button pause_btn;
    boolean pause_check = false;
    private static long startTimeInMillis = 61000; //vorher: private static final long
    private long timeLeftInMillis = startTimeInMillis;
    boolean pause_onStart = true;
    int countLife = 3;
    int round = 1;
    Vibrator scoreRow;
    int scoreRowCount;
    MediaPlayer soundFeedbackScoreRow;
    private InterstitialAd interstitialAd_Hauptklasse;
    private AdView adView_Hauptklasse;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hauptklasse);

        button1  = (Button) findViewById(R.id.button1);
        button2  = (Button) findViewById(R.id.button2);
        button3  = (Button) findViewById(R.id.button3);
        button4  = (Button) findViewById(R.id.button4);
        textView_score = (TextView) findViewById(R.id.textView_score);
        textView_time = (TextView) findViewById(R.id.textView_time);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        countLifeAusgabe = (TextView) findViewById(R.id.countLifeAusgabe);
        pause_btn = (Button) findViewById(R.id.pause_btn);

        button1.setOnClickListener((View.OnClickListener)this);
        button2.setOnClickListener((View.OnClickListener)this);
        button3.setOnClickListener((View.OnClickListener)this);
        button4.setOnClickListener((View.OnClickListener)this);

        scoreRow = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        SharedPreferences colorCheck = this.getSharedPreferences("number", 0);
        int colorCheckNumber = colorCheck.getInt("number", 0);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713"); //HIER KOMMT DIE INTERSTITIAL-ID REIN
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713"); //HIER KOMMT DIE BANNER-ID REIN

        interstitialAd_Hauptklasse = new InterstitialAd(this);
        interstitialAd_Hauptklasse.setAdUnitId("ca-app-pub-3940256099942544/1033173712"); //HIER KOMMT DIE INTERSTITIAL-ID REIN
        interstitialAd_Hauptklasse.loadAd(new AdRequest.Builder().build());

        adView_Hauptklasse = (AdView) findViewById(R.id.adView_Hauptklasse);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        adView_Hauptklasse.loadAd(adRequest2);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        Log.i(Hauptklasse.class.getSimpleName(), "onStart##########################################################################");
        //Erzeuge Zufallszahlen und gleichzeitige Überprüfung auf Gleicheit der Zahlen:
        round = 1;
        generateNumbers(round);
        //Ende Zufallszahlen

        score = 0;
        scoreString = Integer.toString(score);
        textView_score.setText(scoreString);

        scoreRowCount = 0;

        //Ende Score

        countLife = 3;
        counter = 60;

        timer.cancel();
        resetTimer();
        textView_time.setText(" " + String.valueOf(counter) + " Sec.");

        button1.setBackgroundColor(Color.TRANSPARENT);
        button2.setBackgroundColor(Color.TRANSPARENT);
        button3.setBackgroundColor(Color.TRANSPARENT);
        button4.setBackgroundColor(Color.TRANSPARENT);

        /*
        Problembehebung:
        Wenn Activity im Pause-Modus gestartet wird,
        dass der Timer nun nicht mehr bereits im Hintergrund startet
        */

        if (pause_onStart==true)
            timer.start();

        startTimeInMillis = 61000;
        timeLeftInMillis = startTimeInMillis;

        showIfGameOverWithCountLife();

        SharedPreferences colorCheck = this.getSharedPreferences("number", 0);
        int colorCheckNumber = colorCheck.getInt("number", 0);
        loadBackgroundColor(colorCheckNumber);

    }

    public void loadBackgroundColor(int colorCheckNumber)
    {
        View viewSHauptklasse = this.getWindow().getDecorView();

        if (colorCheckNumber == 0)
        {
            viewSHauptklasse.setBackgroundColor(Color.WHITE);
        }else if (colorCheckNumber == 1)
        {
            viewSHauptklasse.setBackgroundColor(Color.rgb(176,196,222));
        }
    }

    @Override
    public void onBackPressed()
    {
        //Deaktiviert den Backward-Navigation-Button
    }


    @Override

    public void onClick (View v)
    {

        switch (v.getId()) {


            case R.id.button1:

                if (zufallszahl1 > zufallszahl2 && zufallszahl1 > zufallszahl3 && zufallszahl1 > zufallszahl4) {


                            //Score erhöhen:
                            score++;
                            scoreString = Integer.toString(score);
                            textView_score.setText(scoreString);
                            scoreRowCount++;
                            vibrateScoreRow();
                            //soundFeedbackScoreRow.start();

                }
                else
                {
                    score = score + 0;
                    scoreString = Integer.toString(score);
                    textView_score.setText(scoreString);
                    countLife--;
                    scoreRowCount = 0;
                    Log.i(Hauptklasse.class.getSimpleName(), "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX1");

                }


                zufallszahl1 = 0;
                zufallszahl2 = 0;
                zufallszahl3 = 0;
                zufallszahl4 = 0;

                generateNumbers(round);
                showIfGameOverWithCountLife();


                break;

            case R.id.button2:

                if (zufallszahl2 > zufallszahl1 && zufallszahl2 > zufallszahl3 && zufallszahl2 > zufallszahl4) {

                            //Score erhöhen:
                            score++;
                            scoreString = Integer.toString(score);
                            textView_score.setText(scoreString);
                            scoreRowCount++;
                            vibrateScoreRow();
                            //soundFeedbackScoreRow.start();

                }
                else
                {
                    score = score + 0;
                    scoreString = Integer.toString(score);
                    textView_score.setText(scoreString);
                    countLife--;
                    scoreRowCount = 0;
                    Log.i(Hauptklasse.class.getSimpleName(), "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX2");

                }

                zufallszahl1 = 0;
                zufallszahl2 = 0;
                zufallszahl3 = 0;
                zufallszahl4 = 0;

                generateNumbers(round);
                showIfGameOverWithCountLife();


                break;

            case R.id.button3:

                if (zufallszahl3 > zufallszahl1 && zufallszahl3 > zufallszahl2 && zufallszahl3 > zufallszahl4) {

                            //Score erhöhen:
                            score++;
                            scoreString = Integer.toString(score);
                            textView_score.setText(scoreString);
                            scoreRowCount++;
                            vibrateScoreRow();
                            //soundFeedbackScoreRow.start();

                }
                else
                {
                    score = score + 0;
                    scoreString = Integer.toString(score);
                    textView_score.setText(scoreString);
                    countLife--;
                    scoreRowCount = 0;
                    Log.i(Hauptklasse.class.getSimpleName(), "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX3");
                }

                zufallszahl1 = 0;
                zufallszahl2 = 0;
                zufallszahl3 = 0;
                zufallszahl4 = 0;

                generateNumbers(round);
                showIfGameOverWithCountLife();


                break;

            case R.id.button4:

                if (zufallszahl4 > zufallszahl1 && zufallszahl4 > zufallszahl2 && zufallszahl4 > zufallszahl3) {

                            //Score erhöhen:
                            score++;
                            scoreString = Integer.toString(score);
                            textView_score.setText(scoreString);
                            scoreRowCount++;
                            vibrateScoreRow();
                            //soundFeedbackScoreRow.start();
                }
                else
                {
                    score = score + 0;
                    scoreString = Integer.toString(score);
                    textView_score.setText(scoreString);
                    countLife--;
                    scoreRowCount = 0;
                    Log.i(Hauptklasse.class.getSimpleName(), "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX4");
                }

                zufallszahl1 = 0;
                zufallszahl2 = 0;
                zufallszahl3 = 0;
                zufallszahl4 = 0;

                generateNumbers(round);
                showIfGameOverWithCountLife();

                break;


        }
    }

    public void vibrateScoreRow()
    {
        if (scoreRowCount == 2)
            scoreRow.vibrate(150);
        if (scoreRowCount == 3)
            scoreRow.vibrate(250);
        if (scoreRowCount == 4)
            scoreRow.vibrate(500);
        if (scoreRowCount > 4)
            scoreRow.vibrate(850);
    }

    public void generateNumbers(int roundNumber)
    {
        round++;
        if(roundNumber<5)
        {
           changeLimit(9);
           SameNumbers(9);
        }
        if (roundNumber>=5)
        {
            changeLimit(30);
            SameNumbers(30);
        }
        if (roundNumber>=8)
        {
            changeLimit(99);
            SameNumbers(99);
        }
        if (roundNumber>=15)
        {
            changeLimit(150);
            SameNumbers(150);
        }
        if (roundNumber>=20)
        {
            changeLimit(400);
            SameNumbers(400);
        }
        if (roundNumber>=25)
        {
            changeLimit(999);
            SameNumbers(999);
        }
        if (roundNumber>=30)
        {
            changeLimit(1999);
            SameNumbers(1999);
        }
        if (roundNumber>=40)
        {
            changeLimit(4999);
            SameNumbers(4999);
        }
        if (roundNumber>=50)
        {
            changeLimit(19999);
            SameNumbers(19999);
        }
        if (roundNumber>=55)
        {
            changeLimit(49999);
            SameNumbers(49999);
        }
        if (roundNumber>=50)
        {
            changeLimit(100000);
            SameNumbers(100000);
        }
    }


    public void changeLimit(int limit)
    {
        zufallszahl1 = (int) (Math.random() * limit) + 1;
        zufallszahlString = Integer.toString(zufallszahl1);
        button1.setText(zufallszahlString);

        zufallszahl2 = (int) (Math.random() * limit) + 1;
        zufallszahlString2 = Integer.toString(zufallszahl2);
        button2.setText(zufallszahlString2);

        zufallszahl3 = (int) (Math.random() * limit) + 1;
        zufallszahlString3 = Integer.toString(zufallszahl3);
        button3.setText(zufallszahlString3);

        zufallszahl4 = (int) (Math.random() * limit) + 1;
        zufallszahlString4 = Integer.toString(zufallszahl4);
        button4.setText(zufallszahlString4);
    }

    public void showIfGameOverWithCountLife()
    {
        if (countLife == 0)
            counter = 0;

        countLifeAusgabe.setText("Your lives: " + Integer.toString(countLife));
    }

    public void SameNumbers(int limitOfRound)
    {

        //Zufallszahl 1:

        while (zufallszahl1 == zufallszahl2)
        {
            //generiere Zufallszahl neu
            zufallszahl2 = (int) (Math.random() * limitOfRound) + 1;

            if(!(zufallszahl1 == zufallszahl2))
            {
                zufallszahlString2 = Integer.toString(zufallszahl2);
                button2.setText(zufallszahlString2);
            }
        }

        while (zufallszahl1 == zufallszahl3)
        {
            //generiere Zufallszahl neu
            zufallszahl3 = (int) (Math.random() * limitOfRound) + 1;

            if (!(zufallszahl1 == zufallszahl3))
            {
                zufallszahlString3 = Integer.toString(zufallszahl3);
                button3.setText(zufallszahlString3);
            }
        }

        while (zufallszahl1 == zufallszahl4)
        {
            //generiere Zufallszahl neu
            zufallszahl4 = (int) (Math.random() * limitOfRound) + 1;

            if(!(zufallszahl1 == zufallszahl4))
            {
                zufallszahlString4 = Integer.toString(zufallszahl4);
                button4.setText(zufallszahlString4);
            }
        }


        //Zufallszahl 2:


        while (zufallszahl2 == zufallszahl1)
        {
            //generiere Zufallszahl neu
            zufallszahl1 = (int) (Math.random() * limitOfRound) + 1;

            if (!(zufallszahl2 == zufallszahl1))
            {
                zufallszahlString = Integer.toString(zufallszahl1);
                button1.setText(zufallszahlString);
            }
        }

        while (zufallszahl2 == zufallszahl3)
        {
            //generiere Zufallszahl neu
            zufallszahl3 = (int) (Math.random() * limitOfRound) + 1;

            if(!(zufallszahl2 == zufallszahl3))
            {
                zufallszahlString3 = Integer.toString(zufallszahl3);
                button3.setText(zufallszahlString3);
            }
        }

        while (zufallszahl2 == zufallszahl4)
        {
            //generiere Zufallszahl neu
            zufallszahl4 = (int) (Math.random() * limitOfRound) + 1;

            if(!(zufallszahl2 == zufallszahl4))
            {
                zufallszahlString4 = Integer.toString(zufallszahl4);
                button4.setText(zufallszahlString4);
            }
        }


        //Zufallszahl 3:

        while (zufallszahl3 == zufallszahl1)
        {
            //generiere Zufallszahl neu
            zufallszahl1 = (int) (Math.random() * limitOfRound) + 1;

            if (!(zufallszahl3 == zufallszahl1))
            {
                zufallszahlString = Integer.toString(zufallszahl1);
                button1.setText(zufallszahlString);
            }
        }

        while (zufallszahl3 == zufallszahl2)
        {
            //generiere Zufallszahl neu
            zufallszahl2 = (int) (Math.random() * limitOfRound) + 1;

            if(!(zufallszahl3 == zufallszahl2))
            {
                zufallszahlString2 = Integer.toString(zufallszahl2);
                button2.setText(zufallszahlString2);
            }
        }

        while (zufallszahl3 == zufallszahl4)
        {
            //generiere Zufallszahl neu
            zufallszahl4 = (int) (Math.random() * limitOfRound) + 1;

            if(!(zufallszahl3 == zufallszahl4))
            {
                zufallszahlString4 = Integer.toString(zufallszahl4);
                button4.setText(zufallszahlString4);
            }
        }

        //Zufallszahl 4:

        while (zufallszahl4 == zufallszahl1)
        {
            //generiere Zufallszahl neu
            zufallszahl1 = (int) (Math.random() * limitOfRound) + 1;

            if (!(zufallszahl4 == zufallszahl1))
            {
                zufallszahlString = Integer.toString(zufallszahl1);
                button1.setText(zufallszahlString);
            }
        }

        while (zufallszahl4 == zufallszahl2)
        {
            //generiere Zufallszahl neu
            zufallszahl2 = (int) (Math.random() * limitOfRound) + 1;

            if(!(zufallszahl4 == zufallszahl2))
            {
                zufallszahlString2 = Integer.toString(zufallszahl2);
                button2.setText(zufallszahlString2);
            }
        }

        while (zufallszahl4 == zufallszahl3)
        {
            //generiere Zufallszahl neu
            zufallszahl3 = (int) (Math.random() * limitOfRound) + 1;

            if(!(zufallszahl4 == zufallszahl3))
            {
                zufallszahlString3 = Integer.toString(zufallszahl3);
                button3.setText(zufallszahlString3);
            }
        }
    }

    public void counterCheck()
    {
        if (pause_check == false)
        {
            if(!(counter==0))
            {
                textView_time.setText(" " + (String.valueOf(counter)) + " Sec.");
            }else
            {
                textView_time.setText("");
            }
            counter--;
        }
        else
        {
            textView_time.setText(" " + (String.valueOf(counter)) + " Sec.");
            timeFinished();
        }
    }


    //Counter:

    CountDownTimer timer = new CountDownTimer(timeLeftInMillis, 1000)
    {

        public void onTick(long millisUntilFinished)
        {
            timeLeftInMillis = millisUntilFinished;
            pause_onStart = true;

            timeFinished();
            counterCheck();
            blinken();
        }

        public void onFinish()
        {

            resetTimer();

            String scoreUebergabe = Integer.toString(score);

            Intent ScoreUebergabeIntent = new Intent(Hauptklasse.this, GameOverAusgabeseite.class);
            ScoreUebergabeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            ScoreUebergabeIntent.putExtra("score", scoreUebergabe);
            startActivity(ScoreUebergabeIntent);
            finish();

        }


    }.start();    //Ende Counter

    private void resetTimer()
    {
        timeLeftInMillis = startTimeInMillis;
    }

    public void blinken()
    {

        SharedPreferences colorCheck = this.getSharedPreferences("number", 0);
        int colorCheckNumber = colorCheck.getInt("number", 0);
        View viewHauptklasse = this.getWindow().getDecorView();
        //Gelbes Blinken in den letzten 10 Sekunden


        if (counter+1 == 15 || counter+1 == 13 || counter+1 == 11)
        {
            viewHauptklasse.setBackgroundColor(Color.YELLOW);
        }

        if (counter+1 == 14 || counter+1 == 12)
        {
            loadBackgroundColor(colorCheckNumber);
        }

        //Rotes Blinken in den letzten 10 Sekunden

        if (counter+1==10 || counter+1 == 8 || counter+1==6 || counter+1==4 || counter+1==2)
        {
            viewHauptklasse.setBackgroundColor(Color.RED);
        }

        if (counter+1==9 || counter+1==7 || counter+1==5 || counter+1==3 || counter+1==1)
        {
            //loadBackgroundColor(colorCheckNumber);
            viewHauptklasse.setBackgroundColor(Color.YELLOW);
        }

    }

    public void timeFinished()
    {
        if (counter == 0)
        {
            counter = counter-0;
            timer.cancel();
            timer.onFinish();
        }
    }
    //Pause-Button


    public void PausePushed (View p)
    {

        if (pause_check == false)
        {

            pause_check = true;
            pause_btn.setText(">>");
            timer.cancel();
            counter = counter-0;

            button1.setCursorVisible(false);
            button1.setEnabled(false);

            button2.setCursorVisible(false);
            button2.setEnabled(false);

            button3.setCursorVisible(false);
            button3.setEnabled(false);

            button4.setCursorVisible(false);
            button4.setEnabled(false);

            pause_onStart = false;

            if(interstitialAd_Hauptklasse.isLoaded())
                interstitialAd_Hauptklasse.show();
        }

        else
        {

            pause_check = false;
            pause_btn.setText("II");

            if(!(pause_onStart==true))
                timer.start();

            button1.setCursorVisible(true);
            button1.setEnabled(true);

            button2.setCursorVisible(true);
            button2.setEnabled(true);

            button3.setCursorVisible(true);
            button3.setEnabled(true);

            button4.setCursorVisible(true);
            button4.setEnabled(true);

        }

    }
    //Ende Pause-Button
}