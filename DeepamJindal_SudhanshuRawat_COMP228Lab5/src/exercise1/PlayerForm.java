package exercise1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PlayerForm extends JFrame {

    private JTextField firstNameField, lastNameField, addressField, postalCodeField, provinceField, phoneField, playerIdField;
    private JButton insertButton, updateButton, backButton, loadButton;

    public PlayerForm() {
        setTitle("Player Information");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create labels and text fields for player information
        JLabel playerIdLabel = new JLabel("Player ID:");
        playerIdField = new JTextField(20);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(20);
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(20);
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);
        JLabel postalCodeLabel = new JLabel("Postal Code:");
        postalCodeField = new JTextField(20);
        JLabel provinceLabel = new JLabel("Province:");
        provinceField = new JTextField(20);
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField(20);

        insertButton = new JButton("Insert Player");
        updateButton = new JButton("Update Player");
        backButton = new JButton("Back to Main Menu");
        loadButton = new JButton("Load Player Info");

        // Layout setup
        setLayout(new GridLayout(10, 2));
        add(playerIdLabel); add(playerIdField);
        add(firstNameLabel); add(firstNameField);
        add(lastNameLabel); add(lastNameField);
        add(addressLabel); add(addressField);
        add(postalCodeLabel); add(postalCodeField);
        add(provinceLabel); add(provinceField);
        add(phoneLabel); add(phoneField);
        add(insertButton);
        add(updateButton);
        add(loadButton);
        add(backButton);

        // Insert Button Action
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Insert player without requiring player_id
                insertPlayerInfo();
            }
        });

        // Update Button Action
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update player info with player_id
                updatePlayerInfo();
            }
        });

        // Load Button Action
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Load player info based on player_id
                String playerIdText = playerIdField.getText();
                if (!playerIdText.isEmpty()) {
                    try {
                        int playerId = Integer.parseInt(playerIdText);
                        loadPlayerInfo(playerId);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(PlayerForm.this, "Please enter a valid player ID.");
                    }
                } else {
                    JOptionPane.showMessageDialog(PlayerForm.this, "Please enter a player ID to load.");
                }
            }
        });

        // Back Button Action
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainMenuForm().setVisible(true);
            }
        });
    }

    // Method to insert player information into the database (no need for player_id)
    private void insertPlayerInfo() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String insertQuery = "INSERT INTO Deepam_Jindal_player (first_name, last_name, address, postal_code, province, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, firstNameField.getText());
            statement.setString(2, lastNameField.getText());
            statement.setString(3, addressField.getText());
            statement.setString(4, postalCodeField.getText());
            statement.setString(5, provinceField.getText());
            statement.setString(6, phoneField.getText());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Player information inserted successfully!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error inserting player info: " + ex.getMessage());
        }
    }

    // Method to update player information based on player_id (ID is mandatory)
    private void updatePlayerInfo() {
        String playerId = playerIdField.getText();
        if (playerId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Player ID to update.");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            String updateQuery = "UPDATE Deepam_Jindal_player SET first_name = ?, last_name = ?, address = ?, postal_code = ?, province = ?, phone_number = ? WHERE player_id = ?";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, firstNameField.getText());
            statement.setString(2, lastNameField.getText());
            statement.setString(3, addressField.getText());
            statement.setString(4, postalCodeField.getText());
            statement.setString(5, provinceField.getText());
            statement.setString(6, phoneField.getText());
            statement.setInt(7, Integer.parseInt(playerId));

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Player information updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "No player found with that ID.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating player info: " + ex.getMessage());
        }
    }

    // Method to load existing player information based on player ID
    public void loadPlayerInfo(int playerId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String selectQuery = "SELECT * FROM Deepam_Jindal_player WHERE player_id = ?";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setInt(1, playerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                firstNameField.setText(resultSet.getString("first_name"));
                lastNameField.setText(resultSet.getString("last_name"));
                addressField.setText(resultSet.getString("address"));
                postalCodeField.setText(resultSet.getString("postal_code"));
                provinceField.setText(resultSet.getString("province"));
                phoneField.setText(resultSet.getString("phone_number"));
                playerIdField.setText(String.valueOf(playerId));  // Display the player ID
            } else {
                JOptionPane.showMessageDialog(this, "No player found with that ID.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading player info: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PlayerForm().setVisible(true);
        });
    }
}
