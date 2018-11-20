package com.example.abbad.douda;

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

    // 0: yellow, 1: red, 2: empty

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    //possible mouvements from a certain position except position 4
    int[][] possiblePositions = {{1, 3, 4}, {0, 4, 2}, {1, 4, 5}, {0,4, 6}, {0, 1, 2}, {2, 4, 8}, {3, 4, 7} ,{ 6, 4, 8}, {5, 4, 7} };
    int activePlayer = 0;
//possiblePositions[0]
    boolean gameActive = true;
    //boolean gameIsDraw= false;
    //int roundCounter=0;
    //*******
    //star==red shell==yellow
    //octupus==red goldfish==yellow
    //*******
    boolean movePiece = false;
    int numberOfRedPlayed=0;
    int numberOfYellowPlayed=0;
    //might give us an error if the surroundings is already full
    int moveFrom=-1;
    ImageView pastCounter;

    //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        //Toast.makeText(this ,counter.getTag().toString()+" has been touched",Toast.LENGTH_LONG).show();
         TextView turn = (TextView) findViewById(R.id.turnTextView);
        if (gameActive  && ((activePlayer==0 && numberOfYellowPlayed<3)||(activePlayer == 1  && numberOfRedPlayed<3)) ) {
            if(gameState[tappedCounter] == 2)
            {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000);
            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.goldfish);
                numberOfYellowPlayed++;
                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.octupus);
                numberOfRedPlayed++;
                activePlayer = 0;

            }

            //counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
                counter.animate().translationYBy(1000).setDuration(300);
            }
        }
        //Yellow player have already played 3 times
        //else if(gameActive && gameState[tappedCounter] == 0 && (activePlayer==0 && numberOfYellowPlayed<3))
          else if(gameActive && activePlayer == 0)
        {
             if(!movePiece)
             {
                 if(gameState[tappedCounter] == 0) {
                     pastCounter = counter;
                     movePiece = true;
                     gameState[tappedCounter] = 2;//this place become free
                     counter.setImageDrawable(null);
                     moveFrom = tappedCounter;
                 }

             }else{
                 //see if this move is possible
                 if(gameState[tappedCounter] == 2 && ( (moveFrom==4 && tappedCounter!=4) ||(tappedCounter == possiblePositions[moveFrom][0] || tappedCounter == possiblePositions[moveFrom][1] || tappedCounter == possiblePositions[moveFrom][2] ))) {

                     counter.setImageResource(R.drawable.goldfish);
                     gameState[tappedCounter] = 0;
                     activePlayer = 1;
                 }
                 else
                 {
                     //move failed return to previous state
                     gameState[moveFrom] = 0;
                     pastCounter.setImageResource(R.drawable.goldfish);
                 }
                 moveFrom=-1;
                 movePiece = false;
             }
        }
        //Red player have already played 3 times
        //else if(gameActive && gameState[tappedCounter] == 1 && (activePlayer==1 && numberOfRedPlayed<3))
          else if(gameActive && activePlayer==1)
        {
            if(!movePiece)
            {
                if(gameState[tappedCounter] == 1) {
                    movePiece = true;
                    pastCounter = counter;
                    gameState[tappedCounter] = 2;//this place become free
                    counter.setImageDrawable(null);
                    moveFrom = tappedCounter;
                }

            }else{
                //see if this move is possible
                if(gameState[tappedCounter] == 2 && ( (moveFrom==4 && tappedCounter!=4) ||(tappedCounter == possiblePositions[moveFrom][0] || tappedCounter == possiblePositions[moveFrom][1] || tappedCounter == possiblePositions[moveFrom][2] ))) {
                    counter.setImageResource(R.drawable.octupus);
                    gameState[tappedCounter] = 1;
                    activePlayer = 0;
                } else
                {
                    //move failed return to previous state
                    gameState[moveFrom] = 1;
                    pastCounter.setImageResource(R.drawable.octupus);
                }
                moveFrom=-1;
                movePiece = false;
            }
        }
        if(activePlayer==0){
            //turn.setText("     Yellow's turn");
            turn.setText("     goldfish's turn");
        }
        else{
            //turn.setText("Red's turn");
            turn.setText("     octupus's turn");
        }
        //if(!gameActive)turn.setVisibility(view.INVISIBLE);

        for (int[] winningPosition : winningPositions) {

            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                // Someone has won!

                gameActive = false;

                String winner ;

                if (activePlayer == 1) {

                    //winner = "Yellow";
                    winner = "goldfish";

                } else {

                   // winner = "Red";
                    winner = "octupus";

                }

               // Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                winnerTextView.setText(winner + " has won!");

                //playAgainButton.setVisibility(View.VISIBLE);

                winnerTextView.setVisibility(View.VISIBLE);
                turn.setVisibility(view.INVISIBLE);

            }

        }

    }

    public void playAgain(View view) {

        //Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        //playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);
        TextView turn = (TextView) findViewById(R.id.turnTextView);

        turn.setVisibility(view.VISIBLE);
        turn.setText("     goldfish's turn");

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 2;

        }

        activePlayer = 0;

        gameActive = true;

        numberOfRedPlayed = 0;

        numberOfYellowPlayed = 0;
       // roundCounter=0;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

    }
}
