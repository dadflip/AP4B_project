package main.java.com.utmunchkin.cards;
import main.java.com.utmunchkin.players.Player;

/**
 * RaceCard
 */
public class RaceClassCards extends Card {

    public RaceClassCards(String cardName) {
        super(cardName);
    }

    @Override
    public void onOpenDoor(Player player) {}

    @Override
    public void onUseDuringYourTurn(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onUseDuringYourTurn'");
    }

    @Override
    public void onUseDuringAnyTurn(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onUseDuringAnyTurn'");
    }

    @Override
    public void onCurse(Player targetPlayer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onCurse'");
    }

    @Override
    public void onRaceOrClass(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onRaceOrClass'");
    }

}