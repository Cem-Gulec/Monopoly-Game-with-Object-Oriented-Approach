package com.oosd.monopolygame.action;

import com.oosd.monopolygame.Player;

public class PayRent implements Command {
    private Player player;
    private int diceValue;
    public PayRent(Player newPlayer) {
        this.player = newPlayer;
    }
    @Override
    public void execute() {
        System.out.println(player.getColor() + " paid rent ");
    }

}
