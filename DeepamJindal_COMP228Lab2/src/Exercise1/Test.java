package Exercise1;

import javax.swing.JOptionPane;
import java.util.Random;
import java.text.NumberFormat;

public class Test {
    // Declare questions and answers
    String[] questions = {"""
                            In which year did Sun Microsystems fund an internal corporate
                            research project led by James Gosling, which resulted in a
                            C++ -based object-oriented programming language that Sun called Java?
                            
                            A. 1999
                            B. 1998
                            C. 1991
                            D. 1990
                            """,
            """
                            Which of the following editions of Java contains the capabilities
                            needed to develop desktop and server applications?
                            
                            A. Java Enterprise Edition (Java EE)
                            B. Java Enterprise Edition 2 (Java 2 EE)
                            C. Java Standard Edition 8 (Java SE 8)
                            D. Java Micro Edition (Java ME)
                            """,
            """
                            What does an instance variable describe?
                            
                            A. Behaviour of an object
                            B. Height of an object
                            C. Measurement of an object
                            D. Properties of an object
                            """,
            """
                            When a method that specifies a return type other than void is called, 
                            what must the method do when it completes its task?
                            
                            A. It must return one result only to the statement one line after the line from which it was called.
                            B. It must return more than just one result to its caller.
                            C. It must return one result to the statement immediately after the method body.
                            D. It must return a result exactly to the point from which it was called.
                            """,
            """
                            Which of the following Java program units defines a group of related objects?
                            
                            A. Java Class
                            B. Java method
                            C. Java
                            D. Java default constructor
                            """
    };

    String[] choices = {"A", "B", "C", "D"};
    String[] correctAnswer = {"C", "C", "D", "D", "A"};
    String[] userSelects = new String[correctAnswer.length];
    String userSelect;
    boolean check;
    int correctCount = 0;
    int wrongCount = 0;
    float correctRate;

    // Methods
    public void simulateQuestion() {
        for (int i = 0; i < questions.length; i++) {
            // Define user select
            userSelect = (String) JOptionPane.showInputDialog(null,
                    questions[i], "Test",
                    JOptionPane.QUESTION_MESSAGE, null,
                    choices, choices[0]);

            // Collecting user input
            userSelects[i] = userSelect;

            // Check answer
            checkAnswer(i);

            // Generate message
            JOptionPane.showMessageDialog(null,
                    generateMessage(i),
                    "Message",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void checkAnswer(int i) {
        if (userSelects[i].equals(correctAnswer[i])) {
            check = true;
            correctCount += 1;
        } else {
            check = false;
            wrongCount += 1;
        }
    }

    public String generateMessage(int i) {
        Random random = new Random();
        if (check) {
            switch (random.nextInt(4)) {
                case 1 -> {
                    return "Excellent!";
                }
                case 2 -> {
                    return "Good!";
                }
                case 3 -> {
                    return "Keep up the good work!";
                }
                default -> {
                    return "Nice work!";
                }
            }
        } else {
            switch (random.nextInt(4)) {
                case 1 -> {
                    return "No. Please try again!";
                }
                case 2 -> {
                    return "Wrong. Try once more!";
                }
                case 3 -> {
                    return "Don't give up!";
                }
                default -> {
                    return "No. Keep Trying.";
                }
            }
        }
    }

    public void inputAnswer() {
        // Show percentage
        NumberFormat showPercent = NumberFormat.getPercentInstance();
        simulateQuestion();
        correctRate = (float) correctCount / questions.length; // Process to avoid zero division error!
        String message = "Your correct count is: " + correctCount + "\n" +
                "Your wrong count is: " + wrongCount + "\n" +
                "Your correct rate is: " + showPercent.format(correctRate);
        JOptionPane.showMessageDialog(null, message);
    }
}
