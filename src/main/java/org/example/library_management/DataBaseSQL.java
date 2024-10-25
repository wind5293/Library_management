package org.example.library_management;

import java.sql.*;

public class DataBaseSQL {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/Library";
    private static final String username = "root";
    private static final String password = "#Oop2023";

    Connection connection = null;

    Statement statement = null;
    ResultSet resultSet = null;

    public void test() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement();

        resultSet = statement.executeQuery("SELECT * FROM book_database");
        while (resultSet.next()) {
            int bookId = resultSet.getInt("bookId");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            boolean isBorrowed = resultSet.getBoolean("isBorrowed");

            System.out.println("Book ID: " + bookId + ", Title: " + title + ", Author: " + author
                    + ", Is Borrowed: " + isBorrowed);
        }
    }


}