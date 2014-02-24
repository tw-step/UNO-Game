package com.step.uno.client.controller;

import com.step.uno.StubFactory;
import com.step.uno.client.model.GameClient;
import com.step.uno.client.view.JoinGameView;
import com.step.uno.client.view.PlayerView;
import com.step.uno.messages.Introduction;
import com.step.uno.messages.Snapshot;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import static org.mockito.Mockito.*;

public class GameClientControllerTest {
    StubFactory stub = new StubFactory();
    PlayerView playerView = mock(PlayerView.class);
    JoinGameView joinGameView = mock(JoinGameView.class);
    private GameClientController controller;

    @Before
    public void setup() {
        when(joinGameView.switchToPlayerView(Matchers.<PlayerScreenController>any(), Matchers.<Snapshot>any())).thenReturn(playerView);
        controller = new GameClientController(stub);
        controller.bindView(joinGameView);
    }

    @Test
    public void sendsIntroductionAfterJoiningGame() {
        controller.join("serverAddress", "me");
        verify(stub.channel, times(1)).send(any(Introduction.class));
    }

    @Test
    public void doesNotInformUserWhenConnectionIsLostBeforeGameStarts() {
        controller.join("serverAddress", "me");
        controller.onConnectionClosed(stub.channel);
        verify(playerView, times(0)).showDisconnected();
    }

    @Test
    public void viewShouldChangeToPlayerViewFromJoinViewOnNewSnapshot() {
        controller.join("serverAddress", "me");
        Snapshot snapshot = new Snapshot();
        controller.onMessage(stub.channel, snapshot);
        verify(joinGameView, times(1)).switchToPlayerView(stub.createScreenController(), snapshot);
    }


    @Test
    public void updatePlayerViewWithNewSnapshot() {
        controller.join("serverAddress", "me");
        Snapshot snapshot = new Snapshot();
        controller.onMessage(stub.channel, snapshot);

        controller.onMessage(stub.channel, snapshot);
        verify(playerView, times(1)).update(stub.createScreenController());
    }


}