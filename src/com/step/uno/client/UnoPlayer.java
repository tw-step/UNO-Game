package com.step.uno.client;

import com.step.communication.factory.UnoFactory;
import com.step.uno.client.controller.GameClientController;
import com.step.uno.client.screen.JoinGameScreen;

public class UnoPlayer {
    public static void main(String[] args) {

        UnoFactory factory = new UnoFactory();

        GameClientController controller = new GameClientController(factory);
        JoinGameScreen joinGameScreen = new JoinGameScreen(controller);
        joinGameScreen.showScreen();
    }
}