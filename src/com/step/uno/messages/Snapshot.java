package com.step.uno.messages;

import com.step.uno.model.Card;
import com.step.uno.model.Colour;
import com.step.uno.model.PlayerSummary;

import java.io.Serializable;

public class Snapshot implements Serializable {
    public Card[] myCards;
    public PlayerSummary[] playerSummaries;
    public int currentPlayerIndex;
    public Card openCard;
    public boolean isInAscendingOrder;
    public int myPlayerIndex;
    public Colour runningColour;
    public int draw2Run;
}