package com.example.abbad.douda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.abbad.douda.R.drawable.yellow;

public class MainActivity extends AppCompatActivity {

boolean vsplayer;
public void clickVsPAi(View view){
        Intent intent =new Intent(getApplicationContext(),vsAiActivity.class);
        startActivity(intent);
    }

    public void clickVsPlayer(View view)
    {
        Intent intent =new Intent(getApplicationContext(),chooseOponnetActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

    }
}
