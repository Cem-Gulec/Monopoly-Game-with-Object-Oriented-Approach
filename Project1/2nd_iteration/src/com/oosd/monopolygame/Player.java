package com.oosd.monopolygame;

import com.oosd.monopolygame.board.*;
import com.oosd.monopolygame.card.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * This class does everything basically. It decides whats gonna get executed each round.
 * It basically plays the Game
 */
public class Player implements BoardPartVisitor {
    // RECEIVER

    private final int propertyProb;
    private final int railRoadProb;
    private final int utilityProb;
    private final int turnMoney;
    private int rollAgainFlag;
    private int landedOnType;
    private int deckType;
    public int doubleCounter;
    private int cash;
    private int position;
    private int diceValue;
    private int totalDiceValue;
    private int jailStatus;
    private int firstDie;
    private int secondDie;
    private String name;
    private String actionStatus;
    private Random rand;
    private List<Property> ownedProperties;
    private List<Railroad> ownedRailRoads;
    private List<Utility> ownedUtilities;
    private List<String> actions;
    private List<Card> chanceDeck;
    private List<Card> communityChestDeck;
    private Property landedOnProperty;
    private Railroad landedOnRailroad;
    private Utility landedOnUtility;

    private HashMap<String, Integer> colorGroupMap;
    private BoardPart bp;

    // player will take additional parameters
    public Player(String name, int propertyProb, int railRoadProb, int utilityProb, int turnMoney) {
        this.name = name;
        this.propertyProb = propertyProb;
        this.railRoadProb = railRoadProb;
        this.utilityProb = utilityProb;
        this.turnMoney = turnMoney;
        this.totalDiceValue = 0;
        this.ownedProperties = new ArrayList<>();
        this.ownedRailRoads = new ArrayList<>();
        this.ownedUtilities = new ArrayList<>();
        // this.cash = cash;
        this.actions = new ArrayList<>();
        this.jailStatus = 0;
        this.rand = new Random();
        initColorGroupMap();

    }

