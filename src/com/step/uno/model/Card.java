package com.step.uno.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Card implements Serializable {
    public Colour colour;
    public Sign sign;

    private static List<Card> createPack() {
        List<Card> cards = new ArrayList<>();
        Colour[] colours = {Colour.Blue, Colour.Green, Colour.Red, Colour.Yellow};
        for (Colour c : colours) {
            cards.add(createCard(c, "_0"));
            for (int times = 0; times < 2; times++) {
                for (int i = 1; i < 10; i++) {
                    cards.add(createCard(c, "_" + i));
                }
                cards.add(createCard(c, "Reverse"));
                cards.add(createCard(c, "Skip"));
                cards.add(createCard(c, "DrawTwo"));
            }
        }

        for (int times = 0; times < 4; times++) {
            cards.add(createCard(Colour.Black, "Wild"));
            cards.add(createCard(Colour.Black, "WildDrawFour"));
        }
        return cards;
    }

    public static List<Card> createNewPacks(int packs) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < packs; i++)
            cards.addAll(createPack());
        return cards;
    }

    private static Card createCard(Colour c, String signText) {
        Card card = new Card();
        card.colour = c;
        card.sign = Sign.valueOf(signText);
        return card;
    }

    @Override
    public String toString() {
        return this.colour + " " + this.sign;
    }
}