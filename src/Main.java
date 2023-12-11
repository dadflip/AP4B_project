import main.java.com.utmunchkin.Rules;
import main.java.com.utmunchkin.gameplay.Play;
import main.java.com.utmunchkin.players.ListOfPlayer;
import main.java.com.utmunchkin.players.Player;
import main.java.com.utmunchkin.utils.DiceRoller;

public class Main {
    public static void main(String[] args) {
        // TODO code application logic here
        //DiceRoller dice = new DiceRoller(); /*unused for the moment, used in setTurn Method */

        Rules rules = new Rules();
        ListOfPlayer list = new ListOfPlayer();
        list.addPlayer(new Player("Joueur 1", 1));
        list.addPlayer(new Player("Joueur 2", 2));
        list.addPlayer(new Player("Joueur 3", 3));
        list.addPlayer(new Player("Joueur 4", 4));
        rules.setTurn(list);


        // Now you have the first player index from Rules
        int firstPlayerIndex = rules.getFirstPlayerIndex();

        // Create an instance of Play with the first player index
        Play game = new Play(list, firstPlayerIndex);
        game.gameProcess();

    }
}
