package exercise1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD"; // Remote Hostname
    private static final String USER = "COMP228_F24_soh_11"; // Replace with your username
    private static final String PASSWORD = "deepam_sudhanshu"; // Replace with your new password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}