package main.java.com.utmunchkin.cards;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The CardData class manages the data for different game cards.
 */
public class CardData {

    /**
     * Enum representing the types of cards.
     */
    public enum CardType {
        TREASURE, DUNGEON
    }

    /**
     * Enum representing the subtypes of cards.
     */
    public enum SubType {
        ARMOR, WEAPON, POTION, HEADGEAR, FOOTGEAR, ONE_HAND, TWO_HANDS, SPECIAL, USABLE, CARRIABLE,
        BIG, SMALL, VALUABLE, JEWELRY, CONSUMABLE, ENCHANTED, LIMITED_USE, MAGICAL, RARE, COMMON,
        TRAP, PUZZLE, HAUNT, ENEMY, BOSS, DEADLY, UNDEAD, MYSTERIOUS, CURSED, TREACHEROUS, FEROCIOUS,
        DREADFUL, ANCIENT, VENOMOUS, EVIL, HAUNTED, DARK, MYTHICAL, MONSTER, CURSE, ACCESSORY
    }

    /**
     * Enum representing the types of monsters.
     */
    public enum MonstersType {
        ENEMY, BOSS, DEADLY, UNDEAD, MYSTERIOUS, CURSED, TREACHEROUS, FEROCIOUS,
        DREADFUL, ANCIENT, VENOMOUS, EVIL, HAUNTED, DARK, MYTHICAL, MONSTER
    }

    /**
     * Enum representing the types of treasures.
     */
    public enum TreasuresTypes {
        ARMOR, WEAPON, POTION, HEADGEAR, FOOTGEAR, ONE_HAND, TWO_HANDS, SPECIAL, USABLE, CARRIABLE,
        BIG, SMALL, VALUABLE, JEWELRY, CONSUMABLE, ENCHANTED, LIMITED_USE, MAGICAL, RARE, COMMON
    }

    /**
     * Enum representing the types of dungeons.
     */
    public enum DungeonsTypes {
        TRAP, PUZZLE, HAUNT
    }

    // Map to store card data
    private static Map<String, CardInfo> cardInfoMap = new HashMap<>();
    private static Map<String, CardInfo> availableCards = new HashMap<>();

    static {
        loadCardDataFromFile(new File("src/main/java/com/utmunchkin/cards/cards_data.csv"));
        resetAvailableCards();
    }

    /**
     * Processes a line of card data from the CSV file.
     *
     * @param line The line containing card data.
     */
    private static void processLine(String line) {
        String[] data = line.split(",");
        if (data.length == 7) {
            try {
                String cardName = data[0].trim();
                String description = data[1].trim();
                int levelBonus = Integer.parseInt(data[2].trim());
                boolean isCursed = Boolean.parseBoolean(data[3].trim());
                CardType cardType = CardType.valueOf(data[4].trim());
                SubType subType = SubType.valueOf(data[5].trim());
                String effectFunctionName = data[6].trim();

                CardInfo cardInfo = new CardInfo(cardName, description, levelBonus, isCursed, cardType, subType, effectFunctionName);
                cardInfoMap.put(cardName, cardInfo);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid data format: " + line);
            }
        } else {
            System.out.println("Invalid data format: " + line);
        }
    }

    /**
     * Loads card data from a CSV file.
     *
     * @param file The CSV file containing card data.
     */
    public static void loadCardDataFromFile(File file) {
        System.out.println("Loading card data from file: " + file.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines().forEach(CardData::processLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Card data loaded successfully!");
    }

    /**
     * Gets a random card information.
     *
     * @return A random CardInfo object.
     */
    public static CardInfo getRandomCardInfo() {
        if (availableCards.isEmpty()) {
            // All cards have been picked, reset the available cards
            resetAvailableCards();
        }

        // Pick a random card from the available cards
        String randomCardName = availableCards.keySet().stream().findAny().orElse(null);

        if (randomCardName != null) {
            // Remove the picked card from available cards
            availableCards.remove(randomCardName);

            // Return the picked card
            return cardInfoMap.get(randomCardName);
        } else {
            // No available cards (this should not happen with proper handling)
            return null;
        }
    }

    /**
     * Gets the CardInfo for a specific card name.
     *
     * @param cardName The name of the card.
     * @return The CardInfo object for the specified card name.
     */
    public static CardInfo getCardInfo(String cardName) {
        return cardInfoMap.get(cardName);
    }

    /**
     * The CardInfo class represents information about a card.
     */
    public static class CardInfo {
        private final String cardName;
        private final String description;
        private final int levelBonus;
        private final boolean isCursed;
        private final CardType cardType;
        private final SubType subType;
        private final String effectFunctionName;

        /**
         * Constructs a CardInfo object.
         *
         * @param cardName           The name of the card.
         * @param description        The description of the card.
         * @param levelBonus         The level bonus of the card.
         * @param isCursed           Whether the card is cursed.
         * @param cardType           The type of the card.
         * @param subType            The subtype of the card.
         * @param effectFunctionName The name of the effect function for the card.
         */
        public CardInfo(String cardName, String description, int levelBonus, boolean isCursed, CardType cardType, SubType subType, String effectFunctionName) {
            this.cardName = cardName;
            this.description = description;
            this.levelBonus = levelBonus;
            this.isCursed = isCursed;
            this.cardType = cardType;
            this.subType = subType;
            this.effectFunctionName = effectFunctionName;
        }

        /**
         * Gets the name of the card.
         *
         * @return The name of the card.
         */
        public String getCardName() {
            return cardName;
        }

        /**
         * Gets the description of the card.
         *
         * @return The description of the card.
         */
        public String getDescription() {
            return description;
        }

        /**
         * Gets the level bonus of the card.
         *
         * @return The level bonus of the card.
         */
        public int getLevelBonus() {
            return levelBonus;
        }

        /**
         * Checks if the card is cursed.
         *
         * @return True if the card is cursed, false otherwise.
         */
        public boolean isCursed() {
            return isCursed;
        }

        /**
         * Gets the type of the card.
         *
         * @return The type of the card.
         */
        public CardType getCardType() {
            return cardType;
        }

        /**
         * Gets the subtype of the card.
         *
         * @return The subtype of the card.
         */
        public SubType getSubType() {
            return subType;
        }

        /**
         * Gets the name of the effect function for the card.
         *
         * @return The name of the effect function for the card.
         */
        public String getEffectFunctionName() {
            return effectFunctionName;
        }

        /**
         * Gets a string representation of the CardInfo object.
         *
         * @return A string representation of the CardInfo object.
         */
        public String getCardInfo() {
            return "CardInfo{" +
                    "cardName='" + cardName + '\'' +
                    ", description='" + description + '\'' +
                    ", levelBonus=" + levelBonus +
                    ", isCursed=" + isCursed +
                    ", cardType=" + cardType +
                    ", subType=" + subType +
                    ", effectFunctionName='" + effectFunctionName + '\'' +
                    '}';
        }
    }

    /**
     * Resets the available cards by copying all cards from cardInfoMap.
     */
    private static void resetAvailableCards() {
        // Initialize the available cards map with all cards
        availableCards = new HashMap<>(cardInfoMap);
    }
}
