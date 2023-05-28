import java.sql.*;
import java.util.Scanner;

public class login {
    
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.sqlite.JDBC");
            // SQLite veritabanı sürücüsü yüklendi

            // Veritabanı bağlantısı yapma veya diğer işlemler devam eder...
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Sürücü yüklenirken bir hata oluştu
        }
        try {
            // Veritabanı bağlantısı oluşturma
            connection = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");

            // Kullanıcıdan giriş bilgilerini alma
            Scanner scanner = new Scanner(System.in);
            System.out.print("Kullanıcı adı: ");
            String username = scanner.nextLine();
            System.out.print("Şifre: ");
            String password = scanner.nextLine();

            // Kullanıcıyı veritabanında arama
            String selectQuery = "SELECT * FROM user WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            // Sonuçları kontrol etme
            if (resultSet.next()) {
                String userType = resultSet.getString("usertype");
                System.out.println("Giriş başarılı! Kullanıcı tipi: " + userType);
            } else {
                System.out.println("Giriş başarısız! Kullanıcı adı veya şifre hatalı.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Kaynakları serbest bırakma
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
