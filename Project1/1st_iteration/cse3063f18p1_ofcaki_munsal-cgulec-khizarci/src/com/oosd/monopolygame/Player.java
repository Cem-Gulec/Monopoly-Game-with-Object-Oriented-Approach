package com.oosd.monopolygame;

import com.oosd.monopolygame.board.*;

public class Player implements BoardPartVisitor {
    // RECEIVER
    private String color;
    private int cash;
    private int position;
    private int diceValue;
    private int totalDiceValue;
    private int jailStatus;
    public Player(String color) {
        this.color = color;
        this.totalDiceValue = 0;
        // this.cash = cash;
    }
    @Override
    public void visit(Chance chanceCommunity) {

        this.setCash(this.cash + 50);
        System.out.println(this.color + " On Chance and takes 50 from bank. " + this.color + " has " + this.cash + "$");

    }

    @Override
    public void visit(FreeParkingLot freeParkingLot) {
        System.out.println(this.color + " On FreeParkingLot");
    }

    @Override
    public void visit(Go go) {
        System.out.println(this.color + " On Go");
    }

    @Override
    public void visit(GoToJail goToJail) {
        System.out.println(this.color + " On GoToJail");
    }

    @Override
    public void visit(Jail jail) {
        this.setCash(this.cash - 750);
        System.out.println(this.color + " On Jail and gives 750 to bank. " + this.color + " has " + this.cash + "$");
    }

    @Override
    public void visit(Property property) {
        System.out.println(this.color + " on " + property.getName());
    }

    @Override
    public void visit(CommunityChest communityChest) {
        System.out.println(this.color + " on " + communityChest.getName());
    }

    @Override
    public void visit(Tax tax) {
        System.out.println("On Tax");
    }

    @Override
    public void visit(Railroad railroad) {
        System.out.println("on railroad");
    }

    @Override
    public void visit(Utility Utility) {
        System.out.println("on utility");
    }

    public int doPlayerHaveJailFreeCard() {
        return 0;
    }
    void setCash(int cash) {
        this.cash = cash;
    }
    public int getCash(){
        return this.cash;
    }
    public String getColor() {
        return this.color;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getJailStatus() {
        return jailStatus;
    }

    public void setJailStatus(int jailStatus) {
        this.jailStatus = jailStatus;
    }

    public int getTotalDiceValue() {
        return totalDiceValue;
    }

    public void setTotalDiceValue(int totalDiceValue) {
        this.totalDiceValue = totalDiceValue;
    }
}
