package DataBaseSQL;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;
    protected final String jdbcUrl = "jdbc:mysql://localhost:3306/librarymanagement";

    public Connection getDBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(jdbcUrl,"root","root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;
    }
}
