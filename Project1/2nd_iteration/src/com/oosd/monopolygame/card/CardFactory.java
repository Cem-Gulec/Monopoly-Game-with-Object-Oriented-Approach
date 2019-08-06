package com.oosd.monopolygame.card;

import org.json.simple.JSONObject;

/**
 * Responsible of creating objects with different types of Card.
 */
public class CardFactory {
    public Card getCard(JSONObject jsonObject) {
        if (((String)jsonObject.get("type")).equalsIgnoreCase("payment")) return new PaymentCard(jsonObject);
        if (((String)jsonObject.get("type")).equalsIgnoreCase("jail")) return new JailCard(jsonObject);
        if (((String)jsonObject.get("type")).equalsIgnoreCase("changePosition")) return new PositionCard(jsonObject);
        return null;
    }
}
