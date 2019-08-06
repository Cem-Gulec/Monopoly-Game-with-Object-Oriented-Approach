package com.oosd.monopolygame.board;

import com.oosd.monopolygame.Player;
import org.json.simple.JSONObject;

public class Railroad implements BoardPart {
    private String type;
    private String name;
    private int cost;
    private int mortgage;
    private Player owner;
    private int owned;
    private int rent1;
    private int rent2;
    private int rent3;
    private int rent4;
    private int ind;
    public Railroad(JSONObject jsonObject, int ind) {
        this.type = (String) jsonObject.get("type");
        this.name = (String) jsonObject.get("description");
        this.cost = Integer.parseInt(jsonObject.get("cost").toString());
        this.rent1 = Integer.parseInt(jsonObject.get("rent1").toString());
        this.rent2 = Integer.parseInt(jsonObject.get("rent2").toString());
        this.rent3 = Integer.parseInt(jsonObject.get("rent3").toString());
        this.rent4 = Integer.parseInt(jsonObject.get("rent4").toString());
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

    public Player getOwner() {
        return owner;
    }

    public int getCost() {
        return cost;
    }

    public int getOwned() {
        return owned;
    }

    public void setOwned(int owned) {
        this.owned = owned;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Railroad's rent changes according to how many railroads Player has
     *
     */
    public int getActiveRent(int railRoadCount) {
        if (railRoadCount == 1) return this.rent1;
        if (railRoadCount == 2) return this.rent2;
        if (railRoadCount == 3) return this.rent3;
        if (railRoadCount == 4) return this.rent4;
        return 0;
    }


    public int getMortgage() {
        return mortgage;
    }
}
