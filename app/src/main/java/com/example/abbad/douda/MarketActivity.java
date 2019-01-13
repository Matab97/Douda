package com.example.abbad.douda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MarketActivity extends AppCompatActivity {


    public void boardshop(View view)
    {
        Intent intent =new Intent(getApplicationContext(),BoardShopActivity.class);
        startActivity(intent);
    }
    public void pieceshop(View view)
    {
        Intent intent =new Intent(getApplicationContext(),PieceShopActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
    }
}
