package com.step.uno.client.model;

import com.step.uno.StubFactory;
import com.step.uno.messages.*;
import com.step.uno.model.Card;
import com.step.uno.model.Colour;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GameClientTest {
    StubFactory stub = new StubFactory();

    @Test
    public void shouldSendIntroductionWhenProvidedPlayerName() {
        stub.gameClient.sendIntroduction("Kajal");
        verify(stub.channel,times(1)).send(any(new Introduction("Kajal").getClass()));
    }

    @Test
    public void shouldSendPlayedCardWhenACardIsPlayed() {
        Card card = new Card();
        stub.gameClient.play(card, Colour.Black);
        verify(stub.channel,times(1)).send(any(new PlayCardAction(card,Colour.Black).getClass()));
    }

    @Test
    public void shouldInformNoActionOnDrawnCard() {
        stub.gameClient.informNoActionOnDrawnCard();
        verify(stub.channel,times(1)).send(any(NoActionOnDrawnCard.class));
    }

    @Test
    public void shouldSendDrawCardActionWhenACardIsDrawn() {
        stub.gameClient.draw();
        verify(stub.channel,times(1)).send(any(DrawCardAction.class));
    }

    @Test
    public void shouldSendDrawTwoCardActionWhenTwoCardsAreDrawn() {
        stub.gameClient.drawTwo();
        verify(stub.channel,times(1)).send(any(DrawTwoCardAction.class));
    }

    @Test
    public void shouldSendDeclareUnoWhenUnoIsDeclared() throws Exception {
        stub.gameClient.declareUno();
        verify(stub.channel,times(1)).send(any(DeclareUnoAction.class));
    }

    @Test
    public void shouldSendCatchActionWhenCatchUnoIsDeclared(){
        stub.gameClient.catchUno(1);
        verify(stub.channel,times(1)).send(any(CatchUnoAction.class));
    }
}
