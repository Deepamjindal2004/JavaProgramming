package exercise1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class GameForm extends JFrame {

    private JComboBox<String> playerComboBox;
    private JTextField gameNameField;

    public GameForm() {
        setTitle("Game Information");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Fetch players from the database
        DefaultComboBoxModel<String> playerModel = new DefaultComboBoxModel<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT player_id, first_name, last_name FROM Deepam_Jindal_player";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                playerModel.addElement(playerId + ": " + firstName + " " + lastName);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading player data: " + e.getMessage());
        }

        // Create components for the form
        JLabel playerLabel = new JLabel("Select Player:");
        playerComboBox = new JComboBox<>(playerModel);

        JLabel gameNameLabel = new JLabel("Game Name:");
        gameNameField = new JTextField(20);

        JButton insertButton = new JButton("Insert Game");
        JButton backButton = new JButton("Back to Main Menu");

        // Layout setup
        setLayout(new GridLayout(4, 2));
        add(playerLabel);
        add(playerComboBox);
        add(gameNameLabel);
        add(gameNameField);
        add(insertButton);
        add(backButton);

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to insert game information into the database
                insertGameInfo();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close this form and go back to the main menu
                dispose();
                new MainMenuForm().setVisible(true);
            }
        });
    }

    private void insertGameInfo() {
        // Get player information
        int selectedPlayerIndex = playerComboBox.getSelectedIndex();
        if (selectedPlayerIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select a player.");
            return;
        }

        String selectedPlayer = (String) playerComboBox.getSelectedItem();
        int playerId = Integer.parseInt(selectedPlayer.split(":")[0].trim());
        String gameName = gameNameField.getText().trim();

        if (gameName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a game name.");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Check if the game exists
            String selectGameQuery = "SELECT game_id FROM Deepam_Jindal_game WHERE game_title = ?";
            PreparedStatement selectGameStatement = connection.prepareStatement(selectGameQuery);
            selectGameStatement.setString(1, gameName);
            ResultSet resultSet = selectGameStatement.executeQuery();

            int gameId = -1;
            if (resultSet.next()) {
                // Game already exists, retrieve the game_id
                gameId = resultSet.getInt("game_id");
            } else {
                // Insert new game title if not found
                String insertGameQuery = "INSERT INTO Deepam_Jindal_game (game_title) VALUES (?)";
                PreparedStatement gameStatement = connection.prepareStatement(insertGameQuery, Statement.RETURN_GENERATED_KEYS);
                gameStatement.setString(1, gameName);
                int rowsAffected = gameStatement.executeUpdate();

                if (rowsAffected > 0) {
                    ResultSet generatedKeys = gameStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        gameId = generatedKeys.getInt(1);
                    }
                }
            }

            if (gameId != -1) {
                // Check if the player-game combination already exists
                String checkPlayerGameQuery = "SELECT * FROM Deepam_Jindal_player_and_game WHERE player_id = ? AND game_id = ?";
                PreparedStatement checkPlayerGameStatement = connection.prepareStatement(checkPlayerGameQuery);
                checkPlayerGameStatement.setInt(1, playerId);
                checkPlayerGameStatement.setInt(2, gameId);
                ResultSet playerGameResultSet = checkPlayerGameStatement.executeQuery();

                if (!playerGameResultSet.next()) {
                    // No existing record, proceed with the insert
                    String insertPlayerGameQuery = "INSERT INTO Deepam_Jindal_player_and_game (player_id, game_id, play_date) VALUES (?, ?, ?)";
                    PreparedStatement insertPlayerGameStatement = connection.prepareStatement(insertPlayerGameQuery);
                    insertPlayerGameStatement.setInt(1, playerId);
                    insertPlayerGameStatement.setInt(2, gameId);
                    insertPlayerGameStatement.setDate(3, new java.sql.Date(System.currentTimeMillis()));
                    insertPlayerGameStatement.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Game information inserted successfully!");
                } else {
                    // Player-game combination already exists
                    JOptionPane.showMessageDialog(this, "Player has already played this game.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error inserting game info: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameForm().setVisible(true);
        });
    }
}
