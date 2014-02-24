package com.step.uno.client.screen;

import com.step.uno.client.controller.PlayerScreenController;
import com.step.uno.client.view.PlayerView;
import com.step.uno.messages.Snapshot;

import javax.swing.*;
import java.awt.*;

public class PlayerScreen extends JFrame implements PlayerView {


    private OpenCardPanel openCardPanel;
    private DrawCardsPanel drawCardsPanel;
    private OtherPlayersPanel otherPlayersPanel;
    private PlayerCardsPanel playerCardsPanel;
    private JPanel currentPlayer;
    private JLabel player;
    private ActivityLogPanel activityLogPanel;

    public PlayerScreen(PlayerScreenController controller, Snapshot snapshot) {

        this.setTitle((controller.getPlayerName()));

        openCardPanel = new OpenCardPanel(controller.getOpenCardColour(), snapshot.openCard.sign.toString());
        drawCardsPanel = new DrawCardsPanel(controller.isMyTurn(), controller.createClosePileController());
        otherPlayersPanel = new OtherPlayersPanel(snapshot.playerSummaries, snapshot.isInAscendingOrder,
                controller.createOtherPlayerController());

        playerCardsPanel = new PlayerCardsPanel(controller.isMyTurn(), snapshot.myCards, controller.createPlayerCardsController());
        activityLogPanel = new ActivityLogPanel();
        currentPlayer = new JPanel();
        player = new JLabel(snapshot.playerSummaries[snapshot.currentPlayerIndex].name + "\'s turn");
        player.setFont(new Font("verdana", Font.BOLD, 25));

        openCardPanel.setBounds(500, 300, 250, 300);
        drawCardsPanel.setBounds(300, 300, 250, 300);
        otherPlayersPanel.setBounds(100, 0, 800, 1800);
        playerCardsPanel.setBounds(0, 0, 900, 200);

        setLayout(new BorderLayout());
        currentPlayer.setBounds(450, 200, 200, 50);
        currentPlayer.add(player);
        currentPlayer.setPreferredSize(new Dimension(400, 200));
        currentPlayer.setVisible(true);

        add(currentPlayer);
        add(openCardPanel);
        add(drawCardsPanel, BorderLayout.CENTER);
        add(otherPlayersPanel);
        add(playerCardsPanel, BorderLayout.SOUTH);
        add(activityLogPanel, BorderLayout.EAST);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    @Override
    public void showDisconnected() {
        setVisible(false);
    }

    @Override
    public void update(PlayerScreenController controller) {
        drawCardsPanel.update(controller.isMyTurn());
        openCardPanel.setOpenCard(controller.getOpenCardColour(), controller.getOpenCardSign());
        otherPlayersPanel.update(controller.getPlayerSummaries(), controller.isInAscendingOrder());
        playerCardsPanel.update(controller.getPlayerCards());
        player.setText(controller.getPlayerName() + "\'s turn");

    }
}
