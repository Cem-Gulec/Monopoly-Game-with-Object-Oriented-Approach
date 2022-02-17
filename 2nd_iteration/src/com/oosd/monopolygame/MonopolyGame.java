package com.oosd.monopolygame;

import com.oosd.monopolygame.action.*;
import com.oosd.monopolygame.board.*;
import com.oosd.monopolygame.card.Card;
import com.oosd.monopolygame.card.CardFactory;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Creates components of the game. It is esponsible of game flow.
 *
 */
public class MonopolyGame {
    private List<BoardPart> board;
    private List<Player> playerList;
    private List<Player> currentPlayerList;
    private int playerCount;

    private BoardPartFactory boardPartFactory;
    private PlayerAction playerAction;
    private int turnValue;
    private List<Card> chanceDeck;
    private List<Card> communityChestDeck;
    private double railRoadProb;
    private double utilityProb;
    private double propertyProb;
    private int turnMoney;

    public MonopolyGame(int playerCount, int propertyProb, int railRoadProb, int utilityProb, int turnMoney, int starterCash,
                         JSONObject jsonBoard, JSONObject jsonChance, JSONObject jsonCommunityChest) {
        this.playerCount = playerCount;
        this.playerAction = new PlayerAction();
        this.turnValue = 0;
        createDeck(jsonChance, jsonCommunityChest);
        createPlayers(propertyProb, railRoadProb, utilityProb,turnMoney, starterCash);
        createBoard(jsonBoard);
        decideMoveOrder();
        startGame();
    }

    /**
     * Creates chanceDeck and communityChestDeck by parsing given json file.
     *
     * @param jsonChance
     * @param jsonCommunityChest
     */
    public void createDeck(JSONObject jsonChance, JSONObject jsonCommunityChest) {
        System.out.println("Creating deck");
        CardFactory cardFactory = new CardFactory();
        chanceDeck = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            JSONObject jsonObject = (JSONObject) jsonChance.get(Integer.toString(i));
            Card card = cardFactory.getCard(jsonObject);
            chanceDeck.add(card);
        }
        communityChestDeck = new ArrayList<>();
        for (int j = 0; j < 16; j++) {
            JSONObject jsonObject = (JSONObject) jsonCommunityChest.get(Integer.toString(j));
            Card card = cardFactory.getCard(jsonObject);
            communityChestDeck.add(card);
        }
        System.out.println("Decks are created");
    }

    /**
     * Creates Board by parsing given json file.
     *
     * @param boardPartJson
     */
    public void createBoard(JSONObject boardPartJson) {
        System.out.println("Creating board");
        BoardPartFactory boardPartFactory = new BoardPartFactory();
        // System.out.println(boardPartJson);
        int i;
        board = new ArrayList<>();
        for (i = 0; i < 40; i++) {
            JSONObject jsonObject = (JSONObject) boardPartJson.get(Integer.toString(i));
            BoardPart boardPart = boardPartFactory.getBoardPart(jsonObject, i);
            board.add(boardPart);
        }
        System.out.println("Board is created");
    }

    /**
     * Creates computer controlled players.
     *
     * @param propertyProb
     * @param railRoadProb
     * @param utilityProb
     * @param turnMoney
     * @param starterCash
     */
    public void createPlayers(int propertyProb, int railRoadProb, int utilityProb, int turnMoney, int starterCash) {
        System.out.println("Creating players");

        this.playerList = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            String name = "Player" + (i + 1);
            Player player = new Player(name, propertyProb, railRoadProb ,utilityProb , turnMoney);
            player.setChanceDeck(chanceDeck);
            player.setCommunityChestDeck(communityChestDeck);
            this.playerList.add(player);
            System.out.println(player.getName() + " is created");
            player.setCash(starterCash);
            System.out.println(player.getName() + " has " + player.getCash() + "$");
        }
        System.out.println("Total of " + playerCount + " players are created");
    }

    /**
     *  Decides which player will roll the dice first each round.
     */
    public void decideMoveOrder() {
        // implement sorting according to rolled dice value
        System.out.println("Deciding move order");
        // might move rollDice up!

        for (int j = 0; j < playerCount; j++) {
            this.playerList.get(j).addAction("rollDice");
        }

        Player currentPlayer;
        for (int i = 0; i < playerCount; i++) {
            currentPlayer = playerList.get(i);
            commandMaker(currentPlayer);
        }
        System.out.println("----------------------");
        int n = playerList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (playerList.get(j).getDiceValue() < playerList.get(j + 1).getDiceValue()) {
                    // swap temp and arr[i]
                    Collections.swap(playerList, j, (j + 1));
                }
            }
        }
        for (int i = 0; i < playerCount; i++) {
            currentPlayer = playerList.get(i);
            currentPlayer.refreshDiceValue();
        }
        /*
        for (int i=0; i < playerCount; i++) {
            System.out.println((playerList.get(i).getName()) + "------" + playerList.get(i).getDiceValue());
        }
        */
        System.out.println(playerList.get(0).getName() + " goes first");
        currentPlayerList = playerList;
    }

    /**
     *  Starts the game and controls the game flow.
     */
    public void startGame() {
        System.out.println("Starting game...");

        int totalDiceValue;
        while (currentPlayerList.size() != 1) {
            this.turnValue++;
            System.out.println("   ---      ");
            System.out.println("Game is on!!! turn == " + this.turnValue);
            System.out.println("----------");
            Player currentPlayer;

            for (int j = 0; j < currentPlayerList.size(); j++) {
                currentPlayerList.get(j).doubleCounter = 0;
                currentPlayerList.get(j).addAction("rollDice");
            }
            for (int i = 0; i < currentPlayerList.size(); i++) {
                currentPlayer = currentPlayerList.get(i);
                while (currentPlayer.getActions().size() != 0) {
                    commandMaker(currentPlayer);
                }
                if (currentPlayerList.get(i).getCash() <= 0) {
                    System.out.println(currentPlayerList.get(i).getName() + " BANKRUPTED!");
                    currentPlayerList.remove(currentPlayerList.get(i));
                }
                System.out.println(currentPlayer.getName() + " HAS $" + currentPlayer.getCash());
            }
            if (currentPlayerList.size() == 1) {
                System.out.println(currentPlayerList.get(0).getName() + " WON!");
            }
        }
    }


    /**
     *  Each round player gets set of commands. This method invokes those commands in Player class without directly
     *  reaching to class with the help of PlayerAction class.
     *
     * */

    public void commandMaker(Player player) {
        String status = player.getActions().get(0);

        if (status.equals("buyProperty")) {
            BuyProperty buyProperty = new BuyProperty(player);
            playerAction.setCommand(buyProperty);
            playerAction.takeAction();
        } else if (status.equals("moveToPosition")) {
            MoveToPosition moveToPosition = new MoveToPosition(player);
            playerAction.setCommand(moveToPosition);
            playerAction.takeAction();

            BoardPart located = board.get(player.getTotalDiceValue());
            located.accept(player);
        } else if (status.equals("payRent")) {
            PayRent payRent = new PayRent(player);
            playerAction.setCommand(payRent);
            playerAction.takeAction();
        } else if (status.equals("pickUpCard")) {
            PickUpCard pickUpCard = new PickUpCard(player);
            playerAction.setCommand(pickUpCard);
            playerAction.takeAction();
        } else if (status.equals("rollDice")) {
            RollDice rollDice = new RollDice(player);
            playerAction.setCommand(rollDice);
            playerAction.takeAction();
        }
    }


}

    
