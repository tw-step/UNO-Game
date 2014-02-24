package com.step.uno.client.screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DrawCardsPanel extends JPanel {
    private JButton draw;

    public DrawCardsPanel(boolean isMyTurn, ActionListener ClosePileController) {
        draw = new JButton("Draw Cards");
        draw.setPreferredSize(new Dimension(100, 150));
        draw.setEnabled(isMyTurn);
        draw.addActionListener(ClosePileController);
        draw.setFont(new Font("verdana", Font.BOLD, 18));
        add(draw);
        setVisible(true);
    }

    public void update(boolean isMyTurn) {
        draw.setEnabled(isMyTurn);
    }
}
