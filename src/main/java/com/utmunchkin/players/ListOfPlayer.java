package main.java.com.utmunchkin.players;
import java.util.Arrays;

public class ListOfPlayer {
    private Player[] list;
    private int size;

    // Utilisation d'une constante pour la taille initiale du tableau
    private static final int INITIAL_CAPACITY = 6;

    public ListOfPlayer() {
        // Initialisation avec la constante
        this.list = new Player[INITIAL_CAPACITY];
        this.size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public Player getPlayer(int index) {
        // Vérification des limites pour éviter les erreurs d'index
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return this.list[index];
    }

    public void addPlayer(Player player) {
        // Vérification si la taille actuelle est suffisante
        if (this.size == this.list.length) {
            // Si la taille n'est pas suffisante, doubler la capacité du tableau
            this.list = Arrays.copyOf(this.list, this.size * 2);
        }
        this.list[this.size] = player;
        this.size++;
    }

    // Méthode générique pour réinitialiser différents aspects du joueur
    private void resetAspect(ActionResetter actionResetter) {
        for (int i = 0; i < this.size; i++) {
            actionResetter.reset(this.list[i]);
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
        this.list[index].addScore(score);
    }

    public void addTurn(int index) {
        this.list[index].addTurn();
    }

    public String toString() {
        // Utilisation de StringBuilder pour des opérations de chaînes fréquentes
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.size; i++) {
            result.append(this.list[i].toString()).append("\n");
        }
        return result.toString();
    }

    // Interface fonctionnelle pour générer des actions de réinitialisation
    private interface ActionResetter {
        void reset(Player player);
    }
}
