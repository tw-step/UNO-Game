package com.step.uno.client.controller;

import com.step.uno.client.model.GameClient;
import com.step.uno.messages.Snapshot;
import com.step.uno.model.Card;
import com.step.uno.model.PlayerSummary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerScreenController {
    private GameClient gameClient;
    private Snapshot snapshot;

    public PlayerScreenController(GameClient gameClient, Snapshot snapshot) {
        this.gameClient = gameClient;
        this.snapshot = snapshot;
    }

    public Color getOpenCardColour() {
        return this.snapshot.openCard.colour.getColor();
    }


    public ClosePileActionListener createClosePileController() {

        return new ClosePileActionListener();
    }

    public String getPlayerName() {
        return this.snapshot.playerSummaries[this.snapshot.myPlayerIndex].name;
    }

    public OtherPlayerActionListener createOtherPlayerController() {
        return new OtherPlayerActionListener();
    }

    public PlayerCardsActionListener createPlayerCardsController() {
        return new PlayerCardsActionListener();
    }

    public boolean isMyTurn() {
        return snapshot.currentPlayerIndex == snapshot.myPlayerIndex;
    }

    public PlayerSummary[] getPlayerSummaries() {
        return snapshot.playerSummaries;
    }

    public boolean isInAscendingOrder() {
        return snapshot.isInAscendingOrder;
    }

    public String getOpenCardSign() {
        return snapshot.openCard.sign.toString();
    }

    public Card[] getPlayerCards() {
        return snapshot.myCards;
    }

    private class ClosePileActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class OtherPlayerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class PlayerCardsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == "uno") {
                gameClient.declareUno();
            }
        }
    }
}
