package com.step.uno.messages;

import java.io.Serializable;

public class CatchUnoAction implements Serializable {
    public int playerIndex;

    public CatchUnoAction(int playerIndex) {

        this.playerIndex = playerIndex;
    }
}
