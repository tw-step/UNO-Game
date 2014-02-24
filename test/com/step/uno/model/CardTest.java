package com.step.uno.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class CardTest {

    List<Card> pack = Card.createNewPacks(1);
    List<Card> numberedCards = new ArrayList<>();
    List<Card> reverseCards = new ArrayList<>();
    List<Card> skipCards = new ArrayList<>();
    List<Card> drawTwoCards = new ArrayList<>();
    List<Card> drawFourCards = new ArrayList<>();
    List<Card> wildCards = new ArrayList<>();

    @Before
    public void setup() {
        for (Card card : pack) {
            if (card.sign.points <= 9)
                numberedCards.add(card);
            if (card.sign.name().equals("Reverse"))
                reverseCards.add(card);
            if(card.sign.name().equals("Skip"))
                skipCards.add(card);
            if(card.sign.name().equals("DrawTwo"))
                drawTwoCards.add(card);
            if(card.sign.name().equals("Wild"))
                wildCards.add(card);
            if(card.sign.name().equals("WildDrawFour"))
                drawFourCards.add(card);
        }
    }

    @Test
    public void createsADeckWhichHas108Cards() {
        assertEquals(Card.createNewPacks(1).size(), 108);
    }

    @Test
    public void checksWhetherADeckHas76NumberedCardsInIt() {
        assertEquals(numberedCards.size(), 76);
    }

    @Test
    public void checksWhetherADeckHasEightReverseCards() {
        assertEquals(reverseCards.size(), 8);
    }

    @Test
    public void checksWhetherADeckHasEightSkipCards() {
        assertEquals(skipCards.size(), 8);
    }

    @Test
    public void checksWhetherADeckHasEightDrawTwoCards() {
        assertEquals(drawTwoCards.size(), 8);
    }

    @Test
    public void checksWhetherADeckHasFourDrawFourCards() {
        assertEquals(drawFourCards.size(), 4);
    }

    @Test
    public void checksWhetherADeckHasFourWildCards() {
        assertEquals(wildCards.size(), 4);
    }

    @Test
    public void toStringGivesTheColorAndSignOfACard() {
        Card card = new Card();
        card.sign = Sign._0;
        card.colour = Colour.Red;
        String colorAndSign = card.toString();
        assertEquals(colorAndSign,"Red 0");
    }

}
