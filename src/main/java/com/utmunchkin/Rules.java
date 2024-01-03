package main.java.com.utmunchkin;

import main.java.com.utmunchkin.cards.Card;
import main.java.com.utmunchkin.players.ListOfPlayer;
import main.java.com.utmunchkin.players.Player;
import main.java.com.utmunchkin.utils.DiceRoller;

/**
 * The class that defines the rules for starting the game and handling turns.
 */
public class Rules {
    private int firstPlayerIndex;

    /**
     * Sets the turn order for players based on dice rolls.
     *
     * @param list The list of players.
     */
    public void setTurn(ListOfPlayer list) {
        DiceRoller dice = new DiceRoller();

        do {
            dice.roll();
        } while (dice.getValue() > list.getSize());

        System.out.println("Dice value is " + dice.getValue());
        int startingIndex = dice.getValue() - 1;
        setFirstPlayerIndex(startingIndex);

        for (int i = 0; i < list.getSize(); i++) {
            int currentIndex = (startingIndex + i) % list.getSize();
            Player currentPlayer = list.getPlayer(currentIndex);

            currentPlayer.setTurn(i + 1);
            System.out.println("Player " + currentPlayer.getName() + " will play in turn " + currentPlayer.getTurn());

            checkHandSizeAtEndOfTurn(currentPlayer);
        }
    }

    /**
     * Checks and adjusts the hand size at the end of each player's turn.
     *
     * @param player The player whose hand size needs to be checked.
     */
    private void checkHandSizeAtEndOfTurn(Player player) {
        int maxHandSize = Constant.MAX_HAND_SIZE; // Maximum limit of cards in hand

        while (player.getHand().size() > maxHandSize) {
            System.out.println("Player " + player.getName() + ", your hand size is greater than " + maxHandSize +
                    ". Discard a card.");

            // Implement logic for allowing the player to choose which card to discard.
            // For example, ask them to choose an index or implement specific logic
            // for automatic discarding.

            // For this example, assume the discarded card is the first card in the hand.
            Card discardedCard = player.getHand().remove(0);
            System.out.println("Discarded card: " + discardedCard);
        }
    }

    /**
     * Gets the index of the first player.
     *
     * @return The index of the first player.
     */
    public int getFirstPlayerIndex() {
        return firstPlayerIndex;
    }

    /**
     * Sets the index of the first player.
     *
     * @param firstPlayerIndex The index of the first player.
     */
    public void setFirstPlayerIndex(int firstPlayerIndex) {
        this.firstPlayerIndex = firstPlayerIndex;
    }
}
