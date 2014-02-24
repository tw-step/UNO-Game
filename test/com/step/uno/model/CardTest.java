package com.step.uno.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class CardTest {
    @Test
    public void createsADeckWhichHas108Cards() {
        assertEquals(Card.createNewPacks(1).size(), 108);
    }

    @Test
    public void checksWhetherADeckHas76NumberedCardsInIt() {
        List<Card> pack = Card.createNewPacks(1);
        List<Card> numberedCards = new ArrayList<>();
        for (Card card : pack) {
            if (card.sign.points <= 9)
                numberedCards.add(card);
        }
        assertEquals(numberedCards.size(), 76);
    }
}
