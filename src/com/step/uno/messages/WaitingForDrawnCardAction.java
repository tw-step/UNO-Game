package com.step.uno.messages;

import com.step.uno.model.Card;

import java.io.Serializable;

public class WaitingForDrawnCardAction implements Serializable {
    public Card card;

    public WaitingForDrawnCardAction(Card card) {
        this.card = card;
    }
}