    /**
     *  Initializes ColorGroupHashMap. It is referenced when some other Player object lands on current Player objects
     *  property.
     */
    public void initColorGroupMap() {
        colorGroupMap = new HashMap<String, Integer>();
        colorGroupMap.put("brown", 0);
        colorGroupMap.put("lightBlue", 0);
        colorGroupMap.put("pink", 0);
        colorGroupMap.put("orange", 0);
        colorGroupMap.put("red", 0);
        colorGroupMap.put("yellow", 0);
        colorGroupMap.put("green", 0);
        colorGroupMap.put("darkBlue", 0);
    }
    /**
     * All visit methods below handles different situtation.
     * When player lands on Chance this method is invoked by accept method in that BoardPart
     **/
    @Override
    public void visit(Chance chanceCommunity) {
        bp = chanceCommunity;
        // this.setCash(this.cash + 50);
        System.out.println(this.name + " On Chance");
        deckType = 1;
        actions.add(0,"pickUpCard");


    }
    /**
     * All visit methods below handles different situtation.
     * When player lands on Chance this method is invoked by accept method in that BoardPart
     **/
    @Override
    public void visit(CommunityChest communityChest) {

        bp = communityChest;
        System.out.println(this.name + " on Community Chest");
        deckType = 2;
        actions.add(0,"pickUpCard");

    }
    /**
     *
     * When player lands on FreeParkingLot this method is invoked by accept method in that BoardPart.
     * This method just prints when player lands on FreeParkingLot
     **/
    @Override
    public void visit(FreeParkingLot freeParkingLot) {
        bp = freeParkingLot;
        System.out.println(this.name + " On FreeParkingLot");
    }
    /**
     *
     * When player lands on Go this method is invoked by accept method in that BoardPart.
     * This method just prints when player lands on Go
     **/
    @Override
    public void visit(Go go) {
        bp = go;
        System.out.println(this.name + " On Go");
        /*
        if (this.turnValue != 1) {
            this.cash += 200;
            System.out.println(this.name + " On Go" + " received $200");
        } else {
            System.out.println(this.name + " On Go");
        }
        */

    }
    /**
     *
     * When player lands on GoToJail this method is invoked by accept method in that BoardPart.
     * When this method is invoked player's jailStatus changes and immedietaly moves to jail.
     **/
    @Override
    public void visit(GoToJail goToJail) {
        bp = goToJail;
        jailStatus = 1;

        doubleCounter = 0;
        diceValue = 20;
        actions.add(0,"moveToPosition");

        System.out.println(this.name + " On GoToJail");

    }
    /**
     *
     * When player lands on Jail this method is invoked by accept method in that BoardPart
     **/
    @Override
    public void visit(Jail jail) {
        bp = jail;
        if (jailStatus == 1) {
            System.out.println(this.name + " in jail!");

        } else {
            System.out.println(this.name + " is just visiting jail.");
        }
        this.setCash(this.cash-0);

    }
    /**
     * When player lands on Property this method is invoked by accept method in that BoardPart.
     * This method checks if property is avaliable to purchase, if player has enough money. If it
     * does it uses probability provided by user to buy or not buy this property.
     * If Property is owned by someone other player. This player pays the rent.
     **/
    @Override
    public void visit(Property property) {
        bp = property;
        System.out.println(this.name + " on " + property.getName());
        landedOnType = 1;
        landedOnProperty = property;
        if (property.getOwned() == 0) {
            if (cash - property.getCost() > 0) {
                int test;
                test = rand.nextInt(6) + 1;
                if (rand.nextDouble() < (propertyProb/100)) {
                    actions.add(0,"buyProperty");
                } else {
                    System.out.println(this.name + "decided not to buy " + property.getName());
                }


            } else {
                System.out.println(this.name + "does not have enough cash to buy property" + property.getName());

            }

        } else {
            // according to status

            if (rollAgainFlag == 1) {
                actions.add(0, "payRent");
                rollAgainFlag = 0;
            } else {
                actions.add(0,"payRent");
            }
            // property.getOwner().setCash(property.getRent());
        }
        if (ownedProperties.contains(property)) {
            actions.clear();
        }
    }
    /**
     * All visit methods below handles different situtation.
     * When player lands on RailRoad this method is invoked by accept method in that BoardPart
     * This class is decides if player can/wants to buy the railroad or if railroad is owned player pays
     * the rent.
     **/
    @Override
    public void visit(Railroad railroad) {
        bp = railroad;
        landedOnType = 2;
        landedOnRailroad = railroad;
        System.out.println("on railroad");
        System.out.println(this.name + " on " + railroad.getName());
        if (railroad.getOwned() == 0) {
            if (cash - railroad.getCost() > 0) {

                if (rand.nextDouble() < (railRoadProb/100)) {
                    actions.add(0,"buyProperty");
                } else {
                    System.out.println(this.name + "decided not to buy " + railroad.getName());
                }
            } else {
                System.out.println(this.name + "does not have enough cash to buy railroad" + railroad.getName());

            }
        } else {
            // according to status
            if (rollAgainFlag == 1) {
                actions.add(0, "payRent");
                rollAgainFlag = 0;
            } else {
                actions.add(0,"payRent");
            }

            // property.getOwner().setCash(property.getRent());
        }
        // Player does not pay rent to itself
        if (ownedRailRoads.contains(railroad)) {
            actions.clear();
        }
    }
    /**
     * When player lands on Utility this method is invoked by accept method in that BoardPart
     **/
    @Override
    public void visit(Utility utility) {
        bp = utility;
        landedOnUtility = utility;
        landedOnType = 3;

        System.out.println(this.name + " on " + utility.getName());
        if (utility.getOwned() == 0) {
            if (cash - utility.getCost() > 0) {

                if (rand.nextDouble() < (utilityProb/100)) {
                    actions.add(0,"buyProperty");
                } else {
                    System.out.println(this.name + "decided not to buy " + utility.getName());
                }
            } else {
                System.out.println(this.name + "does not have enough cash to buy utility" + utility.getName());
            }

        } else {
            if (rollAgainFlag == 1) {
                System.out.println("UTILITY TIMEEEEEEEEEEEEEEEEEEEE");
                actions.add(0, "payRent");
                rollAgainFlag = 0;
            } else {
                actions.add(0,"payRent");
            }
        }
        // Player does not pay rent to itself
        if (ownedUtilities.contains(utility)) {
            actions.clear();
        }
    }

    /**
     * When player lands on Tax this method is invoked by accept method in that BoardPart
     **/
    @Override
    public void visit(Tax tax) {
        bp = tax;
        int taxCost = tax.getCost();
        while (this.cash < taxCost) {

            int mortgageReturn = mortgage();
            if (mortgageReturn == 0) break;
        }
        this.cash -= taxCost;
        System.out.println(this.name + " on " + tax.getName() + " paid $" + taxCost + " has $" + this.cash);
    }

