package com.example.abbad.douda;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class vsPlayerActivity extends AppCompatActivity {

    Board board = new Board();
    Piece p ;
    List<Piece> PieceList = new ArrayList<Piece>();
    Player player1 = new Player(0);
    Player player2 = new Player(1);

    int moveFrom=-1;
    ImageView pastCounter;
    int ptCounter;

    public boolean checkWin(int[] game) {
        boolean g = false;

        for (int[] winningPosition : board.winningPositions) {

            if (game[winningPosition[0]] == game[winningPosition[1]] && game[winningPosition[1]] == game[winningPosition[2]] && game[winningPosition[0]] != 2) {
                // Someone has won!
                g = true;
            }
        }
        return g;
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        //Toast.makeText(this ,counter.getTag().toString()+" has been touched",Toast.LENGTH_LONG).show();
        TextView turn = (TextView) findViewById(R.id.turnTextView);
        if (board.gameActive && board.numberplayed < 6) {
            if (board.gameState[tappedCounter] == 2) {
                //Toast.makeText(getBaseContext(),"Minimax: "+ tappedCounter,Toast.LENGTH_LONG).show();
                board.numberplayed++;
                counter.setTranslationY(-1000);
                if (board.activePlayer == 0) {
                    board.drop(tappedCounter,board.activePlayer);
                    p = new Piece(board.activePlayer, tappedCounter);
                    PieceList.add(p);
                    counter.setImageResource(p.pieceImage);
                    board.activePlayer = 1;

                } else {
                    p = new Piece(board.activePlayer, tappedCounter);
                    board.drop(tappedCounter,board.activePlayer);
                    counter.setImageResource(p.pieceImage);
                    PieceList.add(p);
                    board.activePlayer = 0;
                }
                //counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
                counter.animate().translationYBy(1000).setDuration(200);
            }
        }
        //Yellow player have already played 3 times
        //else if(gameActive && gameState[tappedCounter] == 0 && (activePlayer==0 && numberOfYellowPlayed<3))
        else if ((board.gameActive) && (board.activePlayer == 0)) {
            if (!board.movePiece) {
                if (this.board.gameState[tappedCounter] == player1.playerid) {
                    for (Piece Element : PieceList) {
                        if (Element.position == tappedCounter) { Element.selected = true; } }
                    pastCounter = counter;
                    ptCounter = tappedCounter;
                    board.selectpiece(tappedCounter);
                    counter.setImageDrawable(null);
                    moveFrom = tappedCounter;
                    board.movePiece = true;
                }
            } else {
                //see if this move is possible
                if (board.gameState[tappedCounter] == 2 && ((moveFrom == 4 && tappedCounter != 4) || (tappedCounter == board.possiblePositions[moveFrom][0] || tappedCounter == board.possiblePositions[moveFrom][1] || tappedCounter == board.possiblePositions[moveFrom][2]))) {

                    counter.setImageResource(p.pieceImage);
                    board.movepiece(tappedCounter,player1.playerid,player2.playerid);
                    for (Piece Element : PieceList) {
                        if (Element.position == moveFrom) { Element.position = tappedCounter; } }
                } else {
                    //move failed return to previous state
                    board.gameState[moveFrom] = board.activePlayer;
                    pastCounter.setImageResource(p.pieceImage);
                }
                this.moveFrom = -1;
                this.board.movePiece = false;
                for (Piece Element : PieceList) {
                    if (Element.position == tappedCounter) { Element.selected = false; } }

            }
        }
        //Red player have already played 3 times
        //else if(gameActive && gameState[tappedCounter] == 1 && (activePlayer==1 && numberOfRedPlayed<3))
        else if (board.gameActive && board.activePlayer == player2.playerid) {
            if (!board.movePiece) {
                if (board.gameState[tappedCounter] == player2.playerid) {
                     board.movePiece = true;
                     pastCounter = counter;
                    board.gameState[tappedCounter] = 2;//this place become free
                    counter.setImageDrawable(null);
                    moveFrom = tappedCounter;
                }

            } else {
                //see if this move is possible
                if (board.gameState[tappedCounter] == 2 && ((moveFrom == 4 && tappedCounter != 4) || (tappedCounter == board.possiblePositions[moveFrom][0] || tappedCounter == board.possiblePositions[moveFrom][1] || tappedCounter == board.possiblePositions[moveFrom][2]))) {
                    counter.setImageResource(p.pieceImage);
                    board.movepiece(tappedCounter,player2.playerid,player1.playerid);
                    for (Piece Element : PieceList) {
                        if (Element.position == moveFrom) { Element.position = tappedCounter; } }

                } else {
                    //move failed return to previous state
                    board.gameState[moveFrom] = player2.playerid;
                    pastCounter.setImageResource(p.pieceImage);
                }
                moveFrom = -1;
                board.movePiece = false;
            }
        }
        if (board.activePlayer == 0) {
            //turn.setText("     Yellow's turn");
            turn.setText("     P1 turn");
        } else {
            //turn.setText("Red's turn");
            turn.setText("     P2 turn");
        }
        //if(!gameActive)turn.setVisibility(view.INVISIBLE);


        if (checkWin(board.gameState)) {
            board.gameActive = false;
            // Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

            TextView winnerTextView = findViewById(R.id.winnerTextView);

            String winner;

            if (board.activePlayer == 1) {

                //winner = "Yellow";
                winner = "goldfish";

            } else {

                // winner = "Red";
                winner = "octupus";

            }

            winnerTextView.setText(winner + " has won!");

            //playAgainButton.setVisibility(View.VISIBLE);

            winnerTextView.setVisibility(View.VISIBLE);
            turn.setVisibility(View.INVISIBLE);
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

        for (int i = 0; i < board.gameState.length; i++) {

            board.gameState[i] = 2;

        }

            board.activePlayer = 0;

            board.gameActive = true;

        board.numberplayed=0;
        // roundCounter=0;

    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs_player);
    }
}
