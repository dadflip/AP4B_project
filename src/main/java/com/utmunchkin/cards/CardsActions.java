package main.java.com.utmunchkin.cards;
import main.java.com.utmunchkin.players.Player;

/**
 * CardsActions
 */
public interface CardsActions {

    void onOpenDoor(Player player);

    void onUseDuringYourTurn(Player player);

    void onUseDuringAnyTurn(Player player);

    void onCurse(Player targetPlayer);

    void onRaceOrClass(Player player);
}
