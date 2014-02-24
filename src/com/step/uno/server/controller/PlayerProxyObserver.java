package com.step.uno.server.controller;

import com.step.uno.model.Card;
import com.step.uno.model.Colour;
import com.step.uno.model.Player;

public interface PlayerProxyObserver {
    void onPlayerRegistered(Player player);

    void onPlayerPlayed(Player player, Card card, Colour newColour);

    void onPlayerDrewCard(Player player);

    void onPlayerDeclaredUno(Player player);

    void onPlayerCaughtUno(Player player, int playerIndex);

    void onPlayerDrewTwoCards(Player player);

    void onNoActionOnDrawnCard(Player player);
}
