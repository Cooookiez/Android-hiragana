package pl.cookiez.android_hiragana;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    String adMobAppId = "ca-app-pub-9317173642585148~2178125680";
    String adMobUnitId = "ca-app-pub-9317173642585148/9198829602";

    private Button btt_hira2lati;
    private Button btt_lati2hira;
    private Button btt_stat;

    private void setAdMob(){
        MobileAds.initialize(this, "ca-app-pub-9317173642585148~2178125680"); //releace

        MobileAds.initialize(this, adMobAppId);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId("ca-app-pub-9317173642585148/9198829602"); //releace

        mAdView = findViewById(R.id.mainAdBaner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setAdMob();

        btt_hira2lati = (Button)findViewById(R.id.btt_hira2lati);
        btt_lati2hira = (Button)findViewById(R.id.btt_lati2hira);

        btt_stat = (Button)findViewById(R.id.btt_stat);


        btt_hira2lati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Hiragana2Latian.class));
            }
        });
        btt_lati2hira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Latian2Hiragana.class));
            }
        });

        btt_stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Statistic.class));
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
