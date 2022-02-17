package com.oosd.monopolygame.board;

import org.json.simple.JSONObject;
/**
 * Go part of the Board.
 * accept method accepts BoardPartVisitor and invokes its visit method so that we know Visitor is landed on Visitable
 */
public class Go implements BoardPart {
    private String type;
    private String name;
    private int ind;
    public Go(JSONObject jsonObject, int ind) {

        this.type = (String)jsonObject.get("type");
        this.name = (String)jsonObject.get("name");
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

}
