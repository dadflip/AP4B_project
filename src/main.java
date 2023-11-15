public class main {
    public static void main(String[] args) {
        // TODO code application logic here
        Dice dice = new Dice();
        Rules rules = new Rules();
        ListOfPlayer list = new ListOfPlayer();
        list.addPlayer(new Player("Player 1", 1));
        list.addPlayer(new Player("Player 2", 2));
        list.addPlayer(new Player("Player 3", 3));
        list.addPlayer(new Player("Player 4", 4));
        rules.setTurn(list);

    }
}
