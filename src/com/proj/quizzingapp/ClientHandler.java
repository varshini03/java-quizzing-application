package com.proj.quizzingapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static com.proj.quizzingapp.GameRoomServer.*;

public class ClientHandler implements Runnable{
    private Socket socket;
    static WaitingRoomGUI w;
    static ArrayList<ClientHandler> gameRoom = new ArrayList<>();
    private PrintWriter writer;
    private BufferedReader reader;
    private ArrayList<Question> questions;
    private static int connectedClientsCount = 0;
    private ScheduledExecutorService questionTimer;
    private int currentQuestionIndex;
    private long timeRemaining;
    private static boolean quizStarted = false;
    private int score = 0;
    private int id;
    //extern CountDownLatch quizFinishLatch;


    //private static CountDownLatch quizStartLatch = new CountDownLatch(2);
    //private static CountDownLatch quizFinishLatch = new CountDownLatch(2);

    public ClientHandler(Socket socket, ArrayList<Question> q, int id) {
        this.socket = socket;
        this.questions = q;
        this.id = id;
        //this.quizStartLatch = quizStartLatch;
        //this.quizFinishLatch = quizFinishLatch;
        connectedClientsCount++;
        questionTimer = Executors.newScheduledThreadPool(1);
        currentQuestionIndex = 0;
        timeRemaining = 0;

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public synchronized void notifyQuizFinish() {
        //GameRoomServer.quizFinishLatch.countDown();
        //GameRoomServer.decrement();
        GameRoomServer.quizFinishLatch.countDown();
        //System.out.println(GameRoomServer.quizFinishLatch.getCount());
        if(quizFinishLatch.getCount() == 0){
            System.out.println("client 1: " + scores[0]);
            System.out.println("client 2: " + scores[1]);
            if (scores[0] > scores[1]) System.out.println("client 1 won!");
            else if (scores[0] < scores[1]) System.out.println("client 2 won!");
            else System.out.println("its a tie.");
        }
        //Thread.currentThread().interrupt();
    }


        @Override
        public void run () {
        try {
            String roomName = reader.readLine();
            gameRoom.add(this);
            System.out.println(roomName);
            writer.println(questions.size());

            // Send the number of connected clients back to the client
            //writer.println(connectedClientsCount);


            /*synchronized (lock) {
                while (connectedClientsCount < 2) {
                    lock.wait();
                }
            }*/
            //GameRoomServer.quizStartLatch.await();
            //Socket socket = new Socket("localhost", 8082);
            //w = new WaitingRoomGUI();
            //w.showWaitingRoom();
            if (gameRoom.size() == 2) {
                //w.hideWaitingRoom();
                //startQuiz();
                for (ClientHandler c : gameRoom) {
                    new Thread(() -> {
                        c.startQuiz();
                    }).start();
                }
            }

            quizFinishLatch.await();
            // Wait for all clients to finish the quiz
            //notifyQuizFinish();


            /*if(Thread.currentThread().isInterrupted()){
                return;
            }*/



            /*writer.println("START_QUIZ");
            startQuiz();*/
            // Your existing code logic goes here

        } catch (IOException e) {
            //e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        }

    private void startQuiz() {
        System.out.println("starting");
        for (Question question : questions) {
            writer.println(question.getQuestion());
            writer.println(question.getOptionA());
            writer.println(question.getOptionB());
            writer.println(question.getOptionC());
            writer.println(question.getOptionD());
            writer.println(question.getCorrectAnswer());
            try {

                String userAnswer = reader.readLine();
                //System.out.println(userAnswer);
                if(userAnswer.equals("y")) ++scores[id-1];




                // Compare userAnswer with the correct answer and update logic accordingly
                // For example: if (userAnswer.equals(question.getCorrectAnswer())) { /* Correct */ }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Your logic to start the quiz goes here
            // For example, initialize the first question and start the timer
        }
        //System.out.println("hi");
        System.out.println("Quiz finished for client: " + socket);
        notifyQuizFinish();

    }
    /*public static void notifyQuizStart() {
        synchronized (lock) {
            System.out.println("hi");
            quizStarted = true;
            lock.notifyAll();
        }
    }*/

    public Socket getSocket() {
        return socket;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
