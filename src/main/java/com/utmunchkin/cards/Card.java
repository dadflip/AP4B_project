package main.java.com.utmunchkin.cards;

/**
 * Card
 */
public abstract class Card implements CardsActions {

    protected String cardName;

    public Card(String cardName) {
        this.cardName = cardName;
    }

    // Méthodes communes à toutes les cartes
    // ...

}
