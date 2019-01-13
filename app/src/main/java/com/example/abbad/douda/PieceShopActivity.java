package com.example.abbad.douda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class PieceShopActivity extends AppCompatActivity {

    //il faut verifier que le joueur est connecté pour prendre son score
    int score=10000;
    int piecePrice[] = {0,25,25,100,100,200,200,200,350,350,500,500};
    boolean pieceObtenue[] ={false,false,false,false,false,false,false,false,false,false,false,false,false};
    public void touchpiece(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

            if(pieceObtenue[tappedCounter]) Toast.makeText(this,"board already purchased",Toast.LENGTH_LONG).show();
            else if(piecePrice[tappedCounter]<=score){score-=piecePrice[tappedCounter];pieceObtenue[tappedCounter]=true;}
            else Toast.makeText(this,"score not enough",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_shop);
    }
}
