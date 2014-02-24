package com.step.uno.server.controller;


import com.step.communication.channel.MessageChannel;
import com.step.communication.channel.MessageChannelListener;
import com.step.uno.messages.*;
import com.step.uno.model.Card;
import com.step.uno.model.Game;
import com.step.uno.model.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PlayerProxy implements MessageChannelListener {
    private MessageChannel channel;
    private PlayerProxyObserver observer;
    private String playerName;
    private Player player;

    public PlayerProxy(MessageChannel channel, PlayerProxyObserver observer) {
        this.channel = channel;
        this.observer = observer;
    }

    public void start() {
        channel.startListeningForMessages(this);
    }

    @Override
    public void onError(MessageChannel client, Exception e) {

    }

    private void onClientMessage(Introduction introduction) {
        this.player = new Player(introduction.playerName);
        observer.onPlayerRegistered(this.player);
    }

    private void onClientMessage(PlayCardAction playCard) {
        observer.onPlayerPlayed(player, playCard.card, playCard.newColour);
    }

    private void onClientMessage(DrawCardAction drawCard) {
        observer.onPlayerDrewCard(player);

    }

    private void onClientMessage(DrawTwoCardAction drawCard) {
        observer.onPlayerDrewTwoCards(player);
    }

    private void onClientMessage(DeclareUnoAction declareUno) {
        observer.onPlayerDeclaredUno(player);
    }

    private void onClientMessage(CatchUnoAction catchUno) {
        observer.onPlayerCaughtUno(player, catchUno.playerIndex);
    }

    private void onClientMessage(NoActionOnDrawnCard noAction) {
        observer.onNoActionOnDrawnCard(player);
    }


    @Override
    public void onMessage(MessageChannel client, Object message) {
        try {
            Method method = this.getClass().getDeclaredMethod("onClientMessage", message.getClass());
            method.invoke(this, message);
        } catch (NoSuchMethodException e) {

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionClosed(MessageChannel client) {

    }

    public void sendSnapshot(Game game) {
        Snapshot snapshot = new Snapshot();
        game.populate(snapshot, player);
        channel.send(snapshot);
    }

    public void sendResult(GameResult result) {
        channel.send(result);
    }

    public void sendWaitingForDrawnCardAction(Player player, Card card) {
        if (this.player != player) return;
        channel.send(new WaitingForDrawnCardAction(card));
    }
}