package com.step.uno.model;

import java.io.Serializable;

public enum Sign implements Serializable {
    _0(0, "0"),
    _1(1, "1"),
    _2(2, "2"),
    _3(3, "3"),
    _4(4, "4"),
    _5(5, "5"),
    _6(6, "6"),
    _7(7, "7"),
    _8(8, "8"),
    _9(9, "9"),
    Reverse(20, "Reverse"),
    Skip(20, "Skip"),
    DrawTwo(20, "DrawTwo"),
    WildDrawFour(50, "WildDrawFour"),
    Wild(50, "Wild");
    public final int points;
    private String representation;

    Sign(int points, String representation) {

        this.points = points;
        this.representation = representation;
    }

    @Override
    public String toString() {
        return this.representation;
    }
}
