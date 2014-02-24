package com.step.uno.client.screen;

import com.step.uno.model.PlayerSummary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OtherPlayersPanel extends JPanel {

    //UI components
    private JPanel players = new JPanel();
    private JLabel direction = new JLabel();
    private JScrollPane scrollPane = new JScrollPane();
    private ArrayList<JButton> playerButtons = new ArrayList<>();

    private ActionListener otherPlayersActionListener;

    public OtherPlayersPanel(PlayerSummary[] playerSummaries, boolean isInAscendingOrder, ActionListener OtherPlayersActionListener) {
        otherPlayersActionListener = OtherPlayersActionListener;

        createPlayerButtons(playerSummaries);
        direction.setFont(new Font("verdana", Font.BOLD, 30));
        setDirection(isInAscendingOrder);

        players.setVisible(true);
        scrollPane.getViewport().add(players);
        scrollPane.setPreferredSize(new Dimension(900, 120));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);
        add(direction);
        setSize(150, 150);
        setVisible(true);

    }

    private void setDirection(boolean isInAscendingOrder) {
        if (isInAscendingOrder)
            direction.setText("=>");
        else
            direction.setText("<=");
    }

    private void createPlayerButtons(PlayerSummary[] summaries) {

        for (PlayerSummary summary : summaries) {
            playerButtons.add(new JButton(summary.name + " : " + summary.cardsInHand));
            JButton playerButton = playerButtons.get(playerButtons.size() - 1);
            playerButton.setPreferredSize(new Dimension(150, 100));
            playerButton.setFont(new Font("verdana", Font.BOLD, 14));
            final int newPlayerIndex = playerButtons.indexOf(playerButton);
            playerButton.addActionListener(otherPlayersActionListener);
            players.add(playerButton);
        }
    }

    public void update(PlayerSummary[] summaries, boolean isInAscendingOrder) {
        for (int i = 0; i < playerButtons.size(); i++) {
            playerButtons.get(i).setText(summaries[i].name + " : " + summaries[i].cardsInHand);
        }
        setDirection(isInAscendingOrder);
    }
}

