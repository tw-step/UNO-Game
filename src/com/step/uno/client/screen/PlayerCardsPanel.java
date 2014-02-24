package com.step.uno.client.screen;

import com.step.uno.model.Card;
import com.step.uno.model.Colour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerCardsPanel extends JPanel {
    private JPanel cardsPanel = new JPanel();
    JPanel cardsAndUnoPanel = new JPanel();
    private JScrollPane CardsScrollPane = new JScrollPane();
    private ActionListener actionListener;
    private boolean isMyTurn;

    public PlayerCardsPanel(boolean isMyTurn, Card[] cards, ActionListener actionListener) {
        this.isMyTurn = isMyTurn;
        this.actionListener = actionListener;
        setCards(cards);
    }

    public void setCards(Card[] cards) {
        addCardsToPanel(cards);
        CardsScrollPane.getViewport().add(cardsPanel);
        CardsScrollPane.setPreferredSize(new Dimension(750, 180));
        CardsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        CardsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        JButton uno = new JButton("UNO");
        uno.setPreferredSize(new Dimension(100, 100));
        uno.setFont(new Font("Verdana", Font.BOLD, 16));

        cardsAndUnoPanel.add(uno);
        cardsAndUnoPanel.add(CardsScrollPane);

        add(cardsAndUnoPanel);
        uno.addActionListener(actionListener);
        setVisible(true);
    }

    private void addCardsToPanel(Card[] cards) {
        for (Card card : cards) {
            cardsPanel.add(createCardButton(card));
        }
    }

    private JButton createCardButton(Card card) {
        JButton cardButton = new JButton();
        cardButton.addActionListener(actionListener);
        cardButton.setForeground(Color.BLACK);
        cardButton.setText(card.sign.toString());
        cardButton.setEnabled(isMyTurn);
        cardButton.setFont(new Font("Verdana", Font.BOLD, 24));
        cardButton.setBackground(card.colour.getColor());
        if (card.colour == Colour.Black)
            cardButton.setForeground(Color.WHITE);
        cardButton.setPreferredSize(new Dimension(100, 150));
        cardButton.setVisible(true);
        return cardButton;
    }

    public void update(Card[] cards) {
        //logic is not right needs to be changed
        //not handeled uno button
        cardsPanel.removeAll();
        addCardsToPanel(cards);
        cardsPanel.revalidate();
    }
}
