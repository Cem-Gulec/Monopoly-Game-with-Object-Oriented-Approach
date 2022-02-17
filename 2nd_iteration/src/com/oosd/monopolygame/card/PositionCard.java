package com.oosd.monopolygame.card;

import com.oosd.monopolygame.Player;
import org.json.simple.JSONObject;

/**
 *  It is stored in either chanceDeck or communityChestDeck.
 *  Player might randomly pick this card and invoke doOperationg method.
 *  Its one of the Chance or Community Chest cards that change location of the
 *  Player on board.
 */
public class PositionCard implements Card{
    private String type;
    private String action;
    private String stepCount;
    public PositionCard(JSONObject jsonObject){
        this.action = (String)jsonObject.get("action");

    }
    @Override
    public void doOperation(Player player) {
        System.out.println("NO EFFECT --> " + action);
        // print Action here
        //
    }
}
