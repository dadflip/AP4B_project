package main.java.com.utmunchkin;

import main.java.com.utmunchkin.cards.Card;
import main.java.com.utmunchkin.players.ListOfPlayer;
import main.java.com.utmunchkin.players.Player;
import main.java.com.utmunchkin.utils.DiceRoller;

public class Rules {
    private int firstPlayerIndex;

    public void setTurn(ListOfPlayer list) {
        DiceRoller dice = new DiceRoller();

        do {
            dice.roll();
        } while (dice.getValue() > list.getSize());

        System.out.println("Dice value is " + dice.getValue());
        System.out.println("Player " + list.getPlayer(dice.getValue() - 1).getName() + " will start the game\n");
        int index = dice.getValue() - 1;
        setFirstPlayerIndex(index);

        for (int i = 0; i < list.getSize(); i++) {
            if (index == list.getSize()) {
                index = 0;
            }
            list.getPlayer(index).setTurn(i + 1);
            System.out.println("Player " + list.getPlayer(index).getName() + " will play in turn " + list.getPlayer(index).getTurn());

            index++;
        }

        // À la fin du tour de chaque joueur, vérifier et ajuster la taille de la main
        for (Player player : list.getPlayers()) {
            checkHandSizeAtEndOfTurn(player);
        }
    }

    private void checkHandSizeAtEndOfTurn(Player player) {
        int maxHandSize = 5; // Limite maximale de cartes en main

        while (player.getHand().size() > maxHandSize) {
            System.out.println("Player " + player.getName() + ", your hand size is greater than 5. Discard a card.");

            // Ajoutez ici votre logique pour permettre au joueur de choisir quelle carte défausser
            // Par exemple, demandez-lui de choisir un index ou implémentez une logique spécifique
            // pour la défausse automatique.

            // Pour cet exemple, supposons que la carte à défausser est la première de la main.
            Card discardedCard = player.getHand().remove(0);
            System.out.println("Discarded card: " + discardedCard);
        }
    }

    public int getFirstPlayerIndex() {
        return firstPlayerIndex;
    }

    public void setFirstPlayerIndex(int firstPlayerIndex) {
        this.firstPlayerIndex = firstPlayerIndex;
    }
}
