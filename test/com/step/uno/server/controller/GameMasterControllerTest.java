package com.step.uno.server.controller;

import com.step.communication.channel.MessageChannel;
import com.step.communication.factory.UnoFactory;
import com.step.communication.server.MessageServer;
import com.step.uno.messages.Snapshot;
import com.step.uno.model.Card;
import com.step.uno.model.Colour;
import com.step.uno.model.Game;
import com.step.uno.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

class GameStub extends Game {
    boolean initializeCalled = false;
    boolean playCardCalled = false;
    public GameStub(int packs, List<Player> givenPlayers) {
        super(packs, givenPlayers);
    }

    @Override
    public void initialize() {
        initializeCalled = true;
    }

    @Override
    public void playCard(Player player, Card card, Colour newColour) {
        playCardCalled = true;
    }
}

public class GameMasterControllerTest {
    UnoFactory factory = mock(UnoFactory.class);
    GameMasterController controller = new GameMasterController(1, 1, factory);
    final Game mockedGame = mock(Game.class);
    final List<Player> players = new ArrayList<>();
    final GameStub stub = new GameStub(1, players);
    Player gabbar = new Player("Gabbar");

    @Before
    public void setUp() throws Exception {
        when(factory.createGame(1, players)).thenReturn(mockedGame);
    }

    @Test
    public void startsGameMasterWhenAsked() {

        when(factory.createGame(1, players)).thenReturn(stub);
        controller.startGame();
        verify(factory).createGame(1, players);
        assertTrue(stub.initializeCalled);
    }

    @Test
    public void waitsForConnectionsToStartTheGame() {
        final MessageServer messageServer = mock(MessageServer.class);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return messageServer;
            }
        }).when(factory).createMessageServer();

        controller.waitForConnections();

        verify(factory).createMessageServer();
        verify(messageServer).startListeningForConnections(controller);
    }

    @Test
    public void startsGameWhenAllPlayersRegistered() {
        players.add(gabbar);
        controller.onPlayerRegistered(players.get(0));

        verify(factory).createGame(1, players);
    }

    @Test
    public void handlesWhenANewCardIsPlayed() {
        final List<Player> players = new ArrayList<>();
        final GameStub stub = new GameStub(1, players);
        when(factory.createGame(1, players)).thenReturn(stub);


        controller.startGame();
        controller.onPlayerPlayed(gabbar, new Card(), Colour.Black);

        assertTrue(stub.playCardCalled);
    }

    @Test
    public void handlesWhenPlayerDrawsACard() {
        controller.startGame();
        controller.onPlayerDrewCard(gabbar);

        verify(mockedGame,times(1)).drawCard(gabbar);
    }

    @Test
    public void sendsDeclareUnoRequestWhenInvoked() {
        controller.startGame();
        controller.onPlayerDeclaredUno(gabbar);

        verify(mockedGame,times(1)).declareUno(gabbar);
    }

    @Test
    public void sendsCatchUnoRequestWhenInvoked() {
        controller.startGame();
        controller.onPlayerCaughtUno(gabbar, 2);

        verify(mockedGame,times(1)).catchUno(gabbar, 2);
    }

    @Test
    public void sendsDrawCardRequestWhenRequired() {
        controller.startGame();
        controller.onPlayerDrewCard(gabbar);

        verify(mockedGame,times(1)).drawCard(gabbar);
    }
    @Test
    public void sendsDrawTwoCardsRequestWhenRequired() {
        controller.startGame();
        controller.onPlayerDrewTwoCards(gabbar);

        verify(mockedGame,times(1)).drawTwoCards(gabbar);
    }

    @Test
    public void gameMovesForwardIfNoActionDoneByPlayer() {
        controller.startGame();
        controller.onNoActionOnDrawnCard(gabbar);

        verify(mockedGame,times(1)).moveForwardAsPlayerTookNoActionOnDrawnCard();
    }

    @Test
    public void rejectsConnectionsAfterAllPlayersJoin(){
        StubFactory stub = new StubFactory();
        MessageChannel channel = mock(MessageChannel.class);
        MessageChannel lateChannel = mock(MessageChannel.class);
        GameMasterController controller = new GameMasterController(1,1,stub);
        controller.waitForConnections();
        controller.onNewConnection(channel);
        controller.onNewConnection(lateChannel);
        verify(lateChannel,times(1)).stop();
        verify(lateChannel,never()).send(any(Snapshot.class));
    }

    class StubFactory extends UnoFactory {
        public final MessageServer messageServer = mock(MessageServer.class);

        @Override
        public MessageServer createMessageServer() {
            return messageServer;
        }
    }

}