package main.java.com.utmunchkin;

import main.java.com.utmunchkin.players.ListOfPlayer;
import main.java.com.utmunchkin.utils.DiceRoller;

public class Rules {
    public void setTurn(ListOfPlayer list) {
        DiceRoller dice = new DiceRoller();
        do {
            dice.roll();
        }while (dice.getValue() >list.getSize());
        System.out.println("Dice value is " + dice.getValue());
        System.out.println("Player " + list.getPlayer(dice.getValue() - 1).getName() + " will start the game\n");
        int index = dice.getValue() - 1;
        for(int i = 0; i < list.getSize(); i++) {
            if (index == list.getSize()) {
                index = 0;
            }
            list.getPlayer(index).setTurn(i + 1);
            System.out.println("Player " + list.getPlayer(index).getName() + " will play in turn " + list.getPlayer(index).getTurn());
            index++;
        }

    }

}
