package main.java.com.utmunchkin.cards;
import main.java.com.utmunchkin.players.Player;

/**
 * BlueCard
 */
public class BlueCard extends Card {

    public BlueCard(String cardName) {
        super(cardName);
    }

    @Override
    public void onOpenDoor(Player player) {
        // Ne fait rien pour les cartes bleues lors de l'ouverture de la porte
    }

    @Override
    public void onUseDuringYourTurn(Player player) {
        System.out.println("Utilisation de la carte bleue " + cardName + " pendant votre tour.");
        // Logique spécifique aux cartes bleues pendant votre tour
    }

    @Override
    public void onUseDuringAnyTurn(Player player) {
        System.out.println("Utilisation de la carte bleue " + cardName + " pendant le tour d'un adversaire.");
        // Logique spécifique aux cartes bleues pendant le tour d'un adversaire
    }

    @Override
    public void onCurse(Player targetPlayer) {
        // Ne fait rien pour les cartes bleues lorsqu'elles sont utilisées comme malédictions
    }

    @Override
    public void onRaceOrClass(Player player) {
        // Ne fait rien pour les cartes bleues lorsqu'elles sont utilisées comme cartes Race ou Classe
    }
}
