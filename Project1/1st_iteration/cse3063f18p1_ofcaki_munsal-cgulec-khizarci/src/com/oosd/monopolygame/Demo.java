package com.oosd.monopolygame;

import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

public class Demo {
    public static void main(String[] args) {
        int playerCount;
        playerCount = 4;


        JSONObject jsonObject = getJsonObject("src/com/oosd/monopolygame/monopolyBoard.json");

        MonopolyGame monopolyGame = new MonopolyGame(playerCount);
        monopolyGame.createBoard(jsonObject);
        monopolyGame.createPlayers();
        monopolyGame.decideMoveOrder();
        monopolyGame.startGame();

    }

    private static JSONObject getJsonObject(String fileName) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            Object obj = parser.parse(new FileReader(fileName));
            jsonObject = (JSONObject) obj;
            //System.out.println(jsonObject.get("0"));
            //JSONObject jsonObject12 = (JSONObject)jsonObject.get("0");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


        /*


        HashMap<Integer, HashMap<String, String>> hmap = new HashMap<Integer, HashMap<String, String>>();
        HashMap<String, String> innerHmap = new HashMap<String, String>();
        */


}
