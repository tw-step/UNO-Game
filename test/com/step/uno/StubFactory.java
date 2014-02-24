package com.step.uno;

import com.step.communication.channel.MessageChannel;
import com.step.communication.channel.MessageChannelListener;
import com.step.communication.factory.UnoFactory;
import com.step.uno.client.controller.PlayerScreenController;
import com.step.uno.client.model.GameClient;

import static org.mockito.Mockito.mock;

public class StubFactory extends UnoFactory {
    public final MessageChannel channel = mock(MessageChannel.class);
    public final GameClient gameClient = new GameClient(channel);
    private PlayerScreenController screenController =mock(PlayerScreenController.class);

    @Override
    public MessageChannel connectTo(String serverAddress, MessageChannelListener observer) {
        return channel;
    }

    @Override
    public GameClient createGameClient(MessageChannel channel) {
        return gameClient;
    }

    @Override
    public PlayerScreenController createScreenController() {
        return screenController;
    }
}
