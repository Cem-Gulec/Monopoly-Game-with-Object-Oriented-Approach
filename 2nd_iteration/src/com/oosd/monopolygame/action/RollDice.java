package com.oosd.monopolygame.action;

import com.oosd.monopolygame.Player;
/**
 *  This class is used every round at least once to invoke rollDice method inside Player class.
 *  Its execute() method is invoked by playerAction class' instance.
 *
 */
public class RollDice implements Command {
    private Player player;
    public RollDice(Player newPlayer) {
        this.player = newPlayer;
    }    @Override
    public void execute() {
        player.rollDice();
    }
}
