package DataBaseSQL;

import java.sql.*;

public class BookDataBase {

    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    /**
     * Method to add Document to Library Database.
     *
     * @param bookName   book's title
     * @param bookAuthor author's name
     * @param bookNums   quantity of book
     * @throws SQLException catch exception
     */
    //@Override
    public void addToDataBase(String bookName, String bookAuthor, int bookNums) throws SQLException {
        //Query to add new rows
        String insert = "insert into bookTable(bookName, bookAuthor, bookNums) VALUES (?, ?, ?);";
        //Query to check for existing book in Database
        String check = "Select bookName, bookAuthor, bookNums from bookTable where " +
                "bookName = ? AND bookAuthor = ?;";
        //Query to add book to existed book in Database
        String update = "UPDATE bookTable SET bookNums = bookNums + ? WHERE " +
                "bookName = ? AND bookAuthor = ?;";

        try (Connection con = databaseConnection.getDBConnection()) {

            //check if book is in database or not
            try (PreparedStatement checkCondition = con.prepareStatement(check)) {
                checkCondition.setString(1, bookName);
                checkCondition.setString(2, bookAuthor);

                try (ResultSet rs = checkCondition.executeQuery()) {

                    if(rs.next()){
                        //if book is in database
                        try(PreparedStatement updateDB = con.prepareStatement(update)) {
                            //increase book by bookNums
                            updateDB.setInt(1, bookNums);
                            updateDB.setString(2, bookName);
                            updateDB.setString(3, bookAuthor);
                            updateDB.executeUpdate();
                        }
                        //if book is not in database
                    } else {
                        //add new row
                        try (PreparedStatement preparedStatement = con.prepareStatement(insert)) {
                            preparedStatement.setString(1, bookName);
                            preparedStatement.setString(2, bookAuthor);
                            preparedStatement.setInt(3, bookNums);

                            preparedStatement.executeUpdate();
                        } catch (SQLException e) {
                            System.err.println(e.getMessage() + "add DataBase had ERROR!" + e.getErrorCode());
                            throw e;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("DataBase is not connected");
            throw e;
        }
    }

    /**
     * Delete a document from library DataBase.
     *
     * @param bookName   name of document
     * @param bookAuthor author's name
     * @throws SQLException catch error if query not executed
     */
    //@Override
    public void deleteFromDataBase(String bookName, String bookAuthor) throws SQLException {
        String arg = "Delete from bookTable where bookName = ? AND bookAuthor = ?;";
        try (Connection con = databaseConnection.getDBConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(arg)) {
                preparedStatement.setString(1, bookName);
                preparedStatement.setString(2, bookAuthor);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.err.println(e.getMessage() + "delete in DataBase had problems"
                        + e.getErrorCode());
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("DataBase is not connected");
            throw e;
        }
    }

//    /**
//     * Method to search for Document in DataBase using book's name or author's name.
//     * @param arg name of author or book
//     * @return ResultSet include bookId, book name, author and release year
//     * @throws SQLException catch exception if not found
//     */
//    @Override
//    public ResultSet searchFromDataBase(String arg) throws SQLException {
//        String query = "SELECT BookID, BookName, BookAuthor, ReleaseYear" +
//                " From library where BookName = ? or BookAuthor = ?;";
//        try(Connection con = databaseConnection.getDBConnection()) {
//            try(PreparedStatement preparedStatement = con.prepareStatement(query)) {
//                preparedStatement.setString(1, arg);
//                preparedStatement.setString(2, arg);
//
//                return preparedStatement.executeQuery();
//            } catch (SQLException e) {
//                System.err.println(e.getMessage() + " " + e.getErrorCode());
//                throw e;
//            }
//        } catch (SQLException e) {
//            System.err.println("DataBase is not connected");
//            throw e;
//        }
//    }

    /**
     * Method to get Info of all the books.
     *
     * @return ResultSet of all Documents.
     * @throws SQLException handle exception
     */
    public ResultSet getAllDocument() throws SQLException {
        String query = "select * from bookTable;";
        try (Connection con = databaseConnection.getDBConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
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
     * Method to update Document in DataBase.
     *
     * @param oldBookId  ID of the book that user wants to change
     * @param bookName   changed title of the book
     * @param bookAuthor changed author
     * @throws SQLException catch exception
     */
    //@Override
    public void updateDataBase(String bookName, String bookAuthor, int oldBookId) throws SQLException {
        String query = "Update bookTable Set bookName = ?, bookAuthor = ? where bookId = ?;";
        try (Connection con = databaseConnection.getDBConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, bookName);
                preparedStatement.setString(2, bookAuthor);
                preparedStatement.setInt(3, oldBookId);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.err.println(e.getMessage() + "update ERROR" + e.getErrorCode());
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("DataBase is not connected");
            throw e;
        }
    }

    //@Override
//    public int numberOfRows() throws SQLException {
//        String query = "Select Count(*) from library;";
//        try (Connection con = databaseConnection.getDBConnection()) {
//            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
//
//                ResultSet result = preparedStatement.executeQuery();
//
//                result.next();
//                return result.getInt(1);
//            } catch (SQLException e) {
//                System.err.println(e.getMessage() + " " + e.getErrorCode());
//                throw e;
//            }
//        } catch (SQLException e) {
//            System.err.println("DataBase is not connected");
//            throw e;
//        }
//    }

    /**
     * Get Total Number of Book.
     */
    public int getTotalBooks() throws SQLException {
        String query = "SELECT SUM(bookNums) FROM bookTable";
        try (Connection con = databaseConnection.getDBConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

                ResultSet result = preparedStatement.executeQuery();

                result.next();
                return result.getInt(1);
            } catch (SQLException e) {
                System.err.println(e.getMessage() + "get Total failed" + e.getErrorCode());
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("DataBase is not connected");
            throw e;
        }
    }

}