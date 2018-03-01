package pl.cookiez.android_hiragana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Map<String, String> hira = new HashMap<String, String>();

    Button btt_00;
    Button btt_01;
    Button btt_02;
    Button btt_10;
    Button btt_11;
    Button btt_12;
    Button btt_20;
    Button btt_21;
    Button btt_22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hira.put("a", "あ");
        hira.put("i", "dada");
        hira.put("u", "あ");
        hira.put("e", "あ");
        hira.put("o", "あ");

        hira.put("ka", "あ");
        hira.put("ki", "あ");
        hira.put("ku", "あ");
        hira.put("ke", "あ");
        hira.put("ko", "あ");

        hira.put("sa", "あ");
        hira.put("si", "あ");
        hira.put("su", "あ");
        hira.put("se", "あ");
        hira.put("so", "あ");

        hira.put("ta", "あ");
        hira.put("ti", "あ");
        hira.put("tu", "あ");
        hira.put("te", "あ");
        hira.put("to", "あ");

        hira.put("na", "あ");
        hira.put("ni", "あ");
        hira.put("nu", "あ");
        hira.put("ne", "あ");
        hira.put("no", "あ");

        hira.put("ha", "あ");
        hira.put("hi", "あ");
        hira.put("hu", "あ");
        hira.put("he", "あ");
        hira.put("ho", "あ");

        hira.put("ma", "あ");
        hira.put("mi", "あ");
        hira.put("mu", "あ");
        hira.put("me", "あ");
        hira.put("mo", "あ");

        hira.put("ya", "あ");
        hira.put("yu", "あ");
        hira.put("yo", "あ");

        hira.put("ra", "あ");
        hira.put("ri", "あ");
        hira.put("ru", "あ");
        hira.put("re", "あ");
        hira.put("ro", "あ");

        hira.put("wa", "あ");
        hira.put("wi", "あ");
        hira.put("we", "あ");
        hira.put("wo", "あ");

        btt_00 = (Button)findViewById(R.id.btt_00);
        btt_01 = (Button)findViewById(R.id.btt_01);
        btt_02 = (Button)findViewById(R.id.btt_02);
        btt_10 = (Button)findViewById(R.id.btt_10);
        btt_11 = (Button)findViewById(R.id.btt_11);
        btt_12 = (Button)findViewById(R.id.btt_12);
        btt_20 = (Button)findViewById(R.id.btt_20);
        btt_21 = (Button)findViewById(R.id.btt_21);
        btt_22 = (Button)findViewById(R.id.btt_22);

    }
}
