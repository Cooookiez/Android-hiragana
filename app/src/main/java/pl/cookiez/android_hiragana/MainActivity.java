package pl.cookiez.android_hiragana;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AdView mAdView;
    String adMobAppId = "ca-app-pub-9317173642585148~2178125680";
    String adMobUnitId = "ca-app-pub-9317173642585148/9198829602";

    String[] bttsC = new String[9];

    int hmChars = 48;
    int whichCharIsTrue;
    String[] lati = new String[hmChars];
    String[] hira = new String[hmChars];
    Button[] btts = new Button[9];
    Button btt_next;
    TextView tv_what_is_it;
    TextView tv_hira2lati_correct;
    TextView tv_hira2lati_wrong;
    Random rand;
    boolean podgladPoprawnej = false;
    SharedPreferences sharedPreferences;

    public void check(int btt_nr){
        if(!podgladPoprawnej){ //w tje chwili jest wyświetlona poprawna odpowiedź
            podgladPoprawnej = true;

            //kturt guzik jest dobry
            int correctBotton = 0;
            for(int i = 0; i < 9; i++){
                if(btts[i].getText().toString() == lati[whichCharIsTrue]){
                    correctBotton = i;
                    Log.i("correct btt", String.valueOf(correctBotton));
                    break;
                }
            }

            //dodanie punktu do jednej z zmiennyhc trwałyhc
            if(correctBotton == btt_nr){//poprawne
                int val = sharedPreferences.getInt("hira2lati_correct",0);
                val++;
                sharedPreferences.edit().putInt("hira2lati_correct", val).apply();
            }else{//niepoprawne
                int val = sharedPreferences.getInt("hira2lati_wrong",0);
                val++;
                sharedPreferences.edit().putInt("hira2lati_wrong", val).apply();
            }
            writeScore();

            //"Wyczyszcza wszystkie guziki
            for(int i = 0; i < 9; i++){
                btts[i].setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                btts[i].setTextColor(getResources().getColor(R.color.colorPrimary));
            }

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

    private void writeScore(){
        int correct = sharedPreferences.getInt("hira2lati_correct", 0);
        int wrong = sharedPreferences.getInt("hira2lati_wrong", 0);

        Log.i("writeScore_correct",String.valueOf(correct));
        Log.i("writeScore_wrong",String.valueOf(wrong));

        if(correct>9999) tv_hira2lati_correct.setText("9999+");
        else tv_hira2lati_correct.setText(String.valueOf(correct));

        if(wrong>9999) tv_hira2lati_wrong.setText("9999+");
        else tv_hira2lati_wrong.setText(String.valueOf(wrong));
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
        whichCharIsTrue = rand.nextInt(hmChars);
        tv_what_is_it.setText(hira[whichCharIsTrue]);
        bttsC[0] = lati[whichCharIsTrue];

        //przypisanie do guzików innych losowych odp
        Boolean alreadyExist = false;
        for(int i = 1; i < 9; i++){
            do{
                alreadyExist = false;
                bttsC[i] = lati[rand.nextInt(hmChars)];
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

    private void initVariables(){

        sharedPreferences = this.getSharedPreferences("pl.cookiez.android_hiragana", Context.MODE_PRIVATE);

        btt_next = (Button)findViewById(R.id.btt_next);
        tv_what_is_it = (TextView)findViewById(R.id.tv_what_is_it);
        tv_hira2lati_correct = (TextView)findViewById(R.id.tv_hira2lati_correct);
        tv_hira2lati_wrong = (TextView)findViewById(R.id.tv_hira2lati_wrong);
        rand = new Random();

        btts[0] = (Button)findViewById(R.id.btt_1);
        btts[1] = (Button)findViewById(R.id.btt_2);
        btts[2] = (Button)findViewById(R.id.btt_3);
        btts[3] = (Button)findViewById(R.id.btt_4);
        btts[4] = (Button)findViewById(R.id.btt_5);
        btts[5] = (Button)findViewById(R.id.btt_6);
        btts[6] = (Button)findViewById(R.id.btt_7);
        btts[7] = (Button)findViewById(R.id.btt_8);
        btts[8] = (Button)findViewById(R.id.btt_9);

        int nr = 0;
        lati[nr] = "a";     hira[nr]="あ";   nr++;
        lati[nr] = "i";     hira[nr]="い";   nr++;
        lati[nr] = "u";     hira[nr]="う";   nr++;
        lati[nr] = "e";     hira[nr]="え";   nr++;
        lati[nr] = "o";     hira[nr]="お";   nr++;

        lati[nr] = "ka";    hira[nr]="か";   nr++;
        lati[nr] = "ki";    hira[nr]="き";   nr++;
        lati[nr] = "ku";    hira[nr]="く";   nr++;
        lati[nr] = "ke";    hira[nr]="け";   nr++;
        lati[nr] = "ko";    hira[nr]="こ";   nr++;

        lati[nr] = "sa";    hira[nr]="さ";   nr++;
        lati[nr] = "si";    hira[nr]="し";   nr++;
        lati[nr] = "su";    hira[nr]="す";   nr++;
        lati[nr] = "se";    hira[nr]="せ";   nr++;
        lati[nr] = "so";    hira[nr]="そ";   nr++;

        lati[nr] = "ta";    hira[nr]="た";   nr++;
        lati[nr] = "ti";    hira[nr]="ち";   nr++;
        lati[nr] = "tu";    hira[nr]="つ";   nr++;
        lati[nr] = "te";    hira[nr]="て";   nr++;
        lati[nr] = "to";    hira[nr]="と";   nr++;

        lati[nr] = "na";    hira[nr]="な";   nr++;
        lati[nr] = "ni";    hira[nr]="に";   nr++;
        lati[nr] = "nu";    hira[nr]="ぬ";   nr++;
        lati[nr] = "ne";    hira[nr]="ね";   nr++;
        lati[nr] = "no";    hira[nr]="の";   nr++;

        lati[nr] = "ha";    hira[nr]="は";   nr++;
        lati[nr] = "hi";    hira[nr]="ひ";   nr++;
        lati[nr] = "hu";    hira[nr]="ふ";   nr++;
        lati[nr] = "he";    hira[nr]="へ";   nr++;
        lati[nr] = "ho";    hira[nr]="ほ";   nr++;

        lati[nr] = "ma";    hira[nr]="ま";   nr++;
        lati[nr] = "mi";    hira[nr]="み";   nr++;
        lati[nr] = "mu";    hira[nr]="む";   nr++;
        lati[nr] = "me";    hira[nr]="め";   nr++;
        lati[nr] = "mo";    hira[nr]="も";   nr++;

        lati[nr] = "ya";    hira[nr]="や";   nr++;
        lati[nr] = "yu";    hira[nr]="ゆ";   nr++;
        lati[nr] = "yo";    hira[nr]="よ";   nr++;

        lati[nr] = "ra";    hira[nr]="ら";   nr++;
        lati[nr] = "ri";    hira[nr]="り";   nr++;
        lati[nr] = "ru";    hira[nr]="る";   nr++;
        lati[nr] = "re";    hira[nr]="れ";   nr++;
        lati[nr] = "ro";    hira[nr]="ろ";   nr++;

        lati[nr] = "wa";    hira[nr]="わ";   nr++;
        lati[nr] = "wi";    hira[nr]="ゐ";   nr++;
        lati[nr] = "we";    hira[nr]="ゑ";   nr++;
        lati[nr] = "wo";    hira[nr]="を";   nr++;
        lati[nr] = "nn";    hira[nr]="ん";   nr++;
    }

    private void initAdMob(){

        MobileAds.initialize(this, adMobAppId);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(adMobUnitId);

        mAdView = findViewById(R.id.adBaner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*  toolbar */
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            //toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        initVariables();
        initAdMob();

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
