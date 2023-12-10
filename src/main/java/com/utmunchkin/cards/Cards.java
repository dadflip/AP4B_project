package main.java.com.utmunchkin.cards;

import java.util.ArrayList;
import java.util.Collections;

import main.java.com.utmunchkin.players.Player;

/**
 * Cards
 */
public class Cards extends Card {

    private ArrayList<Card> cards;

    public Cards() {
         //create an empty arraylist
         this.cards = new ArrayList<>();
         //load all cards
         this.loadCards();
    }

    // Autres méthodes sur cartes
    //...

    public void shuffleCards(ArrayList<Card> deckToShuffle) { 
        // Mélangez l'ArrayList
        Collections.shuffle(deckToShuffle);
    }
    public void loadCards(){
        // Charger les cartes dans le paquet
        for (int i = 0; i < 168; i++) {
            Card card = new Card();
            this.cards.add(card);
        }
    }
    public void printCards(){
        // Afficher les cartes
        int i = 0;
        for (Card c : this.cards) {
            System.out.println(c.cardInfo.getCardInfo()+"numero de carte : "+i+"\n");
            i++;
        }
    }

    public ArrayList<Card> constructDeck(int numberOfCards) {
        // Mélanger les cartes si nécessaire
        this.shuffleCards(this.cards);

        // Construire le deck à partir des cartes initiales
        ArrayList<Card> deck = new ArrayList<>();
        for (int i = 0; i < numberOfCards && i < this.cards.size(); i++) {
            deck.add(this.removefromCards(0)); // Ajouter la première carte du paquet et la retirer du paquet
        }
        return deck;
    }

    public ArrayList<Card> constructDeck(int numberOfCards, CardType tCardType) {
        // Shuffle the cards if necessary
        this.shuffleCards(this.cards);
    
        // Construct the deck based on the initial cards and specified card type
        ArrayList<Card> deck = new ArrayList<>();
        int i = 0;
        while (i < numberOfCards && i < this.cards.size()) {
            Card card = this.cards.get(0); // Get the first card from the deck without removing it
    
            // Check the card type before removing it from the deck
            if (card.cardInfo.getCardType() == tCardType) {
                this.removefromCards(0); // Remove the first card from the deck
                deck.add(card);
                i++;
            } else {
                // If the card type doesn't match, move it to the end of the deck
                this.cards.add(card);
                this.cards.remove(0);
            }
        }
        return deck;
    }    
    

    public Card removefromCards(int i) {
        if (i >= 0 && i < this.cards.size()) {
            return this.cards.remove(i);
        } else {
            // Gérer l'indice invalide
            System.out.println("Indice invalide : " + i);
            return null;
        }
    }

    private void distributeCardsFromDeckToPlayer(Player player, ArrayList<Card> deckPile, int quantity) {
        for (int i = 0; i < quantity && !deckPile.isEmpty(); i++) {
            Card card = deckPile.remove(0); // Retirer la première carte du deck
            player.addToHand(card); // Ajouter la carte à la main du joueur
        }
    }

    public void distributeDungeonTreasureCardToPlayer(Player player, int qty) {
    //instancier arraylist
    Dungeon dungeon = new Dungeon();
    Treasure treasure = new Treasure();

    //distribuer les cartes
    this.distributeCardsFromDeckToPlayer(player, dungeon.getDeckPile(), qty);
    this.distributeCardsFromDeckToPlayer(player, treasure.getDeckPile(), qty);

    }
}
