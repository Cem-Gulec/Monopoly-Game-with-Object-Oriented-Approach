package com.oosd.monopolygame.card;

import com.oosd.monopolygame.Player;

/**
 * Parent of the different Card types.
 */
public interface Card {
    public void doOperation(Player player);
}
