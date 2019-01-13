package com.example.abbad.douda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class BoardShopActivity extends AppCompatActivity {

    //il faut verifier que le joueur est connecté pour prendre son score
    int score=10000;
    int boardPrice[] = {0,1000,1200,1200,1500,1500,1500};
    boolean boardObtenue[] ={false,false,false,false,false,false,false};
    public void touchboard(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(boardObtenue[tappedCounter]) Toast.makeText(this,"board already purchased",Toast.LENGTH_LONG).show();
        else if(boardPrice[tappedCounter]<=score){score-=boardPrice[tappedCounter];boardObtenue[tappedCounter]=true;
        Toast.makeText(this,"CONGRATSSSSSSSSSSS!!!!!",Toast.LENGTH_LONG).show();}
        else Toast.makeText(this,"score not enough",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_shop);

    }
}
