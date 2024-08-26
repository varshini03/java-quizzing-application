package com.proj.quizzingapp;

public class Question {
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private String questionType;

    public Question() {
        // Default constructor
        super();
    }
    /*public Question(String text, String correctAnswer) {
        this.question = text;
        this.correctAnswer = correctAnswer;
    }*/

    public Question(String question, String optionA, String optionB, String optionC, String optionD, String correctOption, String questionType) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctOption;
        this.questionType = questionType;
    }
    public String getQuestion() {
        return question;
    }
    public String getOptionA() {
        return optionA;
    }
    public String getOptionB() {
        return optionB;
    }
    public String getOptionC() {
        return optionC;
    }
    public String getOptionD() {
        return optionD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public String getQuestionType() {
        return questionType;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }
    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }
    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }
    public void setOptionD(String optionD) {
        this.optionD = optionC;
    }
    public void setCorrectOption(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

}