    /** This method is responsible of one of the core aspects of the game that is dice rolling. It also checks
     *  if player rolled dice 3 times in row if so sends player to jail.
     * */

    public void rollDice() {
        actions.remove("rollDice");



        firstDie = rand.nextInt(6) + 1;
        secondDie = rand.nextInt(6) + 1;
        diceValue = firstDie + secondDie;
        System.out.println(name + " rolls " + firstDie + " + " + secondDie + " = " + diceValue);
        if (firstDie == secondDie) {
            // if in jail get out
            doubleCounter += 1;
            if (jailStatus == 1) {
                System.out.println("ROLLED DOUBLE OUT OF JAIL");
                actions.add(0,"moveToPosition");
                jailStatus = 0;

            } else {
                if (doubleCounter == 3) {
                    System.out.println("ROLLED DOUBLE THREETIMES IN ROW GOIN TO JAIL");
                    jailStatus = 1;
                    int bpInd = bp.getInd();
                    if (bpInd > 10) diceValue = 50 - bpInd;
                    else diceValue = 10 - bpInd;
                    actions.add(0,"moveToPosition");

                } else {
                    actions.add(0,"moveToPosition");
                    actions.add("rollDice");
                    rollAgainFlag = 1;
                    System.out.println("ROLLED DOUBLE  doubleCounter == " + doubleCounter);
                }

            }

        } else {
            if (jailStatus == 0) {
                actions.add(0,"moveToPosition");
                doubleCounter = 0;
            }

        }


    }

    /**
     * Responsible of chance or community chest card picks
     */
    public void pickUpCard() {
        actions.remove("pickUpCard");
        int ok = rand.nextInt(16);
        System.out.println(" OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO "+ ok);
        if (deckType == 1) chanceDeck.get(ok).doOperation(this);
        else if(deckType == 2) communityChestDeck.get(rand.nextInt(16)).doOperation(this);

    }

    /**
     * It takes different action according to boardPart player located. Other than that responsible of paying rent to another
     * instance of player class. If object that has to pay rent does not have enough cash it invokes mortgage function.
     */
    public void payRent() {
        actions.remove("payRent");
        if (jailStatus == 0) {
            if (landedOnType == 1 && landedOnProperty.getOwner().getJailStatus() == 0) {
                int propRent = landedOnProperty.getActiveRent(landedOnProperty.getOwner().getColorGroupCount(landedOnProperty.getColorGroup()));
                while (this.cash < propRent) {

                    int mortgageReturn = mortgage();
                    if (mortgageReturn == 0) break;
                }
                this.cash -= propRent;
                int cashOwner = landedOnProperty.getOwner().getCash();
                cashOwner += propRent;
                landedOnProperty.getOwner().setCash(cashOwner);
                System.out.println(this.name + " paid property rent $" + propRent + " to " + landedOnProperty.getOwner().getName());
            } else if (landedOnType == 2 && landedOnRailroad.getOwner().getJailStatus() == 0) {
                int railroadRent = landedOnRailroad.getActiveRent((landedOnRailroad.getOwner().getRailRoadListSize()));
                while (this.cash < railroadRent) {

                    int mortgageReturn = mortgage();
                    if (mortgageReturn == 0) break;
                }
                this.cash -= railroadRent;
                int cashOwner = landedOnRailroad.getOwner().getCash();
                cashOwner += railroadRent;
                landedOnRailroad.getOwner().setCash(cashOwner);
                System.out.println(this.name + " paid railroad rent $" + railroadRent + " to " + landedOnRailroad.getOwner().getName());
            } else if (landedOnType == 3 && landedOnUtility.getOwner().getJailStatus() == 0) {
                int ownerUtilityCount = landedOnUtility.getOwner().getUtilityListSize();
                int utilityRent;
                if (ownerUtilityCount == 2) utilityRent = diceValue * 10;
                else utilityRent = diceValue * 4;
                while (this.cash < utilityRent) {

                    int mortgageReturn = mortgage();
                    if (mortgageReturn == 0) break;
                }

                this.cash = this.cash - utilityRent;
                int cashOwner = landedOnUtility.getOwner().getCash();
                cashOwner += utilityRent;
                landedOnUtility.getOwner().setCash(cashOwner);
                System.out.println(this.name + " paid utility rent $" + utilityRent + " to " + landedOnUtility.getOwner().getName());
            }
        }


    }
    public void moveToPosition() {
        actions.remove("moveToPosition");

        this.totalDiceValue += diceValue;
        if (this.totalDiceValue >= 40) {
            if (jailStatus != 1) {
                this.cash += this.turnMoney;
                System.out.println(this.name + " finished one tour and received $" + this.cash  + " and has " + this.cash);

            }

            totalDiceValue = totalDiceValue - 40;
        }

    }

