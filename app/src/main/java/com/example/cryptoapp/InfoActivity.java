package com.example.cryptoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InfoActivity extends AppCompatActivity {

    private ImageButton btc, eth, xrp, doge;
    BottomNavigationView bottomNav;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        btc = (ImageButton) findViewById(R.id.bitcoin);
        btc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.coindesk.com/price/bitcoin/"));
                startActivity(browse);
            }
        });
        eth = (ImageButton) findViewById(R.id.ethereum);
        eth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.coindesk.com/price/ethereum/"));
                startActivity(browse);
            }
        });
        xrp = (ImageButton) findViewById(R.id.ripple);
        xrp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.coindesk.com/price/xrp/"));
                startActivity(browse);
            }
        });
        doge = (ImageButton) findViewById(R.id.dogecoin);
        doge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.coindesk.com/price/doge/"));
                startActivity(browse);
            }
        });


        bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.info);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.user:
                        startActivity(new Intent(getApplicationContext(), UserActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.info:
                        return true;
                }
                return false;
            }
        });

        }
}
