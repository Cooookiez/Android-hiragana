package pl.cookiez.android_hiragana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Map<String, String> hira = new HashMap<String, String>();

    String[] btts = new String[9];

    Button btt_1;
    Button btt_2;
    Button btt_3;
    Button btt_4;
    Button btt_5;
    Button btt_6;
    Button btt_7;
    Button btt_8;
    Button btt_9;
    Button btt_next;

    TextView tv_what_is_it;

    Random rand;

    String what_is_hira;
    String what_is_lati;

    public void losuj(){

        Log.i("losuj", "start");

        int hira_size = hira.size();
        int nth;
        boolean jestPowturzenie;

        //odpowiedz
        nth = rand.nextInt(hira_size);
        what_is_hira = (new ArrayList<String>(hira.values())).get(nth);
        what_is_lati = (new ArrayList<String>(hira.keySet())).get(nth);
        Log.i("what_is_hira", String.valueOf(what_is_hira));
        Log.i("what_is_lati", String.valueOf(what_is_lati));
        tv_what_is_it.setText(what_is_hira);
        btts[0] = what_is_lati;

        //guziki
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hira.put("a", "あ");
        hira.put("i", "い");
        hira.put("u", "う");
        hira.put("e", "え");
        hira.put("o", "お");

        hira.put("ka", "か");
        hira.put("ki", "き");
        hira.put("ku", "く");
        hira.put("ke", "け");
        hira.put("ko", "こ");

        hira.put("sa", "さ");
        hira.put("si", "し");
        hira.put("su", "す");
        hira.put("se", "せ");
        hira.put("so", "そ");

        hira.put("ta", "た");
        hira.put("ti", "ち");
        hira.put("tu", "つ");
        hira.put("te", "て");
        hira.put("to", "と");

        hira.put("na", "な");
        hira.put("ni", "に");
        hira.put("nu", "ぬ");
        hira.put("ne", "ね");
        hira.put("no", "の");

        hira.put("ha", "は");
        hira.put("hi", "ひ");
        hira.put("hu", "ふ");
        hira.put("he", "へ");
        hira.put("ho", "ほ");

        hira.put("ma", "ま");
        hira.put("mi", "み");
        hira.put("mu", "む");
        hira.put("me", "め");
        hira.put("mo", "も");

        hira.put("ya", "や");
        hira.put("yu", "ゆ");
        hira.put("yo", "よ");

        hira.put("ra", "ら");
        hira.put("ri", "り");
        hira.put("ru", "る");
        hira.put("re", "れ");
        hira.put("ro", "ろ");

        hira.put("wa", "わ");
        hira.put("wo", "を");

        btt_1 = (Button)findViewById(R.id.btt_1);
        btt_2 = (Button)findViewById(R.id.btt_2);
        btt_3 = (Button)findViewById(R.id.btt_3);
        btt_4 = (Button)findViewById(R.id.btt_4);
        btt_5 = (Button)findViewById(R.id.btt_5);
        btt_6 = (Button)findViewById(R.id.btt_6);
        btt_7 = (Button)findViewById(R.id.btt_7);
        btt_8 = (Button)findViewById(R.id.btt_8);
        btt_9 = (Button)findViewById(R.id.btt_9);

        btt_next = (Button)findViewById(R.id.btt_next);

        tv_what_is_it = (TextView)findViewById(R.id.tv_what_is_it);

        rand = new Random();

        btt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("btt next", "start");
                losuj();
            }
        });

    }
}
