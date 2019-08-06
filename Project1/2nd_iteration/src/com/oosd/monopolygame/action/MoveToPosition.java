package com.oosd.monopolygame.action;

import com.oosd.monopolygame.Player;
/**
 *  This class is used when Player moves to different BoardPart
 *  Its execute() method is invoked by playerAction class' instance.
 *
 */
public class MoveToPosition implements Command {
    private Player player;
    public MoveToPosition(Player newPlayer) {
        this.player = newPlayer;
    }
    @Override
    public void execute() {
        player.moveToPosition();
    }

}
