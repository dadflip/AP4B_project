package main.java.com.utmunchkin.players;

import java.util.ArrayList;
import java.util.List;

import main.java.com.utmunchkin.cards.Card;
import main.java.com.utmunchkin.cards.Cards;

public class Player extends ListOfPlayer {
    private String name;
    private int score;
    private int turn;
    private int number;
    private List<Card> hand; // Use List interface instead of ArrayList

    private String race;
    private String playerClass;
    private int level;
    private String gender;

    private boolean hasEncounteredMonster;

    // Constructors

    public Player(String name, int number) {
        this.name = name;
        this.score = 0;
        this.turn = 0;
        this.number = number;
        this.hand = new ArrayList<>(); // Initialize hand

        // Set default values for race, class, level, and gender
        this.race = "Human";
        this.playerClass = "No Class";
        this.level = 1;
        this.gender = "Player Defined";

        this.hasEncounteredMonster = false;
    }

    public Player(String name, int number, Cards cardDeck, int numberOfInitialCardsForEachDeck) {
        this(name, number); // Call the default constructor to initialize basic fields
        this.hand = new ArrayList<>(); // Initialize hand

        // Distribute les cartes au joueur lors de la construction
        cardDeck.distributeDungeonTreasureCardToPlayer(this, numberOfInitialCardsForEachDeck);
    }

    public Player(String name, int number, Cards cardDeck, int numberOfInitialCardsForEachDeck,
                  String race, String playerClass, int level, String gender) {
        this(name, number); // Call the default constructor to initialize basic fields
        this.hand = new ArrayList<>(); // Initialize hand

        // Set specified values for race, class, level, and gender
        this.race = race;
        this.playerClass = playerClass;
        this.level = level;
        this.gender = gender;

        this.hasEncounteredMonster = false;

        // Distribute cards to the player during construction
        cardDeck.distributeDungeonTreasureCardToPlayer(this, numberOfInitialCardsForEachDeck);
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public int getTurn() {
        return this.turn;
    }

    public int getNumber() {
        return this.number;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void addTurn() {
        this.turn++;
    }

    public void resetTurn() {
        this.turn = 0;
    }

    public void resetScore() {
        this.score = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void reset() {
        this.resetTurn();
        this.resetScore();
    }


    public void gainLevel(int levelsGained) {
        this.level += levelsGained;
    }

    public void loseLevel(int levelsLoosed) {
        this.level -= levelsLoosed;
    }

    public void addToHand(Card card) {
        this.hand.add(card);
    }

    public void removeFromHand(Card cards) {
        this.hand.remove(cards);
    }

    public void addToHand(List<Card> cards) {
        this.hand.addAll(cards);
    }

    public void removeFromHand(List<Card> cards) {
        this.hand.removeAll(cards);
    }

    public List<Card> getHand() {
        return this.hand;
    }

    public String getRace() {
        return this.race;
    }

    public String getPlayerClass() {
        return this.playerClass;
    }

    public int getLevel() {
        return this.level;
    }
    public String getInfo() {
        return "Player " + this.number + " Name : " + this.name + " Score - " + this.score + " Turn - " + this.turn;
    }

    public String getGender() {
        return this.gender;
    }


    public void setRace(String race) {
        this.race = race;
    }

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }

    public void setLevel(int level) {
        // Ensure that the level is not set to a negative value
        if (level >= 0) {
            this.level = level;
        } else {
            System.out.println("Level cannot be set to a negative value.");
        }
    }


    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean hasEncounteredMonster() {
        return this.hasEncounteredMonster;
    }

    public void setHasEncounteredMonster(boolean b) {
        this.hasEncounteredMonster = b;
    }
}
