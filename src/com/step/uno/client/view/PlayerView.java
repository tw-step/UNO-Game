package com.step.uno.client.view;

import com.step.uno.client.controller.PlayerScreenController;

public interface PlayerView {
    void showDisconnected();

    void update(PlayerScreenController controller);
}