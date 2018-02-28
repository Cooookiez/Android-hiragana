package pl.cookiez.android_hiragana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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
