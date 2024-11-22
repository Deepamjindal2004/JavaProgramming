package exercise1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuForm extends JFrame {

    public MainMenuForm() {
        setTitle("Main Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create buttons
        JButton playerButton = new JButton("Player Form");
        JButton gameButton = new JButton("Game Form");
        JButton reportButton = new JButton("Report Form");

        // Add action listeners for the buttons
        playerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlayerForm().setVisible(true);
                dispose();  // Close the main menu after opening the form
            }
        });

        gameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameForm().setVisible(true);
                dispose();
            }
        });

        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReportForm().setVisible(true);
                dispose();
            }
        });

        // Set layout and add buttons
        setLayout(new GridLayout(3, 1));
        add(playerButton);
        add(gameButton);
        add(reportButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenuForm().setVisible(true); // Show the main menu
        });
    }
}
