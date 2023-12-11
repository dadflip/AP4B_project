package main.java.com.utmunchkin.Interface;// DrawPileWindow.java
import main.java.com.utmunchkin.cards.Treasure;
import main.java.com.utmunchkin.cards.Dungeon;

import javax.swing.*;
import java.awt.*;

public class DrawPileWindow extends JFrame {

    private JLabel treasureLabel;
    private JLabel dungeonLabel;

    public DrawPileWindow(Treasure treasure, Dungeon dungeon) {
        setTitle("DrawPile Window");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 1)); // 2 rows, 1 column
        setSize(800, 600);

        treasureLabel = new JLabel("Treasure Draw Pile");
        dungeonLabel = new JLabel("Dungeon Draw Pile");

        add(treasureLabel);
        add(dungeonLabel);

        pack();
        setLocationRelativeTo(null); // Center the frame
    }

    public void updateDrawPiles(Treasure updatedTreasure, Dungeon updatedDungeon) {
        // Update the labels with information from the updated draw piles
        treasureLabel.setText("Treasure Draw Pile: " + updatedTreasure.getDeckPile());
        dungeonLabel.setText("Dungeon Draw Pile: " + updatedDungeon.getDeckPile());
    }
}
