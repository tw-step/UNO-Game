package com.step.uno.client.screen;

import com.step.uno.client.controller.GameClientController;
import com.step.uno.client.controller.PlayerScreenController;
import com.step.uno.client.view.JoinGameView;
import com.step.uno.client.view.PlayerView;
import com.step.uno.messages.Snapshot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinGameScreen extends JFrame implements JoinGameView {

    private GameClientController controller;

    //UI Components
    JButton join = new JButton("   Join   ");
    JLabel master = new JLabel("Master Name :");
    JLabel playerName = new JLabel("Your Name :");
    JTextField masterName = new JTextField(15);
    JTextField name = new JTextField(15);

    public JoinGameScreen(GameClientController controller) {
        this.controller = controller;
        Panel panel = new Panel();
        masterName.setText("localhost");
        panel.add(master).setFont(new Font("verdana", Font.BOLD, 22));
        panel.add(masterName).setBounds(100, 100, 100, 100);
        panel.add(playerName).setFont(new Font("verdana", Font.BOLD, 22));
        name.setFont(new Font("verdana", Font.BOLD, 18));
        name.setPreferredSize(new Dimension(20, 30));
        masterName.setPreferredSize(new Dimension(20, 30));
        masterName.setFont(new Font("verdana", Font.BOLD, 18));
        panel.add(name);
        join.setFont(new Font("verdana", Font.BOLD, 22));
        panel.add(join).setSize(100, 500);

        setSize(330, 370);
        setLocationRelativeTo(null);
        panel.setBackground(new Color(225, 224, 229));
        add(panel);
    }

    @Override
    public PlayerView switchToPlayerView(PlayerScreenController screenController, Snapshot snapshot) {
        PlayerView view = new PlayerScreen(screenController, snapshot);
        setVisible(false);
        return view;
    }

    public void showScreen() {
        controller.bindView(this);
        join.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.join(masterName.getText(), name.getText());
                join.setEnabled(false);
                join.setText("Please wait...");
            }
        });
        setVisible(true);
    }
}
