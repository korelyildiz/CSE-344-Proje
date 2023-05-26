
import java.sql.*;
import java.util.Scanner;

public class data {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
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

            // Tablo oluşturma (user tablosu)
            Statement statement = connection.createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS user (username TEXT, password TEXT, usertype TEXT)";
            statement.execute(createTableQuery);

            // Kullanıcıdan giriş bilgilerini alma
            Scanner scanner = new Scanner(System.in);
            System.out.print("Kullanıcı adı: ");
            String username = scanner.nextLine();
            System.out.print("Şifre: ");
            String password = scanner.nextLine();
            System.out.print("Kullanıcı tipi: ");
            String userType = scanner.nextLine();

            // Kullanıcıyı veritabanına ekleme
            String insertQuery = "INSERT INTO user (username, password, usertype) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, userType);
            preparedStatement.executeUpdate();

            System.out.println("Kayıt başarıyla tamamlandı!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Kaynakları serbest bırakma
            try {
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
