package exercise1;

import javax.swing.*;

public class StudentInfoTest {
    public static void main(String[] args) {
        // Creating an instance of the StudentInfo frame
        StudentInfo studentInfo = new StudentInfo();

        // Setting up the frame properties
        studentInfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Ensures the application exits on close
        studentInfo.setResizable(false);  // Prevent resizing of the window
        studentInfo.setTitle("Student Application");  // Sets the title of the window
        studentInfo.setSize(770, 500);  // Sets the size of the window
        studentInfo.setVisible(true);  // Makes the window visible
        studentInfo.setLocationRelativeTo(null);  // Centers the window on the screen
    }
}
