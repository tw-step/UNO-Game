package com.step.uno.client.screen;

import javax.swing.*;
import java.awt.*;

public class OpenCardPanel extends JPanel {
    JButton openPile = new JButton();

    public OpenCardPanel(Color color, String sign) {
        setOpenCard(color, sign);
        openPile.setPreferredSize(new Dimension(100, 150));
        openPile.setEnabled(false);
        openPile.setFont(new Font("verdana", Font.BOLD, 18));
        add(openPile);
        setVisible(true);
    }


    public void setOpenCard(Color color, String sign) {
        openPile.setText(sign);
        openPile.setBackground(color);
    }
}
