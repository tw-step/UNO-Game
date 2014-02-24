package com.step.uno.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DeckTest {
    List<Card> cards = new ArrayList<>();
    Card redZero = new Card();
    Card blueTwo = new Card();
    Card greenFive = new Card();
    Deck deck = new Deck();


    @Before
    public void setup() {
        redZero.colour = Colour.Red;
        redZero.sign = Sign._0;
        blueTwo.colour = Colour.Blue;
        blueTwo.sign = Sign._2;
        greenFive.colour = Colour.Green;
        greenFive.sign = Sign._5;

        cards.add(redZero);
        cards.add(blueTwo);
        cards.add(greenFive);

        deck.addAll(cards);
    }

    @Test
    public void drawRemovesGivenCardFromDeck() {
        assertEquals(deck.draw(), redZero);
    }

    @Test
    public void addsNewCardToDeck() {
        Deck deck = new Deck();
        Card redZero = new Card();
        redZero.colour = Colour.Red;
        redZero.sign = Sign._0;

        deck.add(redZero);
        assertEquals(deck.draw(), redZero);
    }

    @Test
    public void givesTheLastCardInTheDeck() {
        assertEquals(deck.lookAtLast(), greenFive);
    }

    @Test
    public void checksThatTheDeckIsEmpty() {
        Deck deck = new Deck();
        assertTrue(deck.isEmpty());
    }

    @Test
    public void checksThatTheDeckIsNotEmpty() {
        assertFalse(deck.isEmpty());
    }

}
