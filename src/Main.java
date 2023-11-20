import main.java.com.utmunchkin.Rules;
import main.java.com.utmunchkin.players.ListOfPlayer;
import main.java.com.utmunchkin.players.Player;
import main.java.com.utmunchkin.utils.DiceRoller;

public class Main {
    public static void main(String[] args) {
        // TODO code application logic here
        DiceRoller dice = new DiceRoller();
        Rules rules = new Rules();
        ListOfPlayer list = new ListOfPlayer();
        list.addPlayer(new Player("Player 1", 1));
        list.addPlayer(new Player("Player 2", 2));
        list.addPlayer(new Player("Player 3", 3));
        list.addPlayer(new Player("Player 4", 4));
        rules.setTurn(list);
    }
}
