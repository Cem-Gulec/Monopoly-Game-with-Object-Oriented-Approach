package com.oosd.monopolygame.board;

import com.oosd.monopolygame.Player;
import org.json.simple.JSONObject;

public class Utility implements BoardPart {
    private String type;
    private String name;
    private int cost;
    private int mortgage;
    private Player owner;
    private int owned;
    private int rent;
    private int ind;

    public Utility(JSONObject jsonObject, int ind) {
        this.type = (String)jsonObject.get("type");
        this.name = (String)jsonObject.get("description");
        this.cost = Integer.parseInt(jsonObject.get("cost").toString());
        this.rent = 100;
        this.ind = ind;
        this.mortgage = Integer.parseInt(jsonObject.get("mortgage").toString());
    }
    @Override
    public void accept(BoardPartVisitor boardPartVisitor) {
        boardPartVisitor.visit(this);
    }

    @Override
    public int getInd() {
        return this.ind;
    }

    public String getName() {
        return this.name;
    }

    public int getOwned() {
        return owned;
    }

    public void setOwned(int owned) {
        this.owned = owned;
    }

    public Player getOwner() {
        return owner;
    }

    public int getCost() {
        return cost;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }



    public int getMortgage() {
        return mortgage;
    }
}
