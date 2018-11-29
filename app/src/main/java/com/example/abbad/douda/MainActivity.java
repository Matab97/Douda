package com.example.abbad.douda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

public class MainActivity extends AppCompatActivity {

boolean vsplayer;
public void clickVsPAi(View view){
        Intent intent =new Intent(getApplicationContext(),vsAiActivity.class);
        startActivity(intent);
    }

    public void clickVsPlayer(View view)
    {
        Intent intent =new Intent(getApplicationContext(),vsPlayerActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

    }
}
