package DataBaseSQL;

import java.sql.*;
import java.time.LocalDate;

public class BorrowedBookDataBase {

    /**
     * Method borrowBook get the book in bookTable.
     *
     * @param userName   is name
     * @param bookName   us book name
     * @param borrowDate is now
     * @param returnDate is
     * @throws SQLException exception
     */
    public void borrowBook(String userName, String bookName, LocalDate borrowDate, LocalDate returnDate) throws SQLException {
        String query = "INSERT INTO borrowedBooks(userName, bookName, borrowDate, returnDate) " +
                "VALUES (?, ?, ?, ?);";
        String check = "select count(*) as count from borrowedBooks where username = ?";
        borrowDate = LocalDate.now();
        try (Connection con = DatabaseConnection.getInstance().getDBConnection()) {

            //Check if user already borrowed 5 books
            try (PreparedStatement checkCondition = con.prepareStatement(check)) {

                checkCondition.setString(1, userName);
                ResultSet borrowed = checkCondition.executeQuery();

                if (borrowed.next() && borrowed.getInt("count") >= 5) {
                    System.err.println("Maximum borrowed reached");
                    return;
                }

                try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                    preparedStatement.setString(1, userName);
                    preparedStatement.setString(2, bookName);
                    preparedStatement.setDate(3, Date.valueOf(borrowDate));
                    preparedStatement.setDate(4, Date.valueOf(returnDate));

                    preparedStatement.executeUpdate();

                    //decrease numbers of book in database by 1
                    String updateBookNums = "Update bookTable set bookNums = " +
                            "bookNums - 1 where bookName = ?;";

                    try (PreparedStatement changeBookNums = con.prepareStatement(updateBookNums)) {
                        changeBookNums.setString(1, bookName);
                        changeBookNums.executeUpdate();

                    }

                    System.out.println("Book " + bookName + " borrowed by " + userName);
                } catch (SQLException e) {
                    if (e.getSQLState().equals("45000")) {

                        //Catch SQL Trigger that limit 5 books borrowed per users
                        //Alert
                        System.out.println("Maximum borrowed books reached. Please " +
                                "return previous book before borrow");
                        System.out.println(e.getMessage());
                    } else {
                        System.err.println("Error borrowing book: " + e.getMessage());
                        throw e;
                    }
                }
            }
        }
    }


    /**
     * method to count total number of borrowed books.
     *
     * @return int number of borrowed Books
     * @throws SQLException catch Exception
     */
    public int getIssuedBooks() throws SQLException {
        String query = "Select Count(*) from borrowedBooks;";
        try (Connection con = DatabaseConnection.getInstance().getDBConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

                ResultSet result = preparedStatement.executeQuery();

                result.next();
                return result.getInt(1);
            } catch (SQLException e) {
                System.err.println(e.getMessage() + " " + e.getErrorCode());
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("DataBase is not connected");
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Method to return book by delete row in borrowedBook Table.
     *
     * @param userName username
     * @param bookName book's title
     * @throws SQLException catch Exception
     */
    public void returnBook(String userName, String bookName) throws SQLException {
        String checkQuery = "SELECT returnDate FROM borrowedBooks WHERE userName = ? AND bookName = ?";
        String deleteQuery = "DELETE FROM borrowedBooks WHERE userName = ? AND bookName = ?";
        String returnValue = "Update bookTable set bookNums = bookNums + 1 " +
                "where bookName = ?;";

        try (Connection con = DatabaseConnection.getInstance().getDBConnection()) {
            // Kiểm tra xem sách đã được mượn chưa và nếu đã đến hạn trả hay chưa
            try (PreparedStatement checkStatement = con.prepareStatement(checkQuery)) {
                checkStatement.setString(1, userName);
                checkStatement.setString(2, bookName);

                ResultSet returnVal = checkStatement.executeQuery();

                try (PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
                     PreparedStatement returnQuantity = con.prepareStatement(returnValue)) {

                    deleteStatement.setString(1, userName);
                    deleteStatement.setString(2, bookName);

                    returnQuantity.setString(1, bookName);

                    //Check if the book has been successfully returned or not
                    int check = deleteStatement.executeUpdate();

                    if (check > 0) {
                        System.out.println("Return book succeed");
                        //if returned successfully, increase number of that book by one
                        returnQuantity.executeUpdate();
                    } else {
                        System.out.println("No record found to return");
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error when check book in returnBook.");
                e.printStackTrace();
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Error when connect in returnBook.");
            e.printStackTrace();
            throw e;
        }
    }
}

