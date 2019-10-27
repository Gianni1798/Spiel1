package com.example.giannirandone.highestnumber;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class Settings extends AppCompatActivity {

    TextView settingsTextViewHighscoreAusgabe;
    Switch switch_Color;
    Button moreInformation;
    //AdView adView_Settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button moreInformations = (Button) findViewById(R.id.btn_moreInformations);
        moreInformations.setOnClickListener(v -> {
            startActivity(new Intent(Settings.this, MoreInformations.class ));
        });

        //adView_Settings = (AdView) findViewById(R.id.adView_Settings);

        //MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111");
        //AdRequest adRequest3 = new AdRequest.Builder().build();
        //adView_Settings.loadAd(adRequest3);

        settingsTextViewHighscoreAusgabe = (TextView) findViewById(R.id.settingsTextViewHighscoreAusgabe);
        switch_Color = (Switch) findViewById(R.id.switch_Color);

        SharedPreferences highscore = getSharedPreferences("score", 0);
        settingsTextViewHighscoreAusgabe.setText(Integer.toString(highscore.getInt("score", 0)));

        SharedPreferences colorCheck = this.getSharedPreferences("number", 0);
        int colorCheckNumber = colorCheck.getInt("number", 0);

        Log.i(Settings.class.getSimpleName(),"HIGHSCORE GESETZT");
        View viewSettings = this.getWindow().getDecorView();



        switch_Color.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                Log.i(Settings.class.getSimpleName(), "SWITCH ONCHECKED");
                int colorStatus = 0;

                SharedPreferences colorStatusNumber = getSharedPreferences("number", colorStatus);
                SharedPreferences.Editor editorColorStatus = colorStatusNumber.edit();

                if (b)
                {
                    colorStatus = 1;
                    editorColorStatus.putInt("number", colorStatus);
                    editorColorStatus.commit();
                    viewSettings.setBackgroundColor(Color.rgb(176,196,222));
                }else
                {
                    colorStatus = 0;
                    editorColorStatus.putInt("number", colorStatus);
                    editorColorStatus.commit();
                    viewSettings.setBackgroundColor(Color.WHITE);
                    Context contextWhite = getApplicationContext();
                    int durationWhite = Toast.LENGTH_SHORT;
                    CharSequence text = "Theme changed back to default";
                    Toast t1 = Toast.makeText(contextWhite,text, durationWhite);
                    t1.show();
                }
                Log.i(Settings.class.getSimpleName(), "SWITCH ONCHECKED 2");
            }
        });

    }

    protected void onStart()
    {
        super.onStart();
        SharedPreferences colorCheck = this.getSharedPreferences("number", 0);
        int colorCheckNumber = colorCheck.getInt("number", 0);
        loadInitialSwitchState(colorCheckNumber);
    }


    public void loadBackgroundColor(int colorCheckNumber)
    {
        View viewSettings = this.getWindow().getDecorView();

        if (colorCheckNumber == 0)
        {
            viewSettings.setBackgroundColor(Color.WHITE);
        }else if (colorCheckNumber == 1)
        {
            viewSettings.setBackgroundColor(Color.rgb(176,196,222));
        }
    }

    public void loadInitialSwitchState(int colorCheckNumber)
    {
        if (colorCheckNumber == 0)
        {
            switch_Color.setChecked(false);
        }else if (colorCheckNumber == 1)
        {
            switch_Color.setChecked(true);
        }
    }

    public void onClick(View v)
    {
        Log.i(Settings.class.getSimpleName(), "onClick_SETTINGS");
    }

}

