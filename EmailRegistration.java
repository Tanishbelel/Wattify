import java.sql.*;
import java.util.Scanner;

public class EmailRegistration {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";  // Use your actual database name
        String username = "root";  // Replace with your database username
        String password = "Tanish@12";  // Replace with your database password

        // Connect to the database
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Connected to the database successfully!");

            // Continuously ask for email input
            while (true) {
                System.out.println("Enter an email (or type 'exit' to quit): ");
                String email = scanner.nextLine();

                // Exit condition
                if (email.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    break;
                }

                // Check if the email is already registered
                String checkEmailQuery = "SELECT COUNT(*) FROM users WHERE email = ?";
                try (PreparedStatement stmt = connection.prepareStatement(checkEmailQuery)) {
                    stmt.setString(1, email);
                    ResultSet rs = stmt.executeQuery();
                    rs.next();
                    int count = rs.getInt(1);

                    if (count > 0) {
                        System.out.println("Email is already registered.");
                    } else {
                        // If not registered, add the email to the database
                        String insertQuery = "INSERT INTO users (email) VALUES (?)";
                        try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                            insertStmt.setString(1, email);
                            int rowsAffected = insertStmt.executeUpdate();
                            if (rowsAffected > 0) {
                                System.out.println("Email successfully registered.");
                            } else {
                                System.out.println("Error registering email.");
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error connecting to the database.");
        }

        scanner.close();
    }
}
