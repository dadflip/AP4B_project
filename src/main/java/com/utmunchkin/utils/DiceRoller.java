package main.java.com.utmunchkin.utils;

import main.java.com.utmunchkin.Constant;

/**
 * A class representing a dice roller for the Munchkin game.
 */
public class DiceRoller {
    private int value;

    /**
     * Initializes a new instance of the DiceRoller class.
     */
    public DiceRoller() {
        this.value = 0;
    }

    /**
     * Gets the current value of the dice.
     *
     * @return The current value of the dice.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Rolls the dice and updates its value.
     */
    public void roll() {
        this.value = (int) (Math.random() * Constant.DICE_SIDES) + 1;
    }
}
