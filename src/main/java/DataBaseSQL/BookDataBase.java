package DataBaseSQL;

import javafx.scene.control.Alert;

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
    public void addToDataBase(String bookName, String bookAuthor, int bookNums) throws SQLException {
        //Query to add new rows.
        String insert = "insert into bookTable(bookName, bookAuthor, bookNums) VALUES (?, ?, ?);";
        //Query to check for existing book in Database.
        String check = "Select bookName, bookAuthor, bookNums from bookTable where " +
                "bookName = ? AND bookAuthor = ?;";
        //Query to add book to existed book in Database.
        String update = "UPDATE bookTable SET bookNums = bookNums + ? WHERE " +
                "bookName = ? AND bookAuthor = ?;";

        try (Connection con = databaseConnection.getDBConnection()) {

            //check if book is in database or not
            try (PreparedStatement checkCondition = con.prepareStatement(check)) {
                checkCondition.setString(1, bookName);
                checkCondition.setString(2, bookAuthor);

                try (ResultSet rs = checkCondition.executeQuery()) {

                    if (rs.next()) {
                        //if book is in database
                        try (PreparedStatement updateDB = con.prepareStatement(update)) {
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
     * Ham moi voi 4 tham so dau vao: bookName, bookAuthor, bookType, bookNums.
     */
    public void addToDataBase(String bookName, String bookAuthor, String bookType, int bookNums) throws SQLException {
        //Query to add new rows.
        String insert = "insert into bookTable(bookName, bookAuthor, bookType, bookNums) " +
                "VALUES (?, ?, ?, ?);";
        //Query to check for existing book in Database.
        String check = "Select bookName, bookAuthor from bookTable where " +
                "bookName = ? AND bookAuthor = ?;";
        //Query to add book to existed book in Database.
        String update = "UPDATE bookTable SET bookNums = bookNums + ? WHERE " +
                "bookName = ? AND bookAuthor = ?;";

        try (Connection con = databaseConnection.getDBConnection()) {

            //check if book is in database or not
            try (PreparedStatement checkCondition = con.prepareStatement(check)) {
                checkCondition.setString(1, bookName);
                checkCondition.setString(2, bookAuthor);

                try (ResultSet rs = checkCondition.executeQuery()) {

                    if (rs.next()) {
                        //if book is in database
                        try (PreparedStatement updateDB = con.prepareStatement(update)) {
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
                            preparedStatement.setString(3, bookType);
                            preparedStatement.setInt(4, bookNums);

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


    /**
     * Get Total Number of Book.
     *
     * @return total quantity of books
     * @throws SQLException catch exception.
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

    /**
     * Method to modify book record.
     *
     * @param bookName      updated Book name
     * @param bookAuthor    updated book author
     * @param bookType      updated book type
     * @param bookNums      updated book nums
     * @param currentBookId current id
     * @throws Exception catch exception
     */
    public void fixBookInfo(String bookName, String bookAuthor, String bookType,
                            int bookNums, int currentBookId) throws Exception {
        String query = "UPDATE booktable SET bookName = ?, bookAuthor = ?, bookType = ?, bookNums = ? WHERE bookId = ?";

        try (Connection con = databaseConnection.getDBConnection();
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setString(1, bookName);
            statement.setString(2, bookAuthor);
            statement.setString(3, bookType);
            statement.setInt(4, bookNums);
            statement.setInt(5, currentBookId);

            // Thực thi câu lệnh
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated <= 0) {
                throw new SQLException("Failed to update book");
            }
        } catch (SQLException e) {
            System.err.println("DataBase is not connected");
            throw e;
        }
    }
}