package main.java.com.utmunchkin.Interface;

import main.java.com.utmunchkin.cards.Card;
import main.java.com.utmunchkin.cards.Dungeon;
import main.java.com.utmunchkin.cards.Treasure;
import main.java.com.utmunchkin.gameplay.Play;
import main.java.com.utmunchkin.players.ListOfPlayer;
import main.java.com.utmunchkin.players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PlayerFrame extends JPanel {

    private String playerName;
    private List<JButton> cardButtons;

    public PlayerFrame(String playerName, ListOfPlayer listOfPlayer, int playerIndex) {
        this.playerName = playerName;
        this.cardButtons = new ArrayList<>();

        setLayout(new GridLayout(2, 4)); // 2 rows, 4 columns
        setBorder(BorderFactory.createTitledBorder(playerName));

        // Assuming listOfPlayer is an instance of ListOfPlayer
        List<Card> hand = listOfPlayer.getPlayer(playerName).getHand();

        setBackground(getUniqueColor(playerIndex));

        for (int i = 0; i < hand.size(); i++) {
            String cardName = hand.get(i).getCardName();
            JButton cardButton = new JButton(cardName);
            cardButton.setEnabled(false);
            cardButtons.add(cardButton);
            add(cardButton);

            cardButton.addActionListener(new CardButtonListener(cardName));
        }
    }
    private Color getUniqueColor(int playerIndex) {
        int red = playerIndex * 50 % 255;
        int green = playerIndex * 80 % 255;
        int blue = playerIndex * 120 % 255;
        return new Color(red, green, blue);
    }
    private class CardButtonListener implements ActionListener {
        private String cardName;

        public CardButtonListener(String cardName) {
            this.cardName = cardName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle the button click (you can implement specific behavior here)
        }
    }
}
