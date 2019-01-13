package com.example.abbad.douda;

public class Piece {
    int owner;
    int position;
    boolean selected ;
    int id;
    Piece(int owner, int position) {
        this.selected = false;
        this.owner = owner;
        this.position = position;
        this.id= owner +1;
    }
}
