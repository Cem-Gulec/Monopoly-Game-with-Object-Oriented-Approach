package com.oosd.monopolygame.action;

import com.oosd.monopolygame.Player;
/**
 *  This class is used when Player on Chance or CommunityChest BoardPart
 *  Its execute() method is invoked by playerAction class' instance.
 *
 */
public class PickUpCard implements Command {

    private Player player;

    public PickUpCard(Player newPlayer) {
        this.player = newPlayer;
    }
    @Override
    public void execute() {
        player.pickUpCard();
    }
}
