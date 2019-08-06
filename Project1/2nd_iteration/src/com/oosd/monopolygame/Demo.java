package com.oosd.monopolygame;

import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

import static java.lang.System.exit;
import static java.lang.System.setOut;

/**
 *  This program implements computer played MonopolyGame
 *
 * @authors
 * Cem Güleç 150117828
 * Kadir Hızarcı 150116004
 * Muratcan Ünsal 150117851
 * Ömer Faruk Çakı 150117821
 *
 * */
public class Demo {

    /**
     * Gets user inputs to launch the game. User either choose to provide parameters themselves or
     * let the system randomly generate.
     *
     * @param args
     */
    public static void main(String[] args) {

        // defaults
        int playerCount = 4;
        int turnMoney = 200;
        int starterCash = 1500;
        int railRoadProb = 100;
        int propertyProb = 50;
        int utilityProb = 30;

        // prepation
        Random rand = new Random();
        try {
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            System.out.println("Enter 1 for custom  or 2 for random variables ");
            int n = reader.nextInt();
            if (n == 1) {
                System.out.println("Enter integer between 2 and 8(inclusive) for player count");
                playerCount = reader.nextInt();
                if (playerCount < 2 || playerCount > 8) {
                    System.out.println("Out of bounds");
                    System.exit(1);
                }
                System.out.println("playerCount " + playerCount);

                System.out.println("Enter integer for the money player receives each round");
                turnMoney = reader.nextInt();
                System.out.println("turnMoney == " + turnMoney);;

                System.out.println("Enter integer for the player's initial money");
                starterCash = reader.nextInt();
                System.out.println("starterCash == " + starterCash);

                System.out.println("Enter integer between 0-100(inclusive)\n" +
                        "If you enter '60' for property buying probability, it means player will buy the property" +
                        " it landed with 0.6 probability.");


                System.out.println("Property buying probability: ");
                propertyProb = reader.nextInt();
                System.out.println("Property buying probability: " + propertyProb);

                System.out.println("Railroad buying probability: ");
                railRoadProb = reader.nextInt();
                System.out.println("Railroad buying probability: " + railRoadProb);

                System.out.println("Utility buying probability: ");
                utilityProb = reader.nextInt();
                System.out.println("Utility buying probability: " + utilityProb);
                if (!(checkBounds(railRoadProb) == 1 && checkBounds(propertyProb) == 1 && checkBounds(utilityProb) == 1)) {
                    System.out.println("Out of bound");
                    exit(1);
                }

            } else if (n == 2) {
                playerCount = rand.nextInt(7) + 2;
                turnMoney = rand.nextInt(201);
                propertyProb = rand.nextInt(101);
                railRoadProb = rand.nextInt(101);
                utilityProb = rand.nextInt(101);
                starterCash = rand.nextInt(1500);
                System.out.println("playerCount == " + playerCount);
                System.out.println("turnMoney == " + turnMoney);
                System.out.println("propertyProb == " + propertyProb);
                System.out.println("railRoadProb == " + railRoadProb);
                System.out.println("utilityProb == " + utilityProb);
                System.out.println("starterCash == " + starterCash);
            } else {
                System.out.println("Enter 1 or 2");
                exit(1);
            }

        } catch (InputMismatchException e) {
            System.out.println("Check your variable's type!");
            exit(1);

        }

        // parsing
        JSONObject jsonBoard = getJsonObject("Project1/2nd_iteration/resource/monopolyBoard.json");
        JSONObject jsonChance = getJsonObject("Project1/2nd_iteration/resource/chance.json");
        JSONObject jsonCommunityChest = getJsonObject("Project1/2nd_iteration/resource/communityChest.json");

        // launching game
        MonopolyGame monopolyGame = new MonopolyGame(playerCount, propertyProb, railRoadProb, utilityProb, turnMoney, starterCash,jsonBoard, jsonChance, jsonCommunityChest );

    }

    /**
     *  Parses json file at given location by @filename
     *  returns JSONObject which will be used to present BoardParts, ChanceDeck, CommunityChestDeck
     * */
    private static JSONObject getJsonObject(String fileName) {

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            Object obj = parser.parse(new FileReader(fileName));
            jsonObject = (JSONObject) obj;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private static int checkBounds(int n) {
        if (n > 100 || n < 0) {
            return 0;
        }
        return 1;
    }
}
