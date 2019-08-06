package com.oosd.monopolygame.action;

import com.oosd.monopolygame.Player;

public class BuyProperty implements Command {
    private Player player;
    private int diceValue;
    public BuyProperty(Player newPlayer) {
        this.player = newPlayer;
    }
    @Override
    public void execute() {
        System.out.println(player.getColor() + " bought property ");
    }

}
