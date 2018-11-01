package com.example.giannirandone.spiel1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GameOverAusgabeseite extends AppCompatActivity implements View.OnClickListener
{


   TextView textView_scoreAusgabe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_ausgabeseite);


        textView_scoreAusgabe = (TextView) findViewById(R.id.textView_scoreAusgabe);

        Intent getScoreUebergabe = getIntent();
        String scoreIntent = getIntent().getStringExtra("ScoreUebergabeIntent");
        textView_scoreAusgabe.setText(scoreIntent);

    }


    @Override
    public void onClick(View v)
    {

        //textView_scoreAusgabe.setText("");
        Intent getScoreUebergabe = getIntent();
        String scoreIntent = getIntent().getStringExtra("ScoreUebergabeIntent");
        textView_scoreAusgabe.setText(scoreIntent);



    }



}
