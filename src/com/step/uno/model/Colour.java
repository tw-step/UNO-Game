package com.step.uno.model;

import java.awt.*;
import java.io.Serializable;

public enum Colour implements Serializable{
    Red(new Color(254, 46, 46)),
    Green(new Color(1, 223, 58)),
    Blue(new Color(46, 204, 250)),
    Yellow(new Color(247, 254, 46)),
    Black(new Color(10, 18, 42));
    private Color color;

    Colour(Color color) {

        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
