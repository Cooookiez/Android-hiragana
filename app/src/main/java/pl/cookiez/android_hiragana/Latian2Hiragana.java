package pl.cookiez.android_hiragana;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

public class Latian2Hiragana extends AppCompatActivity {

    private AdView mAdView;

    private Chars chars = new Chars();

    String[] bttsC = new String[9];
    Button[] btts = new Button[9];
    int whichCharIsTrue;

    Button btt_next;
    TextView tv_what_is_it;
    TextView tv_correct;
    TextView tv_wrong;

    Random rand;
    boolean podgladPoprawnej = false;
    SharedPreferences sharedPreferences;


    private void setAdMob(){
        MobileAds.initialize(this, "ca-app-pub-9317173642585148~2178125680");

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId("ca-app-pub-9317173642585148/9198829602");

        mAdView = findViewById(R.id.mainAdBaner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void setVariables(){
        sharedPreferences = this.getSharedPreferences("pl.cookiez.android_hiragana", Context.MODE_PRIVATE);

        btt_next = (Button)findViewById(R.id.btt_next);
        tv_what_is_it = (TextView)findViewById(R.id.tv_what_is_it);
        tv_correct = (TextView)findViewById(R.id.tv_correct);
        tv_wrong = (TextView)findViewById(R.id.tv_wrong);
        rand = new Random();

        btts[0] = (Button)findViewById(R.id.btt_0);
        btts[1] = (Button)findViewById(R.id.btt_1);
        btts[2] = (Button)findViewById(R.id.btt_2);
        btts[3] = (Button)findViewById(R.id.btt_3);
        btts[4] = (Button)findViewById(R.id.btt_4);
        btts[5] = (Button)findViewById(R.id.btt_5);
        btts[6] = (Button)findViewById(R.id.btt_6);
        btts[7] = (Button)findViewById(R.id.btt_7);
        btts[8] = (Button)findViewById(R.id.btt_8);

    }

    private void writeScore(){
        int correct = sharedPreferences.getInt("lati2hira_correct", 0);
        int wrong = sharedPreferences.getInt("lati2hira_wrong", 0);

        if(correct>9999) tv_correct.setText("9999+");
        else tv_correct.setText(String.valueOf(correct));

        if(wrong>9999) tv_wrong.setText("9999+");
        else tv_wrong.setText(String.valueOf(wrong));
    }

    private void clearButtons(){
        for(int i = 0; i < 9; i++){
            btts[i].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            btts[i].setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }

    private void losuj(){
        podgladPoprawnej = false;
        clearButtons();

        //odpowiedz
        whichCharIsTrue = rand.nextInt(chars.hmChars);
        tv_what_is_it.setText(chars.lati[whichCharIsTrue]);
        bttsC[0] = chars.hira[whichCharIsTrue];

        //przypisanie do guzików innych losowych odp
        Boolean alreadyExist = false;
        for(int i = 1; i < 9; i++){
            do{
                alreadyExist = false;
                bttsC[i] = chars.hira[rand.nextInt(chars.hmChars)];
                for(int j = 0; j < i; j++){
                    if(bttsC[i] == bttsC[j]){
                        alreadyExist = true;
                        break;
                    }
                }
            }while(alreadyExist);
        }

        //mieszanie guzików
        for(int i = 8; i > 0; i--){
            int index = rand.nextInt(i + 1);
            // Simple swap
            String a = bttsC[index];
            bttsC[index] = bttsC[i];
            bttsC[i] = a;
        }

        //wypisanie guzików
        for(int i = 0; i < 9; i++){
            btts[i].setText(bttsC[i]);
        }
    }

    private void check(int btt_nr){
        if(!podgladPoprawnej){ //w tje chwili jest wyświetlona poprawna odpowiedź
            podgladPoprawnej = true;

            //kturt guzik jest dobry
            int correctBotton = 0;
            for(int i = 0; i < 9; i++){
                if(btts[i].getText().toString() == chars.hira[whichCharIsTrue]){
                    correctBotton = i;
                    Log.i("correct btt", String.valueOf(correctBotton));
                    break;
                }
            }

            //dodanie punktu do jednej z zmiennyhc trwałyhc
            if(correctBotton == btt_nr){//poprawne
                int val = sharedPreferences.getInt("lati2hira_correct",0);
                val++;
                sharedPreferences.edit().putInt("lati2hira_correct", val).apply();
            }else{//niepoprawne
                int val = sharedPreferences.getInt("lati2hira_wrong",0);
                val++;
                sharedPreferences.edit().putInt("lati2hira_wrong", val).apply();
            }
            writeScore();

            //podkreslenie nacisniety przez usr guzik na czerwono
            btts[btt_nr].setBackgroundColor(getResources().getColor(R.color.WrongColorPrimary));
            btts[btt_nr].setTextColor(getResources().getColor(R.color.WrongColorAccent));
            //podkreslna dobry guzik na zielono (nadpisuje guzik usr'a jezli ten kliknol dobrze)
            btts[correctBotton].setBackgroundColor(getResources().getColor(R.color.CorrectColorPrimary));
            btts[correctBotton].setTextColor(getResources().getColor(R.color.CorrectColorAccent));

        }else{
            losuj();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3x3);

        setAdMob();
        setVariables();
        writeScore();
        losuj();

        btt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                losuj();
            }
        });
        btts[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(0);
            }
        });
        btts[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(1);
            }
        });
        btts[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(2);
            }
        });
        btts[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(3);
            }
        });
        btts[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(4);
            }
        });
        btts[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(5);
            }
        });
        btts[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(6);
            }
        });
        btts[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(7);
            }
        });
        btts[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(8);
            }
        });

    }


}
