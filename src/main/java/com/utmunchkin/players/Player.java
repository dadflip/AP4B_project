package main.java.com.utmunchkin.players;
public class Player extends ListOfPlayer {
    private String name;
    private int score;
    private int turn;
    private int number;

    public Player(String name, int number) {
        this.name = name;
        this.score = 0;
        this.turn = 0;
        this.number = number;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public int getTurn() {
        return this.turn;
    }

    public int getNumber() {
        return this.number;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void addTurn() {
        this.turn++;
    }

    public void resetTurn() {
        this.turn = 0;
    }

    public void resetScore() {
        this.score = 0;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setTurn(int turn) {
        this.turn = turn;
    }
    public void reset() {
        this.resetTurn();
        this.resetScore();
    }

    public String toString() {
        return this.name + " (" + this.score + ")";
    }



}
