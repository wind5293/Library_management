package DataBaseSQL;

import java.sql.*;

public class BookDataBase extends DataBaseSQL{

    /**
     * Method to add Document to Library Database.
     * @param title book's title
     * @param author author's name
     * @throws SQLException catch exception
     */
    @Override
    public void addToDataBase(String title, String author) throws SQLException {
        String arg = "insert into bookTable(author, title) VALUES (?, ?)";
        Connection con = DriverManager.getConnection(jdbcUrl);

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
}
