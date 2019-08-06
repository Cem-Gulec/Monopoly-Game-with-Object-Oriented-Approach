package com.oosd.monopolygame.board;

import org.json.simple.JSONObject;

public class Tax implements BoardPart {
    private String type;
    private String name;
    private int cost;
    private int ind;
    public Tax(JSONObject jsonObject, int ind) {
        this.type = (String)jsonObject.get("type");
        this.name = (String)jsonObject.get("description");
        this.cost = Integer.parseInt(jsonObject.get("cost").toString());
        this.ind = ind;
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

    public int getCost() {
        return cost;
    }
}
