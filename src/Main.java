import main.java.com.utmunchkin.Rules;
import main.java.com.utmunchkin.gameplay.Play;
import main.java.com.utmunchkin.players.ListOfPlayer;
import main.java.com.utmunchkin.players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The main class that contains the main method to start the Munchkin game.
 */
public class Main {

    private static Rules rules;

    /**
     * The entry point of the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        MainMenu.play();
    }

    /**
     * The main menu class responsible for displaying the game menu.
     */
    public static class MainMenu {

        private static ListOfPlayer list;

        /**
         * Displays the main menu and handles button actions.
         */
        public static void play() {
            initializePlayers();
            startGame();

            
            // Create the main menu window
            /*
            JFrame frame = new JFrame("Main Menu");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);

            // Create a panel to organize components
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 1));

            // Play button
            JButton playButton = new JButton("Play");
            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    // Execute code when the "Play" button is clicked
                    startGame();
                }
            });

            // Options button
            JButton optionsButton = new JButton("Options");
            optionsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Execute code when the "Options" button is clicked
                    JOptionPane.showMessageDialog(frame, "Access options.");
                }
            });

            // Records button
            JButton recordsButton = new JButton("Records");
            recordsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Execute code when the "Records" button is clicked
                    JOptionPane.showMessageDialog(frame, "View records.");
                }
            });

            // Add buttons to the panel
            panel.add(playButton);
            panel.add(optionsButton);
            panel.add(recordsButton);

            // Add the panel to the window
            frame.add(panel);

            // Center the window
            frame.setLocationRelativeTo(null);

            // Make the window visible
            frame.setVisible(true);*/
            
        }

        /**
         * Initializes the list of players for the game.
         */
        private static void initializePlayers() {
            rules = new Rules();
            list = new ListOfPlayer();
            list.addPlayer(new Player("Player 1", 1));
            list.addPlayer(new Player("Player 2", 2));
            list.addPlayer(new Player("Player 3", 3));
            list.addPlayer(new Player("Player 4", 4));
            rules.setTurn(list);

            System.out.println(list.getPlayers());
        }

        /**
         * Starts the Munchkin game.
         */
        private static void startGame() {
            int firstPlayerIndex = rules.getFirstPlayerIndex();
            Play game = new Play(list, firstPlayerIndex);
            game.gameProcess();
        }
    }
}
