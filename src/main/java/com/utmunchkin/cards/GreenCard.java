package main.java.com.utmunchkin.cards;
import main.java.com.utmunchkin.players.Player;

/**
 * GreenCard
 */
public class GreenCard extends Card {

    public GreenCard(String cardName) {
        super(cardName);
    }

    @Override
    public void onOpenDoor(Player player) {
        System.out.println("Utilisation de la carte verte " + cardName + " lors de l'ouverture de la porte.");
        player.gainLevel();
    }

    @Override
    public void onUseDuringYourTurn(Player player) {
        System.out.println("Utilisation de la carte verte " + cardName + " pendant votre tour.");
        player.gainLevel();
    }

    @Override
    public void onUseDuringAnyTurn(Player player) {
        // Ne fait rien pour les cartes vertes pendant le tour d'un adversaire
    }

    @Override
    public void onCurse(Player targetPlayer) {
        // Ne fait rien pour les cartes vertes lorsqu'elles sont utilisées comme malédictions
    }

    @Override
    public void onRaceOrClass(Player player) {
        // Ne fait rien pour les cartes vertes lorsqu'elles sont utilisées comme cartes Race ou Classe
    }
}
