package exercise1;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class ReportForm extends JFrame {

    private JTable reportTable;
    private DefaultTableModel tableModel;

    public ReportForm() {
        setTitle("Player and Game Report");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize JTable and set up the table model
        tableModel = new DefaultTableModel();
        reportTable = new JTable(tableModel);

        // Define column names for the report
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Game Title");
        tableModel.addColumn("Play Date");

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(reportTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load data into the table
        loadReportData();
    }

    private void loadReportData() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT p.first_name, p.last_name, g.game_title, pag.play_date " +
                    "FROM Deepam_Jindal_player p " +
                    "JOIN Deepam_Jindal_player_and_game pag ON p.player_id = pag.player_id " +
                    "JOIN Deepam_Jindal_game g ON pag.game_id = g.game_id " +
                    "ORDER BY pag.play_date";

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Clear any previous data in the table
            tableModel.setRowCount(0);

            // Process the result set and populate the table
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gameTitle = resultSet.getString("game_title");
                Date playDate = resultSet.getDate("play_date");

                // Add a new row with the player and game information
                tableModel.addRow(new Object[]{firstName, lastName, gameTitle, playDate});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading report: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ReportForm().setVisible(true);
        });
    }
}
