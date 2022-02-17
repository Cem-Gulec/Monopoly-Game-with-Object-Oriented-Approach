package com.oosd.monopolygame.card;

import com.oosd.monopolygame.Player;
import org.json.simple.JSONObject;
/**
 *  It is stored in either chanceDeck or communityChestDeck.
 *  Player might randomly pick this card and invoke doOperationg method.
 *  With this type of Card player either receives money or pays.
 */
public class PaymentCard implements Card{
    private int amount;
    private String type;
    private String action;
    public PaymentCard(JSONObject jsonObject){
        this.action = (String)jsonObject.get("action");

    }
    @Override
    public void doOperation(Player player) {
        System.out.println("NO EFFECT --> " + action);
        // player.setCash(player.getCash() - amount);
    }
}
