package com.oosd.monopolygame.board;

import org.json.simple.JSONObject;

/**
 * Invoked from MonopolyGame to create required BoardParts to create Board
 */
public class BoardPartFactory {
    public BoardPart getBoardPart(JSONObject jsonObject, int ind) {
        if (((String)jsonObject.get("type")).equalsIgnoreCase("property")) return new Property(jsonObject, ind);
        if (((String)jsonObject.get("type")).equalsIgnoreCase("chance")) return new Chance(jsonObject, ind);
        if (((String)jsonObject.get("type")).equalsIgnoreCase("communityChest")) return new CommunityChest(jsonObject, ind);
        if (((String)jsonObject.get("type")).equalsIgnoreCase("freeParkingLot")) return new FreeParkingLot(jsonObject, ind);
        if (((String)jsonObject.get("type")).equalsIgnoreCase("go"))  return new Go(jsonObject, ind);
        if (((String)jsonObject.get("type")).equalsIgnoreCase("goToJail")) return new GoToJail(jsonObject, ind);
        if (((String)jsonObject.get("type")).equalsIgnoreCase("jail")) return new Jail(jsonObject, ind);
        if (((String)jsonObject.get("type")).equalsIgnoreCase("tax")) return new Tax(jsonObject, ind);
        if (((String)jsonObject.get("type")).equalsIgnoreCase("railroad")) return new Railroad(jsonObject, ind);
        if (((String)jsonObject.get("type")).equalsIgnoreCase("utility")) return new Utility(jsonObject, ind);
        return null;
    }
}
