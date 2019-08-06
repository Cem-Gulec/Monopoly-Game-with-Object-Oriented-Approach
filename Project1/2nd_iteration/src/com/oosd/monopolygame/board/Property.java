package com.oosd.monopolygame.board;

import com.oosd.monopolygame.Player;
import org.json.simple.JSONObject;
/**
 * Property part of the Board.
 * accept method accepts BoardPartVisitor and invokes its visit method so that we know Visitor is landed on Visitable
 */
public class Property implements BoardPart {
    private String type;
    private String name;
    private String colorGroup;
    private int rent;
    private int rentFullColor;
    private int rent1;
    private int rent2;
    private int rent3;
    private int rent4;
    private int rentHotel;
    private int houseCost;
    private int hotelCost;
    private int cost;
    private int mortgage;
    private Player owner;
    private int owned;
    private int ind;
    public Property(JSONObject jsonObject, int ind) {
        this.type = (String) jsonObject.get("type");
        this.name = (String) jsonObject.get("description");
        this.colorGroup = jsonObject.get("colorGroup").toString();
        this.rent = Integer.parseInt(jsonObject.get("rent").toString());
        this.rentFullColor = Integer.parseInt(jsonObject.get("rentFullColor").toString());
        this.rent1 = Integer.parseInt(jsonObject.get("rent1").toString());
        this.rent2 = Integer.parseInt(jsonObject.get("rent2").toString());
        this.rent3 = Integer.parseInt(jsonObject.get("rent3").toString());
        this.rent4 = Integer.parseInt(jsonObject.get("rent4").toString());
        this.rentHotel = Integer.parseInt(jsonObject.get("rentHotel").toString());
        this.houseCost = Integer.parseInt(jsonObject.get("houseCost").toString());
        this.hotelCost = Integer.parseInt(jsonObject.get("hotelCost").toString());
        this.cost = Integer.parseInt(jsonObject.get("cost").toString());
        this.mortgage = Integer.parseInt(jsonObject.get("mortgage").toString());
        this.ind = ind;
    }

    /**
     * If Player has all properties of colorGroup its rent will be doubled.
     * This functions returns current rent information.
     */
    public int getActiveRent(int propertyColorGroupCount){
        if (propertyColorGroupCount == 3)
        {
            // System.out.println("FULL GROUP");
            return this.rentFullColor;
        }
        else return this.rent;
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

    public String getColorGroup() {
        return colorGroup;
    }

    public String getType() {
        return type;
    }


    public int getCost() {
        return cost;
    }

    public int getMortgage() {
        return mortgage;
    }



    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getOwned() {
        return owned;
    }

    public void setOwned(int owned) {
        this.owned = owned;
    }
}
