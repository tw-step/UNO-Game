package com.step.uno.server;

import com.step.communication.factory.UnoFactory;
import com.step.uno.server.controller.GameMasterController;

/**
 * Starts the UNO_GAME server allows given numbers of players to join with given no of packs
 */
public class UnoServer {
    public static void main(String[] args) {
        int numberOfPlayers = Integer.parseInt(args[0]);
        int numberOfPacks = Integer.parseInt(args[1]);

        new GameMasterController(numberOfPlayers, numberOfPacks, new UnoFactory()).waitForConnections();
        System.out.println("waiting for players to connect..");
    }
}