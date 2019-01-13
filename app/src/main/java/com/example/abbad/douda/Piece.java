package com.example.abbad.douda;

public class Piece {
    int owner;
    int position;
    boolean selected ;
    int id;
    int pieceImage;
    Piece(int owner, int position) {
        this.selected = false;
        this.owner = owner;
        this.position = position;
        this.id= owner +1;
        pieceImage = R.drawable.piece1 + owner;
    }
}
