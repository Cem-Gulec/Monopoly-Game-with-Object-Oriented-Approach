package com.oosd.monopolygame.card;

import com.oosd.monopolygame.Player;
import org.json.simple.JSONObject;

/**
 *  It is stored in either chanceDeck or communityChestDeck.
 *  Player might randomly pick this card and invoke doOperationg method.
 *
 */
public class JailCard implements Card{
    private String type;
    private String action;
    public JailCard(JSONObject jsonObject){
        this.action = (String)jsonObject.get("action");

    }

    /**
     * Invoked from within Player class to print "action" information.
     * @param player
     */
    @Override
    public void doOperation(Player player) {
        System.out.println("NO EFFECT --> " + action);
        /*
        if (this.type.equals("jailLeave")) player.setJailStatus(0);
        if (this.type.equals("jailEnter")) {
            int bpInd = player.getBp().getInd();
            if (bpInd > 10) player.setDiceValue(50 - bpInd);
            else {
                player.setDiceValue(10-bpInd);
            }
            player.addAction("moveToPosition");

        }
        */
    }
}
