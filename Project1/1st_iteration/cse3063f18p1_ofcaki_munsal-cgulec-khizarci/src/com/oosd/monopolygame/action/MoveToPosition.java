package com.oosd.monopolygame.action;

import com.oosd.monopolygame.Player;

public class MoveToPosition implements Command {
    private Player player;
    private int diceValue;
    public MoveToPosition(Player newPlayer) {
        this.player = newPlayer;
    }
    @Override
    public void execute() {
        System.out.println(player.getColor() + " moves by " + player.getDiceValue());
    }

}
