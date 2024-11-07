package DataBaseSQL;

import java.sql.*;

public abstract class DataBaseSQL {
    //protected static final String jdbcUrl = "jdbc:sqlite:Library_management/src/main/" +
           // "java/DataBaseSQL/DataBaseSQL.db";
    protected static final DatabaseConnection databaseConnection = new DatabaseConnection();

    public abstract void addToDataBase(String arg1, String arg2, int num) throws SQLException;

    public abstract void deleteFromDataBase(String arg1, String arg2) throws SQLException;

    public abstract ResultSet searchFromDataBase(String arg) throws SQLException;

    public abstract void updateDataBase(int oldBookId, String title, String author) throws SQLException;

    public abstract int numberOfRows() throws SQLException;
}