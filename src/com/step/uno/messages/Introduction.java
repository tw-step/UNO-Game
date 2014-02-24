package com.step.uno.messages;

import java.io.Serializable;

public class Introduction implements Serializable{
    public String playerName;

    public Introduction(String playerName) {
        this.playerName = playerName;
    }


}
