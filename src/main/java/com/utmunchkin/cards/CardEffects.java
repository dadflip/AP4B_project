package main.java.com.utmunchkin.cards;

import main.java.com.utmunchkin.players.Player;

public class CardEffects {

    private CardEffects() {
        // Private constructor to prevent instantiation, as this class contains only static methods
    }

    public static void applySwordEffect(Player player) {
        // Implement the effect of Sword of Power
        System.out.println(player.getName() + " is applying the effect of Sword of Power");
    }

    public static void applyPotionEffect(Player player) {
        // Implement the effect of Dragon's Breath Potion
        System.out.println(player.getName() + " is applying the effect of Dragon's Breath Potion");
    }

    // Add more effect methods as needed
}
