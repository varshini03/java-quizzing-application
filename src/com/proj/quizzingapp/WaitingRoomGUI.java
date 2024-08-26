package com.proj.quizzingapp;


import javax.swing.*;
import java.awt.*;

public class WaitingRoomGUI {
    private JFrame waitingFrame;
    private JPanel waitingPanel;

    public WaitingRoomGUI() {
        waitingFrame = new JFrame("Waiting Room");
        waitingFrame.setSize(400, 200);
        waitingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        waitingFrame.setLayout(new BorderLayout());

        waitingPanel = new JPanel();
        waitingPanel.setLayout(new GridLayout(3, 1));

        JLabel waitingLabel = new JLabel("Waiting for the opponent to join...");
        waitingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //JButton cancelBtn = new JButton("Cancel");
        //cancelBtn.addActionListener(e -> System.exit(0));

        waitingPanel.add(waitingLabel);
        waitingPanel.add(new JLabel()); // Empty label for spacing
        //waitingPanel.add(cancelBtn);

        waitingFrame.add(waitingPanel);

    }

    public void showWaitingRoom() {

        waitingFrame.setVisible(true);
    }

    public void hideWaitingRoom() {
        waitingFrame.dispose();
    }
}

