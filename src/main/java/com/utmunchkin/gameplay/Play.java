package main.java.com.utmunchkin.gameplay;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import main.java.com.utmunchkin.Rules;
import main.java.com.utmunchkin.cards.Card;
import main.java.com.utmunchkin.cards.CardData.CardInfo;
import main.java.com.utmunchkin.cards.Dungeon.Monster;
import main.java.com.utmunchkin.cards.CardData;
import main.java.com.utmunchkin.cards.CardEffects;
import main.java.com.utmunchkin.cards.Cards;
import main.java.com.utmunchkin.cards.CardsActions;
import main.java.com.utmunchkin.cards.Dungeon;
import main.java.com.utmunchkin.cards.Treasure;
import main.java.com.utmunchkin.players.ListOfPlayer;
import main.java.com.utmunchkin.players.Player;
import main.java.com.utmunchkin.Interface.Board;

public class Play implements CardsActions {

    private int currentPlayerIndex;
    private int firstPlayer;
    private ListOfPlayer players;
    private boolean gameWon;
    private Cards cardsOfGame;
    private Dungeon dungeonCard;
    private Treasure treasureCard;
    private Rules rules;
    private Board board;

    // Default constructor without specifying the first player index
    public Play(ListOfPlayer players) {
        this.players = players;
        this.rules = new Rules(); // Initialize rules
        this.currentPlayerIndex = rules.getFirstPlayerIndex();
        this.gameWon = false;
    }

    // Constructor with a specified first player index
    public Play(ListOfPlayer players, int firstPlayerIndex) {
        this.players = players;
        this.rules = new Rules(); // Initialize rules
        this.currentPlayerIndex = firstPlayerIndex;
        this.gameWon = false;
        this.firstPlayer = firstPlayerIndex;
        this.cardsOfGame = new Cards(); // Initialize cardsOfGame
        this.cardsOfGame.printCards();
        this.dungeonCard = new Dungeon();
        this.treasureCard = new Treasure();
    }
    public void gameProcess() {
        initializeGame();
        while (!gameWon) {
            Player currentPlayer = players.getPlayer(currentPlayerIndex);
            openDoorPhase(currentPlayer);

            if (!currentPlayer.hasEncounteredMonster()) {
                board.updateInfo("Chercher Bagarre");
                lookForTroublePhase(currentPlayer);
                board.updateInfo("Piller la salle");
                lootTheRoomPhase(currentPlayer);
            }

            charityPhase(currentPlayer, players);
            endTurnPhase(currentPlayer);

            checkGameWon(currentPlayer);

            currentPlayerIndex = (currentPlayerIndex + 1) % players.getSize();
        }
    }

    private void initializeGame() {
        for (int i = 0; i < players.getSize(); i++) {
            cardsOfGame.distributeDungeonTreasureCardToPlayer(players.getPlayer(i), 4);
            System.out.println("\n"+players.getPlayer(i).getInfo());
            //for each card in hand, print card name
            for (int j = 0; j < players.getPlayer(i).getHand().size(); j++) {
                System.out.println(players.getPlayer(i).getHand().get(j).getCardName());
            }
            System.out.println("\n");
        }
        board = new Board(this);
    }

    private void checkGameWon(Player currentPlayer) {
        if (currentPlayer.getLevel() >= 10) {
            gameWon = true;
            System.out.println(currentPlayer.getName() + " has won the game!");
        }
    }

    @Override
    public void onOpenDoor(Player player) {
        System.out.println(player.getName() + " is opening a door...");
        Card topDungeonCard = dungeonCard.removeFirstFromDeck();
        
        System.out.println("Top Dungeon Card: " + topDungeonCard);
        
        if (topDungeonCard != null) {
            handleDungeonCard(player, topDungeonCard);
        }
    }

