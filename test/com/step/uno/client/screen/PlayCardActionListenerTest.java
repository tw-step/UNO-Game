package com.step.uno.client.screen;

import com.step.uno.client.model.GameClient;
import com.step.uno.messages.Snapshot;
import com.step.uno.model.Card;
import com.step.uno.model.Colour;
import com.step.uno.model.Sign;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class PlayCardActionListenerTest {

    GameClient gameClient = mock(GameClient.class);
    JPanel panel = new JPanel();
    Snapshot snapshot = new Snapshot();

    @Test
    public void allowsToPlayTheCardIfColorMatchesToTheCardOnOpenPile() {
        Card card = new Card();
        card.sign = Sign._1;
        card.colour = Colour.Blue;

        Card openCard = new Card();
        openCard.colour = Colour.Blue;
        openCard.sign = Sign._2;
        snapshot.openCard = openCard;

        PlayCardActionListener actionListener = new PlayCardActionListener(card, gameClient, panel, snapshot);
        assertTrue(actionListener.canPlay(card, openCard));

    }

    @Test
    public void allowsToPlayTheCardIfNumberMatchesToTheCardOnOpenPile() {
        Card card = new Card();
        card.sign = Sign._1;
        card.colour = Colour.Blue;

        Card openCard = new Card();
        openCard.colour = Colour.Green;
        openCard.sign = Sign._1;
        snapshot.openCard = openCard;

        PlayCardActionListener actionListener = new PlayCardActionListener(card, gameClient, panel, snapshot);
        assertTrue(actionListener.canPlay(card, openCard));

    }

    @Test
    public void wildCardCanBePlayedOnAnyCard() {
        Card card = new Card();
        card.sign = Sign.Wild;
        card.colour = Colour.Black;

        Card openCard = new Card();
        openCard.colour = Colour.Green;
        openCard.sign = Sign._1;
        snapshot.openCard = openCard;

        PlayCardActionListener actionListener = new PlayCardActionListener(card, gameClient, panel, snapshot);
        assertTrue(actionListener.canPlay(card, openCard));
    }

    @Test
    public void whenNeitherNumberNorColorMatchesPlayerShouldNotBeAbleToPlayCard() {
        Card card1 = new Card();
        card1.sign = Sign._2;
        card1.colour = Colour.Red;

        Card openCard = new Card();
        openCard.colour = Colour.Green;
        openCard.sign = Sign._1;

        snapshot.openCard = openCard;

        PlayCardActionListener actionListener = new PlayCardActionListener(card1, gameClient, panel, snapshot);
        assertFalse(actionListener.canPlay(card1, openCard));
    }


}
