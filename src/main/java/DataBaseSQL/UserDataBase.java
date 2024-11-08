package DataBaseSQL;

import java.sql.*;

public class UserDataBase extends DataBaseSQL {

    /**
     * Method to add new User to DataBase
     * @param title book's title
     * @param author author's name
     * @throws SQLException catch exception
     */
    @Override
    public void addToDataBase(String title, String author, int num) throws SQLException {
        String arg = "insert into bookTable(author, title) VALUES (?, ?)";
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/librarymanagement", );

        PreparedStatement preparedStatement = con.prepareStatement(arg);
        preparedStatement.setString(1, author);
        preparedStatement.setString(2, title);

        preparedStatement.executeUpdate();

        con.close();
    }

    /**
     * Delete a document from library DataBase.
     * @param title name of document
     * @param author author's name
     * @throws SQLException catch error if query not executed
     */
    @Override
    public void deleteFromDataBase(String title, String author) throws SQLException {
        String arg = "Delete from bookTable where title = ? AND author = ?";
        Connection con = DriverManager.getConnection(jdbcUrl);

        PreparedStatement preparedStatement = con.prepareStatement(arg);
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, author);

        preparedStatement.executeUpdate();

        con.close();
    }

    /**
     * Method to search for Document in DataBase.
     * @param arg name of author or book
     * @return ResultSet include bookId, title, author
     * @throws SQLException catch exception if not found
     */
    @Override
    public ResultSet searchFromDataBase(String arg) throws SQLException {
        String query = "SELECT bookId, title, author From bookTable where title = ? or author = ?";
        Connection con = DriverManager.getConnection(jdbcUrl);

        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, arg);
        preparedStatement.setString(2, arg);

        return preparedStatement.executeQuery();
    }

    /**
     * Method to update Document in DataBase.
     * @param oldBookId ID of the book that user wants to change
     * @param title changed title of the book
     * @param author changed author
     * @throws SQLException catch exception
     */
    @Override
    public void updateDataBase(int oldBookId, String title, String author) throws SQLException {
        String query = "Update bookTable Set title = ?, author = ? where bookId = ?";
        Connection con = DriverManager.getConnection(jdbcUrl);

        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, author);
        preparedStatement.setInt(3, oldBookId);

        preparedStatement.executeUpdate();

        con.close();
    }

    @Override
    public int numberOfRows() throws SQLException {
        String query = "Select Count(*) from readerAccount";
        Connection con = DriverManager.getConnection(jdbcUrl);

        PreparedStatement preparedStatement = con.prepareStatement(query);

        ResultSet result = preparedStatement.executeQuery();

        result.next();
        return result.getInt(1);
    }
}
