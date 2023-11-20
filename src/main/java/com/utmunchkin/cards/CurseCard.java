package main.java.com.utmunchkin.cards;
import main.java.com.utmunchkin.players.Player;

/**
 * CurseCard
 */
public class CurseCard extends Card {

    public CurseCard(String cardName) {
        super(cardName);
    }

    @Override
    public void onOpenDoor(Player player) {
        // Ne fait rien pour les cartes malédictions lors de l'ouverture de la porte
    }

    @Override
    public void onUseDuringYourTurn(Player player) {
        // Ne fait rien pour les cartes malédictions lors de l'utilisation pendant votre tour
    }

    @Override
    public void onUseDuringAnyTurn(Player player) {
        // Ne fait rien pour les cartes malédictions lors de l'utilisation pendant le tour d'un adversaire
    }

    @Override
    public void onCurse(Player targetPlayer) {
        System.out.println("Application de la malédiction " + cardName + " sur le joueur " + targetPlayer.getName() + ".");
        // Logique spécifique aux malédictions
    }

    @Override
    public void onRaceOrClass(Player player) {
        // Ne fait rien pour les cartes malédictions lorsqu'elles sont utilisées comme cartes Race ou Classe
    }
}
