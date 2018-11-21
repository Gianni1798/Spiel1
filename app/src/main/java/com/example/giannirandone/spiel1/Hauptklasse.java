package com.example.giannirandone.spiel1;

import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import java.lang.Object;


import java.time.chrono.Chronology;
import java.util.Random;

import static android.graphics.Color.*;

public class Hauptklasse extends AppCompatActivity implements View.OnClickListener
{



    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button btn_SpielStarten;
    int zufallszahl;
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
    TextView textView_Feedback;
    Button pause_btn;
    boolean pause_check = false;
    private static final long startTimeInMillis = 60000;
    private long timeLeftInMillis = startTimeInMillis;




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
        textView_Feedback = (TextView) findViewById(R.id.textView_Feedback);
        pause_btn = (Button) findViewById(R.id.pause_btn);


        button1.setOnClickListener((View.OnClickListener)this);
        button2.setOnClickListener((View.OnClickListener)this);
        button3.setOnClickListener((View.OnClickListener)this);
        button4.setOnClickListener((View.OnClickListener)this);

    }

    @Override
    public void onStart()
    {
        super.onStart();

        //Erzeuge Zufallszahlen:

        zufallszahl = (int)(Math.random() * 500) +1;
        zufallszahlString = Integer.toString(zufallszahl);
        button1.setText(zufallszahlString);

        zufallszahl2 = (int)(Math.random() * 500) + 1;
        zufallszahlString2 = Integer.toString(zufallszahl2);
        button2.setText(zufallszahlString2);

        zufallszahl3 = (int)(Math.random() * 500) + 1;
        zufallszahlString3 = Integer.toString(zufallszahl3);
        button3.setText(zufallszahlString3);

        zufallszahl4 = (int)(Math.random() * 500) + 1;
        zufallszahlString4 = Integer.toString(zufallszahl4);
        button4.setText(zufallszahlString4);

        //Ende Zufallszahlen

        //Überprüfe ob Zufallszahlen evtl. gleich sind

        SameNumbers();

        //Ende der Überprüfung


        score = 0;
        scoreString = null;

        if (scoreString == null)
        {
            scoreString = Integer.toString(score);
        }

        textView_score.setText(scoreString);
        //Ende Score

        //counter = 60;
        //timer.purge();
        timer.cancel();
        resetTimer();
        timer.start();
        textView_time.setText(" " + String.valueOf(counter) + " Sec.");


    }





    @Override


    public void onClick (View v)
        {



            switch (v.getId()) {



                case R.id.button1:


                    if (zufallszahl > zufallszahl2) {
                        if (zufallszahl > zufallszahl3) {
                            if (zufallszahl > zufallszahl4) {

                                //Score erhöhen:
                                //textView_Feedback.setText("+");
                                //textView_Feedback.setTextColor(Color.GREEN);
                                score++;
                                scoreString = Integer.toString(score);
                                textView_score.setText(scoreString);



                            }
                        }
                    }
                    else
                    {
                        //textView_Feedback.setText("-");
                        //textView_Feedback.setTextColor(Color.RED);
                        score = score + 0;
                        scoreString = Integer.toString(score);
                        textView_score.setText(scoreString);
                    }


                    zufallszahl = 0;
                    zufallszahl2 = 0;
                    zufallszahl3 = 0;
                    zufallszahl4 = 0;


                    zufallszahl = (int) (Math.random() * 500) + 1;
                    zufallszahlString = Integer.toString(zufallszahl);
                    button1.setText(zufallszahlString);

                    zufallszahl2 = (int) (Math.random() * 500) + 1;
                    zufallszahlString2 = Integer.toString(zufallszahl2);
                    button2.setText(zufallszahlString2);

                    zufallszahl3 = (int) (Math.random() * 500) + 1;
                    zufallszahlString3 = Integer.toString(zufallszahl3);
                    button3.setText(zufallszahlString3);

                    zufallszahl4 = (int) (Math.random() * 500) + 1;
                    zufallszahlString4 = Integer.toString(zufallszahl4);
                    button4.setText(zufallszahlString4);

                    SameNumbers();


                    break;

                case R.id.button2:


                    if (zufallszahl2 > zufallszahl) {
                        if (zufallszahl2 > zufallszahl3) {
                            if (zufallszahl2 > zufallszahl4) {

                                //Score erhöhen:
                                //textView_Feedback.setText("+");
                                //textView_Feedback.setTextColor(Color.GREEN);
                                score++;
                                scoreString = Integer.toString(score);
                                textView_score.setText(scoreString);


                            }
                        }
                    }
                    else
                    {
                        //textView_Feedback.setText("-");
                        //textView_Feedback.setTextColor(Color.RED);
                        score = score + 0;
                        scoreString = Integer.toString(score);
                        textView_score.setText(scoreString);
                    }

                    zufallszahl = 0;
                    zufallszahl2 = 0;
                    zufallszahl3 = 0;
                    zufallszahl4 = 0;


                    zufallszahl = (int) (Math.random() * 500) + 1;
                    zufallszahlString = Integer.toString(zufallszahl);
                    button1.setText(zufallszahlString);

                    zufallszahl2 = (int) (Math.random() * 500) + 1;
                    zufallszahlString2 = Integer.toString(zufallszahl2);
                    button2.setText(zufallszahlString2);

                    zufallszahl3 = (int) (Math.random() * 500) + 1;
                    zufallszahlString3 = Integer.toString(zufallszahl3);
                    button3.setText(zufallszahlString3);

                    zufallszahl4 = (int) (Math.random() * 500) + 1;
                    zufallszahlString4 = Integer.toString(zufallszahl4);
                    button4.setText(zufallszahlString4);

                    SameNumbers();


                    break;

                case R.id.button3:

                    if (zufallszahl3 > zufallszahl) {
                        if (zufallszahl3 > zufallszahl2) {
                            if (zufallszahl3 > zufallszahl4) {

                                //Score erhöhen:
                                //textView_Feedback.setText("+");
                                //textView_Feedback.setTextColor(Color.GREEN);
                                score++;
                                scoreString = Integer.toString(score);
                                textView_score.setText(scoreString);


                            }
                        }
                    }
                    else
                    {
                        //textView_Feedback.setText("-");
                        //textView_Feedback.setTextColor(Color.RED);
                        score = score + 0;
                        scoreString = Integer.toString(score);
                        textView_score.setText(scoreString);
                    }

                    zufallszahl = 0;
                    zufallszahl2 = 0;
                    zufallszahl3 = 0;
                    zufallszahl4 = 0;


                    zufallszahl = (int) (Math.random() * 500) + 1;
                    zufallszahlString = Integer.toString(zufallszahl);
                    button1.setText(zufallszahlString);

                    zufallszahl2 = (int) (Math.random() * 500) + 1;
                    zufallszahlString2 = Integer.toString(zufallszahl2);
                    button2.setText(zufallszahlString2);

                    zufallszahl3 = (int) (Math.random() * 500) + 1;
                    zufallszahlString3 = Integer.toString(zufallszahl3);
                    button3.setText(zufallszahlString3);

                    zufallszahl4 = (int) (Math.random() * 500) + 1;
                    zufallszahlString4 = Integer.toString(zufallszahl4);
                    button4.setText(zufallszahlString4);

                    SameNumbers();


                    break;

                case R.id.button4:

                    if (zufallszahl4 > zufallszahl) {
                        if (zufallszahl4 > zufallszahl2) {
                            if (zufallszahl4 > zufallszahl3) {

                                //Score erhöhen:
                                //textView_Feedback.setText("+");
                                //textView_Feedback.setTextColor(Color.GREEN);
                                score++;
                                scoreString = Integer.toString(score);
                                textView_score.setText(scoreString);


                            }
                        }
                    }
                    else
                    {
                        //textView_Feedback.setText("-");
                        //textView_Feedback.setTextColor(Color.RED);
                        score = score + 0;
                        scoreString = Integer.toString(score);
                        textView_score.setText(scoreString);
                    }

                    zufallszahl = 0;
                    zufallszahl2 = 0;
                    zufallszahl3 = 0;
                    zufallszahl4 = 0;


                    zufallszahl = (int) (Math.random() * 500) + 1;
                    zufallszahlString = Integer.toString(zufallszahl);
                    button1.setText(zufallszahlString);

                    zufallszahl2 = (int) (Math.random() * 500) + 1;
                    zufallszahlString2 = Integer.toString(zufallszahl2);
                    button2.setText(zufallszahlString2);

                    zufallszahl3 = (int) (Math.random() * 500) + 1;
                    zufallszahlString3 = Integer.toString(zufallszahl3);
                    button3.setText(zufallszahlString3);

                    zufallszahl4 = (int) (Math.random() * 500) + 1;
                    zufallszahlString4 = Integer.toString(zufallszahl4);
                    button4.setText(zufallszahlString4);


                    SameNumbers();

                    break;


            }


        }


    public void SameNumbers()
    {

        //Zufallszahl 1:

      while (zufallszahl == zufallszahl2)
      {
          //generiere Zufallszahl neu
          zufallszahl2 = (int) (Math.random() * 500) + 1;

          if(!(zufallszahl == zufallszahl2))
          {
              zufallszahlString2 = Integer.toString(zufallszahl2);
              button2.setText(zufallszahlString2);
          }

      }

      while (zufallszahl == zufallszahl3)
      {
          //generiere Zufallszahl neu
          zufallszahl3 = (int) (Math.random() * 500) + 1;

          if (!(zufallszahl == zufallszahl3))
          {
              zufallszahlString3 = Integer.toString(zufallszahl3);
              button3.setText(zufallszahlString3);
          }

      }

      while (zufallszahl == zufallszahl4)
      {
          //generiere Zufallszahl neu
          zufallszahl4 = (int) (Math.random() * 500) + 1;

          if(!(zufallszahl == zufallszahl4))
          {
              zufallszahlString4 = Integer.toString(zufallszahl4);
              button4.setText(zufallszahlString4);
          }

      }


      //Zufallszahl 2:


      while (zufallszahl2 == zufallszahl)
      {
          //generiere Zufallszahl neu
          zufallszahl = (int) (Math.random() * 500) + 1;

          if (!(zufallszahl2 == zufallszahl))
          {
              zufallszahlString = Integer.toString(zufallszahl);
              button1.setText(zufallszahlString);
          }

      }

      while (zufallszahl2 == zufallszahl3)
      {
          //generiere Zufallszahl neu
          zufallszahl3 = (int) (Math.random() * 500) + 1;

          if(!(zufallszahl2 == zufallszahl3))
          {
              zufallszahlString3 = Integer.toString(zufallszahl3);
              button3.setText(zufallszahlString3);
          }
      }

      while (zufallszahl2 == zufallszahl4)
      {
          //generiere Zufallszahl neu
          zufallszahl4 = (int) (Math.random() * 500) + 1;

          if(!(zufallszahl2 == zufallszahl4))
          {
              zufallszahlString4 = Integer.toString(zufallszahl4);
              button4.setText(zufallszahlString4);
          }
      }


      //Zufallszahl 3:

        while (zufallszahl3 == zufallszahl)
        {
            //generiere Zufallszahl neu
            zufallszahl = (int) (Math.random() * 500) + 1;

            if (!(zufallszahl3 == zufallszahl))
            {
                zufallszahlString = Integer.toString(zufallszahl);
                button1.setText(zufallszahlString);
            }

        }

        while (zufallszahl3 == zufallszahl2)
        {
            //generiere Zufallszahl neu
            zufallszahl2 = (int) (Math.random() * 500) + 1;

            if(!(zufallszahl3 == zufallszahl2))
            {
                zufallszahlString2 = Integer.toString(zufallszahl2);
                button2.setText(zufallszahlString2);
            }
        }

        while (zufallszahl3 == zufallszahl4)
        {
            //generiere Zufallszahl neu
            zufallszahl4 = (int) (Math.random() * 500) + 1;

            if(!(zufallszahl3 == zufallszahl4))
            {
                zufallszahlString4 = Integer.toString(zufallszahl4);
                button4.setText(zufallszahlString4);
            }
        }

        //Zufallszahl 4:

        while (zufallszahl4 == zufallszahl)
        {
            //generiere Zufallszahl neu
            zufallszahl = (int) (Math.random() * 500) + 1;

            if (!(zufallszahl4 == zufallszahl))
            {
                zufallszahlString = Integer.toString(zufallszahl);
                button1.setText(zufallszahlString);
            }

        }

        while (zufallszahl4 == zufallszahl2)
        {
            //generiere Zufallszahl neu
            zufallszahl2 = (int) (Math.random() * 500) + 1;

            if(!(zufallszahl4 == zufallszahl2))
            {
                zufallszahlString2 = Integer.toString(zufallszahl2);
                button2.setText(zufallszahlString2);
            }
        }

        while (zufallszahl4 == zufallszahl3)
        {
            //generiere Zufallszahl neu
            zufallszahl3 = (int) (Math.random() * 500) + 1;

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
    //mm von 61k auf 60k setzen!
    CountDownTimer timer = new CountDownTimer(timeLeftInMillis, 1000)
    {

        public void onTick(long millisUntilFinished)
        {
            timeLeftInMillis = millisUntilFinished;

            timeFinished();
            counterCheck();
            blinken();

            Log.e(Hauptklasse.class.getSimpleName(), "bla");
        }



        public void onFinish()
        {


            resetTimer();

            String scoreUebergabe = Integer.toString(score);

            Intent ScoreUebergabeIntent = new Intent(Hauptklasse.this, GameOverAusgabeseite.class);
            ScoreUebergabeIntent.putExtra("score", scoreUebergabe);
            startActivity(ScoreUebergabeIntent);



        }


    }.start();    //Ende Counter

    private void resetTimer()
    {
        timeLeftInMillis = startTimeInMillis;
    }


    //-----

    public void blinken()
    {
        //Rotes Blinken in den letzten 10 Sekunden

        if (counter+1==10)
        {
            textView1.setTextColor(Color.RED);
            textView_score.setTextColor(Color.RED);
            textView_time.setTextColor(Color.RED);
            textView2.setTextColor(Color.RED);
            button1.setTextColor(Color.RED);
            button2.setTextColor(Color.RED);
            button3.setTextColor(Color.RED);
            button4.setTextColor(Color.RED);
            pause_btn.setTextColor(Color.RED);


        }
        if (counter+1==9)
        {

            textView1.setTextColor(Color.BLACK);
            textView_score.setTextColor(Color.BLACK);
            textView_time.setTextColor(Color.BLACK);
            textView2.setTextColor(Color.BLACK);
            button1.setTextColor(Color.BLACK);
            button2.setTextColor(Color.BLACK);
            button3.setTextColor(Color.BLACK);
            button4.setTextColor(Color.BLACK);
            pause_btn.setTextColor(Color.BLACK);

        }
        if (counter+1==8)
        {

            textView1.setTextColor(Color.RED);
            textView_score.setTextColor(Color.RED);
            textView_time.setTextColor(Color.RED);
            textView2.setTextColor(Color.RED);
            button1.setTextColor(Color.RED);
            button2.setTextColor(Color.RED);
            button3.setTextColor(Color.RED);
            button4.setTextColor(Color.RED);
            pause_btn.setTextColor(Color.RED);

        }
        if (counter+1==7)
        {

            textView1.setTextColor(Color.BLACK);
            textView_score.setTextColor(Color.BLACK);
            textView_time.setTextColor(Color.BLACK);
            textView2.setTextColor(Color.BLACK);
            button1.setTextColor(Color.BLACK);
            button2.setTextColor(Color.BLACK);
            button3.setTextColor(Color.BLACK);
            button4.setTextColor(Color.BLACK);
            pause_btn.setTextColor(Color.BLACK);

        }
        if (counter+1==6)
        {

            textView1.setTextColor(Color.RED);
            textView_score.setTextColor(Color.RED);
            textView_time.setTextColor(Color.RED);
            textView2.setTextColor(Color.RED);
            button1.setTextColor(Color.RED);
            button2.setTextColor(Color.RED);
            button3.setTextColor(Color.RED);
            button4.setTextColor(Color.RED);
            pause_btn.setTextColor(Color.RED);

        }
        if (counter+1==5)
        {

            textView1.setTextColor(Color.BLACK);
            textView_score.setTextColor(Color.BLACK);
            textView_time.setTextColor(Color.BLACK);
            textView2.setTextColor(Color.BLACK);
            button1.setTextColor(Color.BLACK);
            button2.setTextColor(Color.BLACK);
            button3.setTextColor(Color.BLACK);
            button4.setTextColor(Color.BLACK);
            pause_btn.setTextColor(Color.BLACK);

        }
        if (counter+1==4)
        {

            textView1.setTextColor(Color.RED);
            textView_score.setTextColor(Color.RED);
            textView_time.setTextColor(Color.RED);
            textView2.setTextColor(Color.RED);
            button1.setTextColor(Color.RED);
            button2.setTextColor(Color.RED);
            button3.setTextColor(Color.RED);
            button4.setTextColor(Color.RED);
            pause_btn.setTextColor(Color.RED);

        }
        if (counter+1==3)
        {

            textView1.setTextColor(Color.BLACK);
            textView_score.setTextColor(Color.BLACK);
            textView_time.setTextColor(Color.BLACK);
            textView2.setTextColor(Color.BLACK);
            button1.setTextColor(Color.BLACK);
            button2.setTextColor(Color.BLACK);
            button3.setTextColor(Color.BLACK);
            button4.setTextColor(Color.BLACK);
            pause_btn.setTextColor(Color.BLACK);

        }
        if (counter+1==2)
        {

            textView1.setTextColor(Color.RED);
            textView_score.setTextColor(Color.RED);
            textView_time.setTextColor(Color.RED);
            textView2.setTextColor(Color.RED);
            button1.setTextColor(Color.RED);
            button2.setTextColor(Color.RED);
            button3.setTextColor(Color.RED);
            button4.setTextColor(Color.RED);
            pause_btn.setTextColor(Color.RED);

        }
        if (counter+1==1)
        {

            textView1.setTextColor(Color.BLACK);
            textView_score.setTextColor(Color.BLACK);
            textView_time.setTextColor(Color.BLACK);
            textView2.setTextColor(Color.BLACK);
            button1.setTextColor(Color.BLACK);
            button2.setTextColor(Color.BLACK);
            button3.setTextColor(Color.BLACK);
            button4.setTextColor(Color.BLACK);
            pause_btn.setTextColor(Color.BLACK);

        }
    }

    public void timeFinished()
    {

        //textView_time = Integer.toString(counter);


        if (counter == 0)
        {

            counter = counter-0;
            timer.cancel();
            resetTimer();
            timer.onFinish();

        }


        //Log.e(Hauptklasse.class.getSimpleName(), "bla");

    }

    //-----




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

        }
        else
            {


            pause_check = false;
            pause_btn.setText("II");
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