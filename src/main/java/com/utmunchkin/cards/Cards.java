package main.java.com.utmunchkin.cards;

import java.util.ArrayList;
import java.util.Collections;

import java.util.Iterator;

import main.java.com.utmunchkin.players.Player;

/**
 * The Cards class represents a collection of cards in the game.
 */
public class Cards extends Card {

    private ArrayList<Card> cards;
    ArrayList<Card> tempCardList = new ArrayList<>();


    /**
     * Constructs a new Cards object.
     */
    public Cards() {
        // Create an empty ArrayList
        this.cards = new ArrayList<>();
        // Load all cards
        this.loadCards();
    }

    /**
     * Shuffles the given deck of cards.
     *
     * @param deckToShuffle The deck of cards to shuffle.
     */
    public void shuffleCards(ArrayList<Card> deckToShuffle) {
        // Shuffle the ArrayList
        Collections.shuffle(deckToShuffle);
    }

    /**
     * Loads cards into the deck.
     */
    public void loadCards() {
        // Load cards into the deck
        for (int i = 0; i < 168; i++) {
            Card card = new Card();
            this.cards.add(card);
        }
    }

    /**
     * Prints information about all cards in the deck.
     */
    public void printCards() {
        // Print information about all cards in the deck
        int i = 0;
        for (Card c : this.cards) {
            System.out.println(c.cardInfo.getCardInfo() + "numero de carte : " + i + "\n");
            i++;
        }
    }

    /**
     * Constructs a deck of cards with a specified number of cards.
     *
     * @param numberOfCards The number of cards in the deck.
     * @return The constructed deck.
     */
    public ArrayList<Card> constructDeck(int numberOfCards) {
        // Shuffle the cards if necessary
        this.shuffleCards(this.cards);

        // Construct the deck from the initial cards
        ArrayList<Card> deck = new ArrayList<>();
        for (int i = 0; i < numberOfCards && i < this.cards.size(); i++) {
            deck.add(this.removefromCards(0)); // Add the first card from the deck and remove it from the deck
        }
        return deck;
    }

    /**
     * Constructs a deck of cards with a specified number of cards and a specific card type.
     *
     * @param numberOfCards The number of cards in the deck.
     * @param tCardType     The specified card type.
     * @return The constructed deck.
     */
    public ArrayList<Card> constructDeck(CardType tCardType) {
        // Shuffle the cards if necessary
        this.shuffleCards(this.cards);
    
        // Construct the deck based on the initial cards and specified card type
        ArrayList<Card> deck = new ArrayList<>();
        Iterator<Card> iterator = this.cards.iterator();
    
        while (iterator.hasNext()) {
            Card card = iterator.next();
        
            if (card.getInfo().getCardType() == tCardType) {
                iterator.remove(); // Remove the current card using the iterator's remove method
                deck.add(card);
            } else {
                // If the card type doesn't match, move it to a temporary list
                tempCardList.add(card);
                iterator.remove(); // Remove the current card using the iterator's remove method
            }
        }
        
        // Add the cards from the temporary list back to this.cards
        this.cards.addAll(tempCardList);
        
    
        return deck;
    }    

    /**
     * Removes a card from the deck at the specified index.
     *
     * @param i The index of the card to remove.
     * @return The removed card, or null if the index is invalid.
     */
    public Card removefromCards(int i) {
        if (i >= 0 && i < this.cards.size()) {
            return this.cards.remove(i);
        } else {
            // Handle invalid index
            System.out.println("Invalid index: " + i);
            return null;
        }
    }

    /**
     * Distributes cards from the deck pile to a player.
     *
     * @param player   The player to receive the cards.
     * @param deckPile The deck pile to distribute cards from.
     * @param quantity The number of cards to distribute.
     */
    private void distributeCardsFromDeckToPlayer(Player player, ArrayList<Card> deckPile, int quantity) {
        for (int i = 0; i < quantity && !deckPile.isEmpty(); i++) {
            Card card = deckPile.remove(0); // Remove the first card from the deck
            player.addToHand(card); // Add the card to the player's hand
        }
    }

    /**
     * Distributes dungeon and treasure cards to a player.
     *
     * @param player The player to receive the cards.
     * @param qty    The number of cards to distribute.
     */
    public void distributeDungeonTreasureCardToPlayer(Player player, int qty) {
        // Distribute cards
        this.distributeCardsFromDeckToPlayer(player, Dungeon.getDeckPile(), qty);
        this.distributeCardsFromDeckToPlayer(player, Treasure.getDeckPile(), qty);
    }
}
