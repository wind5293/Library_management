package DataBaseSQL;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getDBConnection() {

        String databaseName = "";
        String databaseUser = "root";
        String databasePassword = "Soulofwind@1";
        String url = "jdbc:mysql://localhost/librarymanagement" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return databaseLink;
    }
}
