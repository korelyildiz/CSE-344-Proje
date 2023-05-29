import java.sql.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class data {
    
	public boolean registerCorrect = false;
	public String userType = ""; //info for logged in account	
	public String userName = ""; //info for logged in account
	public String Category = "";
	public List<String> bound = new ArrayList<>(); //info for logged in account
	
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
        	
        	connection = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");

            // Tablo oluşturma (user tablosu)
            Statement statement = connection.createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS user (username TEXT, password TEXT, usertype TEXT, boundaccount TEXT, category TEXT,"
            		+ "level1 INT, level2 INT, level3 INT, level1_2 INT, level2_2 INT, level3_2 INT, level1_3 INT, level2_3 INT, level3_3 INT)";
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
                userName = resultSet.getString("username");
                
                if(userType.contentEquals("Authorized User")) {
                	getBound(userName);
                }else {
                	getCategory(userName);
                }
                
                
            } else {
                System.out.println("Giriş başarısız! Kullanıcı adı veya şifre hatalı.");
            }
            
		}catch (SQLException e) {
            e.printStackTrace();
        }

	}
	
	public void bindAccount(String username,String password) {
		try {
            
			String selectQuery = "SELECT * FROM user WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            
            // Sonuçları kontrol etme
            if (resultSet.next()) {
            	String user = resultSet.getString("username"); 
                bound.add(user);
            	
            	if(resultSet.getString("usertype").contentEquals("Unauthorized User")) {
                	String insertQuery = "UPDATE user SET boundaccount = ? WHERE username = ?";
                    preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, String.join(",", bound));
                    preparedStatement.setString(2,userName);
                    preparedStatement.executeUpdate();
                                       
                }else {
                	System.out.println("Can't bind Authorized User");
                }
                
            } else {
                System.out.println("Giriş başarısız! Kullanıcı adı veya şifre hatalı.");
            }
            
		}catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	
	public void removeAccount(String username) {
		bound.remove(username);
		try {
			String insertQuery = "UPDATE user SET boundaccount = ? WHERE username = ?";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, String.join(",", bound));
            preparedStatement.setString(2,userName);
            preparedStatement.executeUpdate();
		
		}catch(SQLException e) {
            e.printStackTrace();
        }
	}
	
	
	public void getBound(String username) {
		try {
			String selectQuery = "SELECT * FROM user WHERE username = ?";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
			
            if (resultSet.next()) {
            	String columnValue = resultSet.getString("boundaccount");
                if (columnValue != null) {
                    // Split the retrieved string and add each element to the list
                    String[] elements = columnValue.split(",");
                    bound.addAll(Arrays.asList(elements));
                }
            }
			
		}catch(SQLException e) {
            e.printStackTrace();
        }
		
	}
	
	public boolean Bound(String username) {
		if(bound.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public void getCategory(String username) {
		try {
			String selectQuery = "SELECT * FROM user WHERE username = ?";
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
                Category = resultSet.getString("category");
			}
			
		}catch(SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void changeCategory(String username, String category) {
		
		try {
			String selectQuery = "SELECT * FROM user WHERE username = ?";
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				String insertQuery = "UPDATE user SET category = ? WHERE username = ?";
                preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1,category);
                preparedStatement.setString(2,username);
                preparedStatement.executeUpdate();
                Category = category;
                System.out.println(Category);
			}
			
		}catch(SQLException e) {
            e.printStackTrace();
        }
		
	}
	
	public int getScore(String username, String category, int level) {
		int score = 0;
		try {
			String selectQuery = "SELECT * FROM user WHERE username = ?";
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				if(category.equals("2")) {
					if(level == 1) {
						score = resultSet.getInt("level1_2");
					}
					if(level == 2) {
						score = resultSet.getInt("level2_2");
					}
					if(level == 3) {
						score = resultSet.getInt("level3_2");
					}
				}else if(category.equals("3")) {
					if(level == 1) {
						score = resultSet.getInt("level1_3");
					}
					if(level == 2) {
						score = resultSet.getInt("level2_3");
					}
					if(level == 3) {
						score = resultSet.getInt("level3_3");
					}
				}else {
					if(level == 1) {
						score = resultSet.getInt("level1");
					}
					if(level == 2) {
						score = resultSet.getInt("level2");
					}
					if(level == 3) {
						score = resultSet.getInt("level3");
					}
				}
			}
		}catch(SQLException e) {
	            e.printStackTrace();
	    }	
		
		return score;
	}
	
	public void setScore(String username, String category, int level, int score) {
	    try {
	        String selectQuery = "SELECT * FROM user WHERE username = ?";
	        preparedStatement = connection.prepareStatement(selectQuery);
	        preparedStatement.setString(1, username);
	        resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            if (category.equals("2")) {
	                if (level == 1) {
	                    String insertQuery = "UPDATE user SET level1_2 = ? WHERE username = ?";
	                    preparedStatement = connection.prepareStatement(insertQuery);
	                    preparedStatement.setInt(1, score);
	                    preparedStatement.setString(2, username);
	                    preparedStatement.executeUpdate();
	                    System.out.println("skor 1-2!");
	                }
	                if (level == 2) {
	                    String insertQuery = "UPDATE user SET level2_2 = ? WHERE username = ?";
	                    preparedStatement = connection.prepareStatement(insertQuery);
	                    preparedStatement.setInt(1, score);
	                    preparedStatement.setString(2, username);
	                    preparedStatement.executeUpdate();
	                    System.out.println("skor 2-2!");
	                }
	                if (level == 3) {
	                    String insertQuery = "UPDATE user SET level3_2 = ? WHERE username = ?";
	                    preparedStatement = connection.prepareStatement(insertQuery);
	                    preparedStatement.setInt(1, score);
	                    preparedStatement.setString(2, username);
	                    preparedStatement.executeUpdate();
	                    System.out.println("skor 3-2!");
	                }
	            } else if (category.equals("3")) {
	                if (level == 1) {
	                    String insertQuery = "UPDATE user SET level1_3 = ? WHERE username = ?";
	                    preparedStatement = connection.prepareStatement(insertQuery);
	                    preparedStatement.setInt(1, score);
	                    preparedStatement.setString(2, username);
	                    preparedStatement.executeUpdate();
	                    System.out.println("skor 1-3!");
	                }
	                if (level == 2) {
	                    String insertQuery = "UPDATE user SET level2_3 = ? WHERE username = ?";
	                    preparedStatement = connection.prepareStatement(insertQuery);
	                    preparedStatement.setInt(1, score);
	                    preparedStatement.setString(2, username);
	                    preparedStatement.executeUpdate();
	                    System.out.println("skor 2-3!");
	                }
	                if (level == 3) {
	                    String insertQuery = "UPDATE user SET level3_3 = ? WHERE username = ?";
	                    preparedStatement = connection.prepareStatement(insertQuery);
	                    preparedStatement.setInt(1, score);
	                    preparedStatement.setString(2, username);
	                    preparedStatement.executeUpdate();
	                    System.out.println("skor 3-3!");
	                }
	            } else {
	                if (level == 1) {
	                    String insertQuery = "UPDATE user SET level1 = ? WHERE username = ?";
	                    preparedStatement = connection.prepareStatement(insertQuery);
	                    preparedStatement.setInt(1, score);
	                    preparedStatement.setString(2, username);
	                    preparedStatement.executeUpdate();
	                    System.out.println("skor 1!");
	                }
	                if (level == 2) {
	                    String insertQuery = "UPDATE user SET level2 = ? WHERE username = ?";
	                    preparedStatement = connection.prepareStatement(insertQuery);
	                    preparedStatement.setInt(1, score);
	                    preparedStatement.setString(2, username);
	                    preparedStatement.executeUpdate();
	                    System.out.println("skor 2");
	                }
	                if (level == 3) {
	                    String insertQuery = "UPDATE user SET level3 = ? WHERE username = ?";
	                    preparedStatement = connection.prepareStatement(insertQuery);
	                    preparedStatement.setInt(1, score);
	                    preparedStatement.setString(2, username);
	                    preparedStatement.executeUpdate();
	                    System.out.println("skor 3");
	                }
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public void logout() {
		userType = "";
		userName = "";
		Category = "";
		bound.clear();
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
