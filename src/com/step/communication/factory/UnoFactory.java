package com.step.communication.factory;

import com.step.communication.channel.MessageChannel;
import com.step.uno.client.controller.PlayerScreenController;
import com.step.uno.client.model.GameClient;
import com.step.uno.model.Game;
import com.step.uno.model.Player;
import com.step.uno.server.controller.GameMasterController;
import com.step.uno.server.controller.PlayerProxy;

import java.util.List;

/**
 * all new Creation required for Uno Server is done here
 */
public class UnoFactory extends CommunicationFactory {
    public Game createGame(int packs, List<Player> players) {
        return new Game(packs, players);
    }

    public PlayerProxy createPlayerProxy(MessageChannel channel, GameMasterController controller) {
        return new PlayerProxy(channel, controller);
    }

    public GameClient createGameClient(MessageChannel channel) {
        return new GameClient(channel);
    }

    public PlayerScreenController createScreenController() {
        return null;
    }
}
