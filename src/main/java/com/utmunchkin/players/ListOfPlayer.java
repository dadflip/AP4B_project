package main.java.com.utmunchkin.players;

import java.util.ArrayList;
import java.util.List;

import main.java.com.utmunchkin.Constant;

public class ListOfPlayer extends Constant {
    private List<Player> players;

    public ListOfPlayer() {
        this.players = new ArrayList<>(INITIAL_CAPACITY);
    }

    public int getSize() {
        return players.size();
    }

    public Player getPlayer(int index) {
        validateIndex(index);
        return players.get(index);
    }
    public Player getPlayer(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    private void resetAspect(ActionResetter actionResetter) {
        for (Player player : players) {
            actionResetter.reset(player);
        }
    }

    public void reset() {
        resetAspect(Player::reset);
    }

    public void resetScore() {
        resetAspect(Player::resetScore);
    }

    public void resetTurn() {
        resetAspect(Player::resetTurn);
    }

    public void addScore(int index, int score) {
        validateIndex(index);
        players.get(index).addScore(score);
    }

    public void addTurn(int index) {
        validateIndex(index);
        players.get(index).addTurn();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Player player : players) {
            result.append(player.toString()).append("\n");
        }
        return result.toString();
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= players.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    private interface ActionResetter {
        void reset(Player player);
    }
}
