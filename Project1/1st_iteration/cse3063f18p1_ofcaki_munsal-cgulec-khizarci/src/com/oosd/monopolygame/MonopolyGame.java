package com.oosd.monopolygame;

import com.oosd.monopolygame.action.PlayerAction;
import com.oosd.monopolygame.action.MoveToPosition;
import com.oosd.monopolygame.action.RollDice;
import com.oosd.monopolygame.board.*;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MonopolyGame {
    private List<BoardPart> board;
    private List<Player> playerList;
    private List<Player> currentPlayerList;
    private int playerCount;
    private BoardPartFactory boardPartFactory;
    public MonopolyGame(int playerCount) {
        this.playerCount = playerCount;
    }
    public void createBoard(JSONObject boardPartJson) {
        System.out.println("Creating board");
        BoardPartFactory boardPartFactory = new BoardPartFactory();
        // System.out.println(boardPartJson);
        int i;
        board = new ArrayList<>();
        for (i = 0; i < 40; i++) {
            JSONObject jsonObject = (JSONObject)boardPartJson.get(Integer.toString(i));
            BoardPart boardPart = boardPartFactory.getBoardPart(jsonObject);
            board.add(boardPart);
        }
        System.out.println("Board is created");
    }
    public void createPlayers() {
        System.out.println("Creating players");
        Random rand = new Random();
        playerCount = rand.nextInt(6) + 2;
        this.playerList = new ArrayList<>();
        for (int i=0; i < playerCount; i++) {
            String color = "Player" + (i+1);
            Player player = new Player(color);
            this.playerList.add(player);
            System.out.println(player.getColor() + " is created");
            player.setCash(1500);
            System.out.println(player.getColor() + " has " + player.getCash() + "$");
        }
        System.out.println("Total of " + playerCount + " players are created");
    }

    public void decideMoveOrder() {
        // implement sorting according to rolled dice value
        System.out.println("Deciding move order");
        // might move rollDice up!
        PlayerAction playerAction;
        RollDice rollDice;
        for (int i=0; i < playerCount; i++) {
            rollDice = new RollDice(playerList.get(i));
            playerAction = new PlayerAction(rollDice);
            playerAction.takeAction();

        }
        System.out.println("----------------------");
        int n = playerList.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n - i - 1; j++){
                if (playerList.get(j).getDiceValue() < playerList.get(j + 1).getDiceValue()) {
                    // swap temp and arr[i]
                    Collections.swap(playerList, j, (j + 1));
                }
            }
        }
        /*
        for (int i=0; i < playerCount; i++) {
            System.out.println((playerList.get(i).getColor()) + "------" + playerList.get(i).getDiceValue());
        }
        */
        System.out.println(playerList.get(0).getColor() + " goes first");
        currentPlayerList = playerList;
    }

    public void startGame() {
        System.out.println("Starting game...");
        int turnValue = 0;
        int totalDiceValue;
        while (currentPlayerList.size() != 1) {
            turnValue++;
            System.out.println("   ---      ");
            System.out.println("Game is on!!! turn == " + turnValue);
            System.out.println("----------");
            for (int i=0; i<currentPlayerList.size(); i++) {
                // current player rolls the dice
                RollDice rollDice = new RollDice(playerList.get(i));
                PlayerAction playerAction = new PlayerAction(rollDice);
                playerAction.takeAction();


                totalDiceValue = currentPlayerList.get(i).getTotalDiceValue();
                totalDiceValue += rollDice.getDiceValue();
                currentPlayerList.get(i).setTotalDiceValue(totalDiceValue);
                // System.out.println("total dice val "+ totalDiceValue);
                if (totalDiceValue >= 40) {
                    currentPlayerList.get(i).setTotalDiceValue(totalDiceValue - 40);
                }

                MoveToPosition moveToPosition = new MoveToPosition(playerList.get(i));
                PlayerAction playerAction2 = new PlayerAction(moveToPosition);
                playerAction2.takeAction();

                board.get(currentPlayerList.get(i).getTotalDiceValue()).accept(currentPlayerList.get(i));


                if (currentPlayerList.get(i).getCash() <= 0) {
                    System.out.println(currentPlayerList.get(i).getColor() + " BANKRUPTED!");
                    currentPlayerList.remove(currentPlayerList.get(i));

                }
                if (currentPlayerList.size() == 1) {
                    System.out.println(currentPlayerList.get(0).getColor() + " WON!");
                }

            }

        }
    }
}

    
