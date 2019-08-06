package com.oosd.monopolygame.action;

import com.oosd.monopolygame.Player;
/**
 *  This class is used when Player pays the rent of the BoardPart
 *  its currently on.
 *  Its execute() method is invoked by playerAction class' instance.
 *
 */
public class PayRent implements Command {
    private Player player;

    public PayRent(Player newPlayer) {
        this.player = newPlayer;
    }
    @Override
    public void execute() {
        player.payRent();

    }

}
