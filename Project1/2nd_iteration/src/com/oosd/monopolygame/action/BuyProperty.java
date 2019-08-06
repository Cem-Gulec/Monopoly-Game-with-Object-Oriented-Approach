package com.oosd.monopolygame.action;

import com.oosd.monopolygame.Player;

/**
 *  This class is used when Player decides to buy BoardPart it is located.
 *  Its execute() method is invoked by playerAction class' instance.
 *
 */
public class BuyProperty implements Command {
    private Player player;

    public BuyProperty(Player newPlayer) {
        this.player = newPlayer;
    }
    @Override
    public void execute() {
        player.buyProperty();
    }

}