    private void handleDungeonCard(Player player, Card dungeonCard) {
        String cardName = dungeonCard.getCardName();
        CardInfo cardInfo = Card.getCardInfo(cardName);
    
        if (cardInfo.getCardType() == CardData.CardType.MONSTER) {
            Dungeon.Monster monster = (Dungeon.Monster) dungeonCard;
            handleMonsterEncounter(player, monster);
        } else if (cardInfo.getCardType() == CardData.CardType.CURSE) {
            Dungeon.Curse curse = (Dungeon.Curse) dungeonCard;
            handleCurse(player, curse);
        } else {
            handleNonMonsterCard(player, dungeonCard, cardInfo);
        }
    }

    private void handleMonsterEncounter(Player player, Dungeon.Monster monster) {
        System.out.println("It's a monster! " + player.getName() + " must face it.");
        player.setHasEncounteredMonster(true);
        faceMonster(player, monster);
    }

    private void handleCurse(Player player, Dungeon.Curse curse) {
        System.out.println("It's a curse! " + player.getName() + " must suffer its effects.");
        sufferCurse(player, curse);
    }

    private void handleNonMonsterCard(Player player, Card dungeonCard, CardInfo cardInfo) {
        System.out.println("It's not a monster or curse. " + player.getName() + " adds it to their hand.");
        player.addToHand(dungeonCard);

        String effectFunctionName = cardInfo.getEffectFunctionName();
        if (effectFunctionName != null && !effectFunctionName.isEmpty()) {
            applySpecialEffect(player, effectFunctionName);
        }
    }

    private void applySpecialEffect(Player player, String effectFunctionName) {
        switch (effectFunctionName) {
            case "applySwordEffect":
                CardEffects.applySwordEffect(player);
                break;
            case "applyPotionEffect":
                CardEffects.applyPotionEffect(player);
                break;
            default:
                System.out.println("Unknown special effect: " + effectFunctionName);
                break;
        }
    }

    private void openDoorPhase(Player player) {
        onOpenDoor(player);
    }

    private void lootTheRoomPhase(Player player) {
        System.out.println("Vous n'avez rencontré aucun monstre en ouvrant la porte.");
        System.out.println("Vous pouvez piller la salle.");
        Dungeon drawnCard = new Dungeon();
        player.addToHand(drawnCard.removeFirstFromDeck());
        System.out.println(player.getName() + " a tiré une carte Donjon face cachée : " + drawnCard.getCardName());
    }

    private void lookForTroublePhase(Player player) {
        List<Card> playerHand = player.getHand();
        if (!playerHand.isEmpty()) {
            board.updateInfo("Vous n'avez croisé aucun monstre en ouvrant la porte.");
            board.updateInfo("Vous pouvez affronter un monstre de votre main :");

            displayPlayerHand(playerHand);
            
            Dungeon.Monster selectedMonster = (Monster) selectMonsterFromHand(playerHand);

            faceMonster(player, selectedMonster);
            
            board.updateInfo(player.getName() + " affronte le monstre : " + selectedMonster.getCardName());
        } else {
            board.updateInfo("Vous n'avez aucun monstre dans votre main.");
        }
    }

    private void displayPlayerHand(List<Card> playerHand) {
        for (int i = 0; i < playerHand.size(); i++) {
            System.out.println((i + 1) + ". " + playerHand.get(i).getCardName());
        }
    }

    private Card selectMonsterFromHand(List<Card> playerHand) {
        if (playerHand.isEmpty()) {
            board.updateInfo("Vous n'avez aucun monstre dans votre main.");
            return null;
        }

        board.updateInfo("Choisissez un monstre de votre main :");
        board.showInstructionDialog("Entrez le numéro du monstre que vous souhaitez affronter : ");
        int choice = board.getChoice();
        return playerHand.get(choice);
    }

    private void endTurnPhase(Player player) {
        player.setHasEncounteredMonster(false);
    }