    /**
     * It is invoked when player wants to buy property.
     */
    public void buyProperty() {
        actions.remove("buyProperty");
        if (jailStatus == 0) {
            if (landedOnType == 1) {
                ownedProperties.add(landedOnProperty);
                this.cash -= landedOnProperty.getCost();
                landedOnProperty.setOwned(1);
                landedOnProperty.setOwner(this);
                int val = colorGroupMap.get(landedOnProperty.getColorGroup());
                colorGroupMap.put(landedOnProperty.getColorGroup(), val + 1);

                System.out.println(this.name + " bought property " + landedOnProperty.getName() + "now has $" + this.cash);
            } else if (landedOnType == 2) {
                ownedRailRoads.add(landedOnRailroad);
                this.cash -= landedOnRailroad.getCost();
                landedOnRailroad.setOwned(1);
                landedOnRailroad.setOwner(this);
                System.out.println(this.name + " bought railroad " + landedOnRailroad.getName());
            } else if (landedOnType == 3) {
                ownedUtilities.add(landedOnUtility);
                this.cash -= landedOnUtility.getCost();
                landedOnUtility.setOwned(1);
                landedOnUtility.setOwner(this);
                System.out.println(this.name + " bought utility " + landedOnUtility.getName());
            }


        }

    }

    /**
     *  It is invoked when player does not have money to pay rent or pay tax.
     *  sets property, railroad or utility owned to 0 and increase player's cash by
     *  boardParts mortgage value.
     *
     */
    public int mortgage() {
        if (ownedProperties.size() != 0) {
            System.out.println(name + "mortgaging " + ownedProperties.get(0).getName());
            int val = colorGroupMap.get(ownedProperties.get(0).getColorGroup());
            ownedProperties.get(0).setOwned(0);
            colorGroupMap.put(ownedProperties.get(0).getColorGroup(), val - 1);
            this.cash += ownedProperties.get(0).getMortgage();
            ownedProperties.remove(0);
            return 1;
        } else if (ownedUtilities.size() != 0) {
            System.out.println(name + "mortgaging utility" + ownedUtilities.get(0).getName());
            ownedUtilities.get(0).setOwned(0);
            this.cash += ownedUtilities.get(0).getMortgage();
            ownedUtilities.remove(0);
            return 1;
        } else if (ownedRailRoads.size() != 0) {
            System.out.println(name + "mortgaging railroad" + ownedRailRoads.get(0).getName());
            ownedRailRoads.get(0).setOwned(0);
            this.cash += ownedRailRoads.get(0).getMortgage();
            ownedRailRoads.remove(0);
            return 1;
        }
        return 0;
    }

    public int getColorGroupCount(String cGroup) {
        return colorGroupMap.get(cGroup);
    }

    public int getUtilityListSize() {
        return ownedUtilities.size();
    }

    public int getRailRoadListSize() {
        return ownedRailRoads.size();
    }



    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getCash() {
        return this.cash;
    }

    public String getName() {
        return this.name;
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


    public int getFirstDie() {
        return firstDie;
    }

    public int getSecondDie() {
        return secondDie;
    }

    public List<String> getActions() {
        return actions;
    }

    public void addAction(String action) {
        actions.add(action);
    }

    public void removeAction(String action) {
        actions.remove(action);
    }

    public void refreshDiceValue() {
        this.firstDie = 0;
        this.secondDie = 0;
        this.diceValue = 0;
    }


    public BoardPart getBp() {
        return this.bp;
    }

    public void setChanceDeck(List<Card> chanceDeck) {
        this.chanceDeck = chanceDeck;
    }

    public void setCommunityChestDeck(List<Card> communityChestDeck) {
        this.communityChestDeck = communityChestDeck;
    }
}
