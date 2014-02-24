package com.step.uno.client.view;

import com.step.uno.client.controller.PlayerScreenController;
import com.step.uno.messages.Snapshot;

public interface JoinGameView {
    PlayerView switchToPlayerView(PlayerScreenController controller, Snapshot snapshot);
}
