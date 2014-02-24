package com.step.uno.client.controller;

import com.step.communication.channel.MessageChannel;
import com.step.communication.channel.MessageChannelListener;
import com.step.communication.factory.UnoFactory;
import com.step.uno.client.model.GameClient;
import com.step.uno.client.view.JoinGameView;
import com.step.uno.client.view.PlayerView;
import com.step.uno.messages.Snapshot;
import com.step.uno.messages.WaitingForDrawnCardAction;

import java.lang.reflect.InvocationTargetException;

public class GameClientController implements MessageChannelListener {
    private final PlayerScreenController controller;
    private UnoFactory factory;
    private JoinGameView joinGameView;
    private PlayerView playerView;
    private MessageChannel channel;
    private GameClient gameClient;

    public GameClientController(UnoFactory factory) {
        this.factory = factory;
        controller =factory.createScreenController();
    }

    public void join(String serverAddress, String playerName) {
        channel = factory.connectTo(serverAddress, this);
        channel.startListeningForMessages(this);
        gameClient = factory.createGameClient(channel);
        gameClient.sendIntroduction(playerName);

    }

    @Override
    public void onError(MessageChannel client, Exception e) {

    }

    private void handle(Snapshot snapshot) {
        if (playerView == null){
            playerView = joinGameView.switchToPlayerView(controller, snapshot);
        }
        else playerView.update(controller);
    }

    private void handle(WaitingForDrawnCardAction waiting) {
        gameClient.informNoActionOnDrawnCard();
    }

    @Override
    public void onMessage(MessageChannel client, Object message) {
        try {
            getClass().getDeclaredMethod("handle", message.getClass()).invoke(this, message);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onConnectionClosed(MessageChannel client) {
        client.stop();
        if (playerView != null)
            playerView.showDisconnected();
    }

    public void bindView(JoinGameView joinGameView) {
        this.joinGameView = joinGameView;
    }
}
