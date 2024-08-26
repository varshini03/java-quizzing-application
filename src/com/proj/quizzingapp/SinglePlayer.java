package com.proj.quizzingapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SinglePlayer extends JFrame{
    String[][] correctAnswers = null;

    private JTextField[][] puzzleGrid;
    private JTextArea cluesArea;
    private JButton submitButton;

    public SinglePlayer() {
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Java Crossword Puzzle");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        // Create the puzzle grid
        puzzleGrid = new JTextField[30][30];
        JPanel puzzlePanel = new JPanel(new GridLayout(30, 30));
        // Represent black grids with a black background
        String[][] crosswordGrid = {

                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "S", "W", "I", "T", "C", "H", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", "N", " ", " ", " ", " ", " ", " ", "N", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", "P", " ", "E", " ", " ", "R", " ", " ", " ", "H", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", "U", " ", "X", " ", " ", "E", " ", " ", "N", "E", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", "B", " ", "T", "E", "S", "T", " ", " ", " ", "R", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", "L", " ", "L", " ", " ", "U", " ", " ", " ", "C", "O", "N", "T", "I", "N", "U", "E", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", "I", " ", "I", " ", " ", "R", " ", " ", " ", "T", " ", " ", " ", " ", " ", "N", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", "C", " ", "N", " ", " ", "N", " ", " ", " ", "A", " ", " ", " ", " ", " ", "E", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", "S", "T", "A", "T", "I", "C", " ", " ", " ", "N", " ", " ", " ", " ", " ", "X", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "C", " ", " ", " ", " ", " ", "T", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "A", "R", "R", "A", "Y", "S", " ", " ", " ", " ", " ", "D", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "O", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", "J", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "U", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", "F", "I", "N", "A", "L", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "B", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", "V", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "L", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {"O", "V", "E", "R", "L", "O", "A", "D", "I", "N", "G", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "E", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", "O", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", "I", " ", " ", " ", " ", " ", " ", " ", " ", " ", "J", " ", " ", " ", "B", " ", "F", "A", "L", "S", "E", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", "D", " ", " ", " ", " ", " ", " ", " ", " ", " ", "A", " ", " ", " ", "Y", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "V", " ", " ", " ", "T", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", "D", "E", "F", "A", "U", "L", "T", "E", " ", "M", "U", "L", "T", "I", "T", "H", "R", "E", "A", "D", "I", "N", "G"},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "C", " ", " ", " ", "C", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "O", "R", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "D", "A", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", "C", "O", "M", "M", "E", "N", "T", "S", " ", " ", " ", " ", "E", "N", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "D", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "O", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "M", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "}
        };

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (!crosswordGrid[i][j].equals(" ")) {
                    puzzleGrid[i][j] = new JTextField(2);
                    puzzleGrid[i][j].setHorizontalAlignment(JTextField.CENTER);
                    puzzleGrid[i][j].setEditable(true);
                    puzzleGrid[i][j].setBackground(Color.WHITE);
                    puzzleGrid[i][j].addMouseListener(new GridClickListener(i, j));
                    puzzlePanel.add(puzzleGrid[i][j]);
                } else {
                    // Represent black grids with a black background
                    JLabel blackLabel = new JLabel();
                    blackLabel.setBackground(Color.BLACK);
                    blackLabel.setOpaque(true);
                    puzzlePanel.add(blackLabel);
                }
            }
        }

        // Create the clues area
        cluesArea = new JTextArea("Across: \n 1. The ----- statement selects among multiple actions based on the possible values of an integer variable or experssion.\n 6. Keyword ----- creates an object of the class specified to the right of the keyword\n 7. Given the command java MyClass test, the first command-line argument is -----\n 8. The number used to refer to a particular element of an array is called the element's -----\n 10. The ----- statement, when executed in a repetition statement, skips the remaining statements in the loop body and proceeds with the next iteration of the loop\n 12. A(n) ----- variable represents classwide information that is shared by all the objects of the class.\n 18. List and tables of value can be stored in -----\n 21. Keyword ----- specifies that a variable is not modifiable.\n 22. In Java, it is possible to have several methods with the same name that each operate on different types or number of arguments. this feature is called method -----\n 24. When a Java program is compiled, the file produced by the compiler ends with the ----- file extension.\n 26. If the loop continuation condition in a for header is initially -----, the program does not execute the for statement's body\n 27. Instance variables of types char, byte, short, int, long, float and double are all given the value by ----- default.\n 29. ----- allows a Java program to perform multiple activities in parallel\n 30.----- are used to document a program and improve its readability\n" +
                "\n Down:\n 2. OOD takes advantage of ------- relationships, where new classes of objects are derived by absorbing characteristics of existing classes then addign unique characteristics of their own\n 3. Scanner method ----- reads characters until a newline character is encountered then returns those characters as a String\n 4. Each class declaration that begins with keyword ----- must be stored in a file that has exactly the same name as the class and ends with the ,java file-name extension\n 5. The ----- statement in a called method can be used to pass the value of an expression back to the calling method\n 9. Scanner method ----- returns a double value\n 11. The size, shape, color and weight of an object are considered ----- of the object\n 13. The command ----- from the JDK executes a Java application\n 14. The command ----- from the JDK compiles a Java program\n 15. The file produced by the Java compiler contains ----- that are executed by the Java Virtual Machine\n 16. Return type ----- indicates that a method will perform a task but will not return any information when it completes its task\n 17. java applications begin execution at method -----\n 19. The ----- of a declaration is the portion of a program that can refer to the entity in the declaration by name\n 20. The ----- method is called by the garbage collector just before it reclaims an object's memory\n 23. The do...while statement tests the loop continuation condition ----- executing the loop's body; therefore, the body always executes atleast once\n 25. The program that translates high-level language programs into machine language are called -----\n 28. An object of class ------ produces random numbers\n, 10, 20");
        cluesArea.setEditable(false);

        // Create the submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your logic to check answers and calculate marks
                int marks = calculateMarks();
                JOptionPane.showMessageDialog(SinglePlayer.this,
                        "Total Marks: " + marks,
                        "Results", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Add components to the frame
        add(puzzlePanel, BorderLayout.CENTER);
        add(new JScrollPane(cluesArea), BorderLayout.EAST);
        add(submitButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private int calculateMarks() {
        int marks = 0;
        correctAnswers = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "S", "W", "I", "T", "C", "H", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", "N", " ", " ", " ", " ", " ", " ", "N", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", "P", " ", "E", " ", " ", "R", " ", " ", " ", "H", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", "U", " ", "X", " ", " ", "E", " ", " ", "N", "E", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", "B", " ", "T", "E", "S", "T", " ", " ", " ", "R", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", "L", " ", "L", " ", " ", "U", " ", " ", " ", "C", "O", "N", "T", "I", "N", "U", "E", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", "I", " ", "I", " ", " ", "R", " ", " ", " ", "T", " ", " ", " ", " ", " ", "N", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", "C", " ", "N", " ", " ", "N", " ", " ", " ", "A", " ", " ", " ", " ", " ", "E", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", "S", "T", "A", "T", "I", "C", " ", " ", " ", "N", " ", " ", " ", " ", " ", "X", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "C", " ", " ", " ", " ", " ", "T", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "A", "R", "R", "A", "Y", "S", " ", " ", " ", " ", " ", "D", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "O", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", "J", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "U", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", "F", "I", "N", "A", "L", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "B", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", "V", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "L", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {"O", "V", "E", "R", "L", "O", "A", "D", "I", "N", "G", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "E", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", "O", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", "I", " ", " ", " ", " ", " ", " ", " ", " ", " ", "J", " ", " ", " ", "B", " ", "F", "A", "L", "S", "E", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", "D", " ", " ", " ", " ", " ", " ", " ", " ", " ", "A", " ", " ", " ", "Y", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "V", " ", " ", " ", "T", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", "D", "E", "F", "A", "U", "L", "T", "E", " ", "M", "U", "L", "T", "I", "T", "H", "R", "E", "A", "D", "I", "N", "G"},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "C", " ", " ", " ", "C", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "O", "R", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "D", "A", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", "C", "O", "M", "M", "E", "N", "T", "S", " ", " ", " ", " ", "E", "N", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "D", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "O", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "M", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "}
        };

        boolean[][] checkedDown = new boolean[29][30];
        boolean[][] checkedAcross = new boolean[29][30];

        for (int i = 0; i < 29; i++) {
            for (int j = 0; j < 30; j++) {
                if (!correctAnswers[i][j].equals(" ") && !checkedAcross[i][j]) {
                    boolean isCorrect = calculateDirectionScore(i, j, 1, 0, checkedAcross);
                    if (isCorrect) {
                        marks++;
                    }
                }

                if (!correctAnswers[i][j].equals(" ") && !checkedDown[i][j]) {
                    boolean isCorrect = calculateDirectionScore(i, j, 0, 1, checkedDown);
                    if (isCorrect) {
                        marks++;
                    }
                }
            }
        }

        return marks;
    }

    private boolean calculateDirectionScore(int row, int col, int rowIncrement, int colIncrement, boolean[][] checked) {
        boolean isCorrect = false;
        int currentRow = row;
        int currentCol = col;
        StringBuilder word = new StringBuilder();

        while (currentRow < 29 && currentCol < 30 && !correctAnswers[currentRow][currentCol].equals(" ")) {
            word.append(puzzleGrid[currentRow][currentCol].getText().toUpperCase());
            checked[currentRow][currentCol] = true; // Mark the cell as checked
            currentRow += rowIncrement;
            currentCol += colIncrement;
        }

        // Check if the word matches the correct answer
        if (word.toString().equals(correctAnswers[row][col])) {
            isCorrect = true;
        }

        return isCorrect;
    }
    private class GridClickListener extends MouseAdapter {
        private int row;
        private int col;

        public GridClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            String question = getQuestion(row, col);

            if(!question.equals(" ")) {
                JOptionPane.showMessageDialog(SinglePlayer.this,
                        "Question: " + question,
                        "Grid Information", JOptionPane.INFORMATION_MESSAGE);
            }

        }


        private String getQuestion(int row, int col) {
            // Replace this with your actual set of Java-related questions
            String[][] questions = {
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "1.", " ", "2.", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", "3.", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", "4.", " ", " ", " ", " ", "9.", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "6.", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", "7.", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "8.", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "11.", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", "10.", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "12.", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", "5.", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", "18.", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {"21.","17.", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "15.", " ", " ", " ", "16.", " ", "24.", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", "26.", " ", " ", " ", " ", " ", " ", " ", " ", "27.", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "28.", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", "29.", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "}
            };
            return questions[row][col];
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SinglePlayer().setVisible(true);
            }
        });
    }
}






