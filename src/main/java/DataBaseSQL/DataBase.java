package DataBaseSQL;

import java.sql.*;

public class DataBase {
    //String url = "jdbc:sqlite:D:\\UET - VNU\\OOP\\ProjectLibrary_main\\" +
            //"Library_management\\src\\main\\java\\DataBaseSQL\\DataBaseSQL.db";
    String url = "jdbc:sqlite:Library_management/src/main/java/DataBaseSQL/DataBaseSQL.db";
    public void test () throws SQLException {
        Connection con = DriverManager.getConnection(url);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM readerAccount;");

            // Process the results
            while (rs.next()) {
                int id = rs.getInt("userId"); // Adjust column name as necessary
                String username = rs.getString("username");
                String password = rs.getString("password");
                System.out.println("ID: " + id + ", Username: " + username + ", Password: " + password);
            }
    }
}

