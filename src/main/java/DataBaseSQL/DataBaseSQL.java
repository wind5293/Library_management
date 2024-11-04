package DataBaseSQL;

import java.sql.*;

public abstract class DataBaseSQL {
    protected static final String jdbcUrl = "jdbc:sqlite:Library_management/src/main/" +
            "java/DataBaseSQL/DataBaseSQL.db";

    public abstract void addToDataBase(String arg1, String arg2, int num) throws SQLException;

    public abstract void deleteFromDataBase(String arg1, String arg2) throws SQLException;

    public abstract ResultSet searchFromDataBase(String arg) throws SQLException;

    public abstract void updateDataBase(int oldBookId, String title, String author) throws SQLException;

    public abstract int numberOfRows() throws SQLException;
}

/**
 *     public void test () throws SQLException {
 *         Connection con = DriverManager.getConnection(url);
 *         Statement st = con.createStatement();
 *         ResultSet rs = st.executeQuery("SELECT * FROM bookTable;");
 *
 *             // Process the results
 *             while (rs.next()) {
 *                 int id = rs.getInt("bookId"); // Adjust column name as necessary
 *                 String title = rs.getString("title");
 *                 String author = rs.getString("author");
 *                 System.out.println("ID: " + id + ", title " + title + ", Password: " + author);
 *             }
 *     }
 **/