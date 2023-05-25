import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class login {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/project344";
        String username = "root";
        String password = "123456";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.print("Username: ");
            String usernameInput = scanner.nextLine();
            System.out.print("Password: ");
            String passwordInput = scanner.nextLine();

            if (!usernameInput.isEmpty() && !passwordInput.isEmpty()) {
                String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, usernameInput);
                    statement.setString(2, passwordInput);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        System.out.println("Login successful.");
                    } else {
                        System.out.println("Error: Invalid username or password.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error on login: " + e.getMessage());
                }
            } else {
                System.out.println("Error: Please enter both username and password.");
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }
}

