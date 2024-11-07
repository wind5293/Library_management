package DataBaseSQL;

import java.sql.*;

public class BookDataBase extends DataBaseSQL {

    /**
     * Method to add Document to Library Database.
     *
     * @param title  book's title
     * @param author author's name
     * @param releasedYear the year that the book was published
     * @throws SQLException catch exception
     */
    @Override
    public void addToDataBase(String title, String author, int releasedYear) throws SQLException {
        String arg = "insert into library(BookAuthor, BookName, ReleaseYear) VALUES (?, ?, ?);";
        try(Connection con = databaseConnection.getDBConnection()) {
            try(PreparedStatement preparedStatement = con.prepareStatement(arg)) {
                preparedStatement.setString(1, author);
                preparedStatement.setString(2, title);
                preparedStatement.setInt(3, releasedYear);

                preparedStatement.executeUpdate();
            } catch (SQLException e){
                System.err.println(e.getMessage() + " " + e.getErrorCode());
                throw e;
            }
        } catch (SQLException e){
            System.err.println("DataBase is not Connected");
            throw e;
        }
    }

    /**
     * Delete a document from library DataBase.
     * @param title name of document
     * @param author author's name
     * @throws SQLException catch error if query not executed
     */
    @Override
    public void deleteFromDataBase(String title, String author) throws SQLException {
        String arg = "Delete from library where BookName = ? AND BookAuthor = ?;";
        try(Connection con = databaseConnection.getDBConnection()) {
            try(PreparedStatement preparedStatement = con.prepareStatement(arg)) {
                preparedStatement.setString(1, title);
                preparedStatement.setString(2, author);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.err.println(e.getMessage() + " " + e.getErrorCode());
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("DataBase is not connected");
            throw e;
        }
    }

    /**
     * Method to search for Document in DataBase using book's name or author's name.
     * @param arg name of author or book
     * @return ResultSet include bookId, book name, author and release year
     * @throws SQLException catch exception if not found
     */
    @Override
    public ResultSet searchFromDataBase(String arg) throws SQLException {
        String query = "SELECT BookID, BookName, BookAuthor, ReleaseYear" +
                " From library where BookName = ? or BookAuthor = ?;";
        try(Connection con = databaseConnection.getDBConnection()) {
            try(PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, arg);
                preparedStatement.setString(2, arg);

                return preparedStatement.executeQuery();
            } catch (SQLException e) {
                System.err.println(e.getMessage() + " " + e.getErrorCode());
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("DataBase is not connected");
            throw e;
        }
    }

    /**
     * Method to get Info of all the books.
     * @return ResultSet of all Documents.
     * @throws SQLException handle exception
     */
    public ResultSet getAllDocument() throws SQLException {
        String query = "select * from library;";
        try(Connection con = databaseConnection.getDBConnection()){
            try(PreparedStatement preparedStatement = con.prepareStatement(query)) {
                return preparedStatement.executeQuery();
            }  catch (SQLException e) {
                System.err.println(e.getMessage() + " " + e.getErrorCode());
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("DataBase is not connected");
            throw e;
        }
    }

    /**
     * Method to update Document in DataBase.
     *
     * @param oldBookId ID of the book that user wants to change
     * @param title     changed title of the book
     * @param author    changed author
     * @throws SQLException catch exception
     */
    @Override
    public void updateDataBase(int oldBookId, String title, String author) throws SQLException {
        String query = "Update library Set BookName = ?, BookAuthor = ? where BookID = ?;";
        try(Connection con = databaseConnection.getDBConnection()) {
            try(PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, title);
                preparedStatement.setString(2, author);
                preparedStatement.setInt(3, oldBookId);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.err.println(e.getMessage() + " " + e.getErrorCode());
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("DataBase is not connected");
            throw e;
        }
    }

    @Override
    public int numberOfRows() throws SQLException {
        String query = "Select Count(*) from library;";
        try(Connection con = databaseConnection.getDBConnection()) {
            try(PreparedStatement preparedStatement = con.prepareStatement(query)) {

                ResultSet result = preparedStatement.executeQuery();

                result.next();
                return result.getInt(1);
            }  catch (SQLException e) {
                System.err.println(e.getMessage() + " " + e.getErrorCode());
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("DataBase is not connected");
            throw e;
        }
    }
}
