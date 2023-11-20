package main.java.com.utmunchkin.utils;

public class DiceRoller {
    private int value;

    public DiceRoller() {
        this.value = 0;
    }

    public int getValue() {
        return this.value;
    }

    public void roll() {
        this.value = (int) (Math.random() * 6) + 1;
    }
}
