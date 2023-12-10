package main.java.com.utmunchkin.cards;

import main.java.com.utmunchkin.players.Player;

import java.io.File;
import java.lang.reflect.Method;

public class Card extends CardData {

    protected final CardData.CardInfo cardInfo;

    public Card(String cardName) {
        // Load card data using a relative path
        String relativePath = "src/main/java/com/utmunchkin/cards/cards_data.csv";
        CardData.loadCardDataFromFile(new File(relativePath));
        // Get card info from CardData
        this.cardInfo = CardData.getCardInfo(cardName);
    }
    //default constructor
    public Card() {
        this.cardInfo = CardData.getRandomCardInfo();
    }

    public void applyCardEffect(Player player) {
        // Implement specific behavior for each card type
        // Use cardInfo to access attributes like levelBonus, isCursed, etc.
        System.out.println("Applying card effect: " + cardInfo.getCardName());

        // Get the function name from the card info
        String effectFunctionName = cardInfo.getEffectFunctionName();

        // Call the corresponding function using reflection
        try {
            Class<?> effectClass = Class.forName("main.java.com.utmunchkin.cards.CardEffects");
            Method effectMethod = effectClass.getMethod(effectFunctionName, Player.class);
            effectMethod.invoke(null, player);  // Assuming the methods are static
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCardName() {
        return cardInfo.getCardName();
    }

    public CardData.CardInfo getInfo() {
        return cardInfo;
    }
    public String toString() {
        return cardInfo.getCardInfo();
    }
}
