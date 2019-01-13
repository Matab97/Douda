package com.example.abbad.douda;

public class Player {
    public int getPlayerid() {
        return playerid;
    }

    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    int score;
    int playerid;
    Player(int id) {
        this.playerid = id;
    }
}
