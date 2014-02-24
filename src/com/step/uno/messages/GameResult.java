package com.step.uno.messages;

import com.step.uno.model.PlayerResult;

import java.io.Serializable;

public class GameResult implements Serializable {
    public PlayerResult[] players;
}
