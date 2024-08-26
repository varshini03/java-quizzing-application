package com.proj.quizzingapp;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnector {
    static Connection conn = null;
    static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    static String user = "system";
    static String pwd = "orcl";

    public static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            if (conn != null) return conn;
            else throw new RuntimeException("Error connecting to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // to close connection
    public static void closeConnection(Connection c) throws SQLException {
        try{
            if(c!=null && !c.isClosed()){
                c.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // fetch questions from database
    public static ArrayList<Question> getQuizQuestions() {
        ArrayList<Question> questions = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM quiz_questions ORDER BY DBMS_RANDOM.value FETCH NEXT 5 ROWS ONLY";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Question question = new Question();
                        question.setQuestion(resultSet.getString("QUESTION"));
                        question.setOptionA(resultSet.getString("OPTIONA"));
                        question.setOptionB(resultSet.getString("OPTIONB"));
                        question.setOptionC(resultSet.getString("OPTIONC"));
                        question.setOptionD(resultSet.getString("OPTIOND"));
                        question.setCorrectOption(resultSet.getString("CORRECTANSWER"));
                        question.setQuestionType(resultSet.getString("QUESTIONTYPE"));

                        questions.add(question);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching quiz questions from the database.", e);
        }

        return questions;
    }
}


