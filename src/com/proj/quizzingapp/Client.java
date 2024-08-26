package com.proj.quizzingapp;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;


public class Client {
    private static Socket socket;
    private static WaitingRoomGUI waitingRoomGUI;

    public static void main(String[] args) throws IOException {
        try {
            socket = new Socket("localhost", 8082);
        }catch(Exception e){
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Quizzing App");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(400, 300);

            JPanel mainPanel = new JPanel(new BorderLayout());
            JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 10));

            JButton singlePlayerButton = new JButton("Single-player");
            JButton multiPlayerButton = new JButton("Multi-player");
            JButton quitButton = new JButton("Quit");

            // Apply a modern look
            Font buttonFont = new Font("Arial", Font.BOLD, 16);
            singlePlayerButton.setFont(buttonFont);
            multiPlayerButton.setFont(buttonFont);
            quitButton.setFont(buttonFont);

            // Apply a different color scheme
            Color buttonBackground = new Color(67, 128, 166);
            Color buttonForeground = Color.WHITE;

            singlePlayerButton.setBackground(buttonBackground);
            singlePlayerButton.setForeground(buttonForeground);

            multiPlayerButton.setBackground(new Color(61, 190, 115));
            multiPlayerButton.setForeground(buttonForeground);

            quitButton.setBackground(new Color(210, 96, 83));
            quitButton.setForeground(buttonForeground);

            // Set tool tips for buttons
            singlePlayerButton.setToolTipText("Play in single-player mode");
            multiPlayerButton.setToolTipText("Play in multi-player mode");
            quitButton.setToolTipText("Quit the application");

            buttonPanel.add(singlePlayerButton);
            buttonPanel.add(multiPlayerButton);
            buttonPanel.add(quitButton);

            mainPanel.add(buttonPanel, BorderLayout.CENTER);

            singlePlayerButton.addActionListener(e -> {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new SinglePlayer().setVisible(true);
                    }
                });
            });


            multiPlayerButton.addActionListener(e -> {
                String roomName = JOptionPane.showInputDialog(mainFrame, "Enter Game Room:");
                //gameRoom.add("client");

                if (roomName != null && !roomName.isEmpty()) {
                    mainFrame.setVisible(false);
                    try (BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                         PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
                        // Your code for handling multi-player functionality goes here
                        writer.println(roomName);
                        int numQuestions = Integer.parseInt(serverReader.readLine());
                        //int numClients = Integer.parseInt(serverReader.readLine());
                        Scanner s = new Scanner(System.in);
                        for (int i = 0; i < numQuestions; i++) {
                            String questionText = serverReader.readLine();
                            String optionA = serverReader.readLine();
                            String optionB = serverReader.readLine();
                            String optionC = serverReader.readLine();
                            String optionD = serverReader.readLine();
                            String correctAnswer = serverReader.readLine();
                            System.out.println("Question: " + questionText);
                            System.out.println("A. " + optionA);
                            System.out.println("B. " + optionB);
                            System.out.println("C. " + optionC);
                            System.out.println("D. " + optionD);

                            String userAnswer = s.nextLine();
                            // Send the user's answer to the server
                            if(correctAnswer.equalsIgnoreCase(userAnswer)) writer.println("y");
                            else writer.println("n");
                        }

                        // Notify the server that the client has finished the quiz
                        //ClientHandler.notifyQuizFinish();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                       /* if (numClients == 1) {
                            // Create an instance of WaitingRoomGUI
                            waitingRoomGUI = new WaitingRoomGUI();
                            // Show the waiting room GUI
                            waitingRoomGUI.showWaitingRoom();
                        }else{
                            waitingRoomGUI = new WaitingRoomGUI();
                        }
                        if (numClients == 2) {
                            waitingRoomGUI.hideWaitingRoom();
                            ClientHandler.notifyQuizStart();
                            // Move to starting page with the first question
                            // You need to implement this based on your logic
                            // For example, display the first question and options
                            // and handle the logic for moving to the next question
                            // startGame();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }*/
                }
            });

            quitButton.addActionListener(e -> System.exit(0));

            // Add padding to the main panel
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            mainFrame.add(mainPanel);
            mainFrame.setVisible(true);
        });


    }
}
class QuestionForm extends JFrame {
    private String userAnswer;
    private final CountDownLatch answerLatch = new CountDownLatch(1);

    public QuestionForm(JFrame parent, String question, String optionA, String optionB, String optionC, String optionD) {
        super("Question Form");
        System.out.println(question + " " + optionA);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JPanel mainPanel = new JPanel(new GridLayout(6,1));

        JLabel questionLabel = new JLabel(question);
        JRadioButton optionARadio = new JRadioButton(optionA);
        JRadioButton optionBRadio = new JRadioButton(optionB);
        JRadioButton optionCRadio = new JRadioButton(optionC);
        JRadioButton optionDRadio = new JRadioButton(optionD);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(optionARadio);
        buttonGroup.add(optionBRadio);
        buttonGroup.add(optionCRadio);
        buttonGroup.add(optionDRadio);

        // Create a "Next" button
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            if (optionARadio.isSelected()) userAnswer = optionA;
            else if (optionBRadio.isSelected()) userAnswer = optionB;
            else if (optionCRadio.isSelected()) userAnswer = optionC;
            else if (optionDRadio.isSelected()) userAnswer = optionD;

            answerLatch.countDown();
            setVisible(false);
        });

        // Add components to the main panel
        mainPanel.add(questionLabel);
        mainPanel.add(optionARadio);
        mainPanel.add(optionBRadio);
        mainPanel.add(optionCRadio);
        mainPanel.add(optionDRadio);
        mainPanel.add(nextButton);

        // Add padding to the main panel
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(mainPanel);
        mainPanel.setVisible(true);
        questionLabel.setVisible(true);
        optionARadio.setVisible(true);
        optionBRadio.setVisible(true);
        optionCRadio.setVisible(true);
        optionDRadio.setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500, 500);
    }

    public void waitForAnswer() {
        try {
            answerLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getUserAnswer() {
        return userAnswer;
    }
}
