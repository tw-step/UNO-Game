package com.step.uno.server.controller;

import com.step.communication.channel.MessageChannel;
import com.step.communication.factory.UnoFactory;
import com.step.communication.server.MessageServer;
import com.step.communication.server.MessageServerListener;
import com.step.uno.messages.GameResult;
import com.step.uno.model.*;

import java.util.ArrayList;
import java.util.List;

public class GameMasterController implements MessageServerListener, PlayerProxyObserver {
    private final int totalPlayers;
    private final int totalPacks;
    private final UnoFactory factory;
    private MessageServer server;
    private final List<PlayerProxy> proxies = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private Game game;

    public GameMasterController(int totalPlayers, int packs, UnoFactory factory) {
        this.totalPlayers = totalPlayers;
        this.totalPacks = packs;
        this.factory = factory;
    }

    public void waitForConnections() {
        server = factory.createMessageServer();
        server.startListeningForConnections(this);
    }

    @Override
    public void onNewConnection(MessageChannel channel) {
        if (proxies.size() == totalPlayers) {
            channel.stop();
            return;
        }
        System.out.println("new Connection");
        PlayerProxy proxy = factory.createPlayerProxy(channel, this);
        proxy.start();
        proxies.add(proxy);
    }

    public void startGame() {
        game = factory.createGame(totalPacks, players);
        game.initialize();
        sendSnapshot();
    }

    private void sendSnapshot() {
        for (PlayerProxy proxy : proxies)
            proxy.sendSnapshot(game);
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onPlayerRegistered(Player player) {
        players.add(player);
        if (players.size() == totalPlayers)
            startGame();
    }

    @Override
    public void onPlayerPlayed(Player player, Card card, Colour newColour) {
        game.playCard(player, card, newColour);
        if (player.hasWon())
            sendResult();
        else
            sendSnapshot();
    }

    private void sendResult() {
        GameResult result = new GameResult();
        game.populate(result);
        for (PlayerProxy proxy : proxies)
            proxy.sendResult(result);
    }

    @Override
    public void onPlayerDrewCard(Player player) {
        Card card = game.drawCard(player);
        sendWaitingForDrawnCardAction(player, card);
    }

    private void sendWaitingForDrawnCardAction(Player player, Card card) {
        for (PlayerProxy proxy : proxies) {
            proxy.sendWaitingForDrawnCardAction(player, card);
        }
    }

    @Override
    public void onPlayerDeclaredUno(Player player) {
        game.declareUno(player);
        sendSnapshot();
    }

    @Override
    public void onPlayerCaughtUno(Player player, int playerIndex) {
        game.catchUno(player, playerIndex);
        sendSnapshot();
    }

    @Override
    public void onPlayerDrewTwoCards(Player player) {
        game.drawTwoCards(player);
        sendSnapshot();
    }

    @Override
    public void onNoActionOnDrawnCard(Player player) {
        game.moveForwardAsPlayerTookNoActionOnDrawnCard();
        sendSnapshot();
    }
}