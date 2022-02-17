package com.oosd.monopolygame.action;
import com.oosd.monopolygame.Player;

import java.util.Random;

public class RollDice implements Command {
    private Player player;
    private int diceValue;
    public RollDice(Player newPlayer) {
        this.player = newPlayer;
    }
    @Override
    public void execute() {
        // rolling dice. it will completely change.
        roll();
        System.out.println(player.getColor() + " rolls " + this.diceValue);
        player.setDiceValue(diceValue);
    }

    public void roll() {
        Random rand = new Random();
        int n = rand.nextInt(12) + 1;
        setDiceValue(n);
    }
    public int getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }

}
