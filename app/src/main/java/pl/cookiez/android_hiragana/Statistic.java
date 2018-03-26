package pl.cookiez.android_hiragana;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Statistic extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    TextView tv_h2l_correct;
    TextView tv_h2l_wrong;
    TextView tv_l2h_correct;
    TextView tv_l2h_wrong;

    Button btt_clearHira2Lati;
    Button btt_clearLati2Hira;

    private void writeScore(){
        int h2l_correct = sharedPreferences.getInt("hira2lati_correct", 0);
        int h2l_wrong   = sharedPreferences.getInt("hira2lati_wrong", 0);
        int l2h_correct = sharedPreferences.getInt("lati2hira_correct", 0);
        int l2h_wrong   = sharedPreferences.getInt("lati2hira_wrong", 0);

        tv_h2l_correct.setText(String.valueOf(h2l_correct));
        tv_h2l_wrong.setText(String.valueOf(h2l_wrong));
        tv_l2h_correct.setText(String.valueOf(l2h_correct));
        tv_l2h_wrong.setText(String.valueOf(l2h_wrong));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        sharedPreferences = this.getSharedPreferences("pl.cookiez.android_hiragana", Context.MODE_PRIVATE);

        tv_h2l_correct = (TextView)findViewById(R.id.tv_h2l_correct);
        tv_h2l_wrong   = (TextView)findViewById(R.id.tv_h2l_wrong);
        tv_l2h_correct = (TextView)findViewById(R.id.tv_l2h_correct);
        tv_l2h_wrong   = (TextView)findViewById(R.id.tv_l2h_wrong);

        writeScore();

        btt_clearHira2Lati = (Button)findViewById(R.id.btt_clearHira2Lati);
        btt_clearLati2Hira = (Button)findViewById(R.id.btt_clearLati2Hira);

        btt_clearHira2Lati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().putInt("hira2lati_correct", 0).apply();
                sharedPreferences.edit().putInt("hira2lati_wrong", 0).apply();
                writeScore();
            }
        });
        btt_clearLati2Hira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().putInt("lati2hira_correct", 0).apply();
                sharedPreferences.edit().putInt("lati2hira_wrong", 0).apply();
                writeScore();
            }
        });

    }
}
