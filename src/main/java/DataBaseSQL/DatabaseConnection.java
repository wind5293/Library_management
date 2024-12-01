package DataBaseSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection databaseLink;
    protected final String jdbcUrl = "jdbc:mysql://localhost:3306/librarymanagement";


    private DatabaseConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(jdbcUrl, "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Apply Singleton.
     * @return return instance
     */
    public static DatabaseConnection getInstance() {
        if(instance == null){
            synchronized (DatabaseConnection.class) {
                if(instance == null){
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    /**
     * Connect to Database.
     * @return Connection connect to database
     */
    public synchronized Connection getDBConnection() {
        try {
            if(databaseLink == null || databaseLink.isClosed()) {
                databaseLink = DriverManager.getConnection(jdbcUrl, "root", "root");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return databaseLink;
    }
}
