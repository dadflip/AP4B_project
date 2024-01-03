package main.java.com.utmunchkin.Interface;

import javax.swing.*;

import main.java.com.utmunchkin.Constant;
import main.java.com.utmunchkin.cards.Dungeon;
import main.java.com.utmunchkin.cards.Treasure;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The MapPanel class represents the panel displaying the game map.
 */
public class MapPanel extends JPanel {

    /**
     * The size of the grid.
     */
    public static final int GRID_SIZE = Constant.INITIAL_CAPACITY - 1;

    /**
     * The size of each cell in pixels.
     */
    public static final int CELL_SIZE = 20;

    /**
     * The color for void cells.
     */
    public static final Color VOID_CELL_COLOR = Constant.MAP_GRID_VOID_CELL_COLOR;

    private Map<Character, ImageIcon> icons;
    private int playerX = 0;  // Initial position
    private int playerY = 2;  // Initial position

    /**
     * Constructs a MapPanel object.
     */
    public MapPanel() {
        initializeIcons();
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        initializeGrid();
        movePlayerTo(playerX, playerY);
    }

    /**
     * Initializes the icons for different game actions.
     */
    private void initializeIcons() {
        icons = new HashMap<>();
        icons.put(' ', getScaledImageIcon("", CELL_SIZE, CELL_SIZE));
        icons.put('p', getScaledImageIcon("src/main/java/com/utmunchkin/gameplay/img/game/P.png", CELL_SIZE, CELL_SIZE));
        icons.put('d', getScaledImageIcon("src/main/java/com/utmunchkin/gameplay/img/dungeon.png", CELL_SIZE, CELL_SIZE));
        icons.put('t', getScaledImageIcon("src/main/java/com/utmunchkin/gameplay/img/treasure.png", CELL_SIZE, CELL_SIZE));
        icons.put('h', getScaledImageIcon("src/main/java/com/utmunchkin/gameplay/img/monsterhand.png", CELL_SIZE, CELL_SIZE));
        icons.put('m', getScaledImageIcon("src/main/java/com/utmunchkin/gameplay/img/treasure.png", CELL_SIZE, CELL_SIZE));
        // Add more icons as needed
    }

    /**
     * Initializes the grid with default icons.
     */
    private void initializeGrid() {
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            JLabel label = createCellLabel(' ');
            add(label);
        }
    }

    /**
     * Scales the image icon to the specified width and height.
     *
     * @param imagePath The path to the image.
     * @param width     The desired width.
     * @param height    The desired height.
     * @return The scaled image icon.
     */
    private ImageIcon getScaledImageIcon(String imagePath, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image image = originalIcon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    /**
     * Creates a cell label with the specified icon.
     *
     * @param iconKey The key representing the icon.
     * @return The created JLabel.
     */
    private JLabel createCellLabel(char iconKey) {
        JLabel label = new JLabel(icons.get(iconKey));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBackground(VOID_CELL_COLOR);
        label.setOpaque(true);
        label.setBorder(BorderFactory.createLineBorder(Constant.BORDER_COLOR_1));
        return label;
    }

    public void performGameAction(char action, int targetX, int targetY) {


        SwingUtilities.invokeLater(() -> {
            // Perform the game action and update the UI
            switch (action) {
                case 'Z':
                    setIconAtPosition(' ', targetX, targetY);
                    break;
                case 'M':
                    movePlayerTo(targetX, targetY);
                    break;
                case 'A':
                    setIconAtPosition('p', targetX, targetY);
                    break;
                case 'O':
                    openDoor(targetX, targetY);
                    break;
                case 'L':
                    openTreasure(targetX, targetY);
                    break;
                case 'H':
                    setIconAtPosition('h', targetX, targetY);
                    break;
                // Add more cases for other game actions
            }
        });

    }

    /**
     * Moves the player to the specified position.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    private void movePlayerTo(int x, int y) {
        if (isValidMove(x, y)) {
            getLabelAt(playerX, playerY).setIcon(icons.get(' '));
            playerX = x;
            playerY = y;
            getLabelAt(playerX, playerY).setIcon(icons.get('p'));
            revalidate();
            repaint();
        } else {
            System.out.println("Invalid move!");
        }
    }

    /**
     * Opens a door at the specified position.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    private void openDoor(int x, int y) {
        setIconAtPosition('d', x, y);
    }

    /**
     * Opens a treasure at the specified position.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    private void openTreasure(int x, int y) {
        setIconAtPosition('t', x, y);
    }

    /**
     * Sets the icon at the specified position.
     *
     * @param c The key representing the icon.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    private void setIconAtPosition(char c, int x, int y) {
        getLabelAt(x, y).setIcon(icons.get(c));
        revalidate();
        repaint();
    }

    /**
     * Gets the label at the specified position.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The JLabel at the specified position.
     */
    private JLabel getLabelAt(int x, int y) {
        int index = x + y * GRID_SIZE;
        return (JLabel) getComponent(index);
    }

    /**
     * Checks if the move to the specified position is valid.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return True if the move is valid, false otherwise.
     */
    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE;
    }

    /**
     * Sets the color of a cell at the specified position.
     *
     * @param x     The x-coordinate.
     * @param y     The y-coordinate.
     * @param color The color to set.
     */
    public void setCellColor(int x, int y, Color color) {
        JLabel label = getLabelAt(x, y);
        if (label != null) {
            label.setBackground(color);
        }
    }

    /**
     * Resets the colors of all cells to the default void cell color.
     */
    public void resetCellColors() {
        for (Component component : getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                label.setBackground(VOID_CELL_COLOR);
            }
        }
        revalidate();
        repaint();
    }

    /**
     * Sets the color of all cells to the specified color.
     *
     * @param color The color to set.
     */
    public void setAllCellsColors(Color color) {
        for (Component component : getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                label.setBackground(color);
            }
        }
        revalidate();
        repaint();
    }

    // Methods for moving in the 8 directions

    /**
     * Moves the player up.
     */
    public void moveUp() {
        movePlayerTo(playerX, playerY - 1);
    }

    /**
     * Moves the player down.
     */
    public void moveDown() {
        movePlayerTo(playerX, playerY + 1);
    }

    /**
     * Moves the player left.
     */
    public void moveLeft() {
        movePlayerTo(playerX - 1, playerY);
    }

    /**
     * Moves the player right.
     */
    public void moveRight() {
        movePlayerTo(playerX + 1, playerY);
    }

    /**
     * Moves the player up-left.
     */
    public void moveUpLeft() {
        movePlayerTo(playerX - 1, playerY - 1);
    }

    /**
     * Moves the player up-right.
     */
    public void moveUpRight() {
        movePlayerTo(playerX + 1, playerY - 1);
    }

    /**
     * Moves the player down-left.
     */
    public void moveDownLeft() {
        movePlayerTo(playerX - 1, playerY + 1);
    }

    /**
     * Moves the player down-right.
     */
    public void moveDownRight() {
        movePlayerTo(playerX + 1, playerY + 1);
    }

    /**
     * Gets the X-coordinate of the player.
     *
     * @return The X-coordinate of the player.
     */
    public int getPX() {
        return this.playerX;
    }

    /**
     * Gets the Y-coordinate of the player.
     *
     * @return The Y-coordinate of the player.
     */
    public int getPY() {
        return this.playerY;
    }
}


