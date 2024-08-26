package com.proj.quizzingapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static com.proj.quizzingapp.ClientHandler.gameRoom;

public class GameRoomServer {
    private static final int NUM_CLIENTS = 2;
    public static CountDownLatch quizStartLatch = new CountDownLatch(NUM_CLIENTS);
    public static int[] scores;
    private static int cnt = 0;
    public static volatile CountDownLatch quizFinishLatch = new CountDownLatch(NUM_CLIENTS);


    public static void main(String[] args){
        scores = new int[NUM_CLIENTS];

        ArrayList<Question> questions = DatabaseConnector.getQuizQuestions();

        try (ServerSocket serverSocket = new ServerSocket(8082)) {


            System.out.println("Server is running and waiting for connections...");

            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("New connection: " + socket);
                cnt++;
                quizStartLatch.countDown();

                ClientHandler clientHandler = new ClientHandler(socket,questions, cnt );
                clientHandler.setQuestions(questions);

                new Thread(clientHandler).start();

                //System.out.println(quizFinishLatch.getCount());
                //quizFinishLatch.await();
                //System.out.println("hi");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