    private void faceMonster(Player player, Dungeon.Monster monster) {
        int playerCombatStrength = player.getLevel();

        System.out.println("Combat details:");
        System.out.println("Player Combat Strength: " + playerCombatStrength);
        System.out.println("Monster Combat Strength: " + monster.getMonsterCombatStrength());

        if (playerCombatStrength >= monster.getMonsterCombatStrength()) {
            System.out.println("You defeat the monster!");

            int levelsGained = Math.min(2, monster.getLevels());
            player.gainLevel(levelsGained);
            int treasuresGained = monster.getTreasures();
            gainTreasures(treasuresGained, player);
            System.out.println("Gained " + levelsGained + " level(s) and " + treasuresGained + " treasure(s).");
        } else {
            System.out.println("You must flee from the monster!");
            player.loseLevel(1);
        }
    }

    private void gainTreasures(int treasuresGained, Player player) {
        System.out.println("Drawing " + treasuresGained + " treasure(s).");

        List<Card> drawnTreasures = drawTreasureCards(treasuresGained);
        player.addToHand(drawnTreasures);
    }

    private List<Card> drawTreasureCards(int count) {
        List<Card> treasureCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            treasureCards.add(new Treasure());
        }
        return treasureCards;
    }

    private void sufferCurse(Player player, Dungeon.Curse curse) {
        // TODO: Implement logic for suffering the curse
    }

    private void charityPhase(Player currentPlayer, ListOfPlayer allPlayers) {
        if (currentPlayer.getHand().size() > 5) {
            System.out.println("Munchkin : La charité");

            while (currentPlayer.getHand().size() > 5) {
                playExcessCard(currentPlayer);
            }

            Player lowestLevelPlayer = findPlayerWithLowestLevel(allPlayers);

            if (lowestLevelPlayer != null) {
                giveExcessCards(currentPlayer, lowestLevelPlayer);
            }
        }
    }

    private void playExcessCard(Player player) {
        List<Card> playerHand = player.getHand();
        if (!playerHand.isEmpty()) {
            Card cardToPlay = playerHand.get(0);
            playCard(cardToPlay, player);
            System.out.println(player.getName() + " a joué la carte : " + cardToPlay.getCardName());
        }
    }

    private void playCard(Card cardToPlay, Player curPlayer) {
        String effectFunctionName = cardToPlay.getInfo().getEffectFunctionName();
    
        if (effectFunctionName != null && !effectFunctionName.isEmpty()) {
            applySpecialEffect(curPlayer, effectFunctionName);
        } else {
            System.out.println("No special effect for card: " + cardToPlay.getCardName());
        }
    }
    
    private Player findPlayerWithLowestLevel(ListOfPlayer allPlayers) {
        List<Player> playersList = allPlayers.getPlayers();

        return playersList.stream()
                .min(Comparator.comparing(Player::getLevel))
                .orElse(null);
    }

    private void giveExcessCards(Player donor, Player recipient) {
        List<Card> excessCards = donor.getHand().subList(5, donor.getHand().size());
        recipient.addToHand(excessCards);
        donor.removeFromHand(excessCards);

        System.out.println(donor.getName() + " a donné les cartes excédentaires à " + recipient.getName());
    }
    //getters and setters
    public int getFirstPlayerIndex() {
        return firstPlayer;
    }
    public void setFirstPlayerIndex(int firstPlayerIndex) {
        this.firstPlayer = firstPlayerIndex;
    }
    public ListOfPlayer getPlayers() {
        return players;
    }
    public void setPlayers(ListOfPlayer players) {
        this.players = players;
    }
    public boolean isGameWon() {
        return gameWon;
    }
    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }
    public Cards getCardsOfGame() {
        return cardsOfGame;
    }
    public void setCardsOfGame(Cards cardsOfGame) {
        this.cardsOfGame = cardsOfGame;
    }
    public Rules getRules() {
        return rules;
    }
    public void setRules(Rules rules) {
        this.rules = rules;
    }
    public Board getBoard() {
        return board;
    }
    public Dungeon getDungeonCard() {
        return dungeonCard;
    }
    public Treasure getTreasureCard() {
        return treasureCard;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
}
