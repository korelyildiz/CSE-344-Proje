import java.sql.*;
import java.util.Scanner;

public class data {
    
	public boolean registerCorrect = false;
	public String userType = "";
	
	private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    
	public data() {
        
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

        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
	
	public void register(String username,String password,String userType) {
		
		try {
			
			String selectQuery = "SELECT * FROM user WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            // Sonuçları kontrol etme
            if (resultSet.next()) {
                registerCorrect = false;
            } else {
            	// Kullanıcıyı veritabanına ekleme
            	String insertQuery = "INSERT INTO user (username, password, usertype) VALUES (?, ?, ?)";
            	preparedStatement = connection.prepareStatement(insertQuery);
            	preparedStatement.setString(1, username);
            	preparedStatement.setString(2, password);
            	preparedStatement.setString(3, userType);
            	preparedStatement.executeUpdate();
            	registerCorrect = true;
            	System.out.println("Kayıt başarılı!");
            }
            
		} catch (SQLException e) { 
            e.printStackTrace();
        } 
	}
	
	public void login(String username,String password) {
		
		try {
			String selectQuery = "SELECT * FROM user WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            // Sonuçları kontrol etme
            if (resultSet.next()) {
                userType = resultSet.getString("usertype"); 
            } else {
                System.out.println("Giriş başarısız! Kullanıcı adı veya şifre hatalı.");
            }
            
		}catch (SQLException e) {
            e.printStackTrace();
        }

	}
	
	public void logout() {
		userType = "";
		registerCorrect = false;
	}
	
	public void close() {
		
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
