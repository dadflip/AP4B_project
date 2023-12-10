package main.java.com.utmunchkin.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import main.java.com.utmunchkin.players.ListOfPlayer;

public class Board extends JFrame {

    private List<PlayerFrame> playerFrames;
    private ListOfPlayer players;

    public Board(ListOfPlayer a) {
        setTitle("Munchkin Game Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 4)); // 2 rows, 4 columns
        players = a;
        playerFrames = new ArrayList<>();

        for (int i = 0; i < players.getSize(); i++) {
            PlayerFrame playerFrame = new PlayerFrame(players.getPlayer(i).getName(), players, i);
            playerFrames.add(playerFrame);
        }

        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    // Additional constructor for the default case
    public Board() {
        setTitle("Munchkin Game Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 4)); // 2 rows, 4 columns
        players = new ListOfPlayer();
        playerFrames = new ArrayList<>();

        for (int i = 0; i < players.getSize(); i++) {
            PlayerFrame playerFrame = new PlayerFrame(players.getPlayer(i).getName(), players, i);
            playerFrames.add(playerFrame);
        }

        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    public class PlayerFrame extends JFrame {

        private String playerName;
        private List<JButton> cardButtons;

        public PlayerFrame(String playerName, ListOfPlayer listOfPlayer, int playerIndex) {
            this.playerName = playerName;
            this.cardButtons = new ArrayList<>();

            setTitle("Player " + (playerIndex + 1) + "'s Hand");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close only the current frame

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2, 4)); // 2 rows, 4 columns
            panel.setBorder(BorderFactory.createTitledBorder(playerName));  // TitledBorder for the panel

            // Assign a unique background color based on the player's index
            panel.setBackground(getUniqueColor(playerIndex));

            for (int i = 0; i < listOfPlayer.getPlayer(playerName).getHand().size(); i++) {
                String cardName = listOfPlayer.getPlayer(playerName).getHand().get(i).getCardName();
                JButton cardButton = new JButton(cardName);
                cardButton.setEnabled(false);  // Initially set the button to be non-clickable
                cardButtons.add(cardButton);
                panel.add(cardButton);

                // Add an ActionListener to handle button clicks
                cardButton.addActionListener(new CardButtonListener(cardName));
            }

            add(panel);
            pack();
            setLocationRelativeTo(null); // Center the frame
            setVisible(true);  // Set the frame to be visible
        }

        // ActionListener to handle button clicks
        private class CardButtonListener implements ActionListener {
            private String cardName;

            public CardButtonListener(String cardName) {
                this.cardName = cardName;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the button click (you can implement specific behavior here)
                System.out.println("Button clicked: " + cardName);
            }
        }

        // Method to generate a unique color based on the player's index
        private Color getUniqueColor(int playerIndex) {
            int red = playerIndex * 50 % 255;
            int green = playerIndex * 80 % 255;
            int blue = playerIndex * 120 % 255;
            return new Color(red, green, blue);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ListOfPlayer players = new ListOfPlayer();  // Replace with your actual player data
            new Board(players);
        });
    }
}
