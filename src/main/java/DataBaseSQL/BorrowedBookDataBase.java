package DataBaseSQL;

import java.sql.*;
import java.time.LocalDate;

public class BorrowedBookDataBase {

    /**
     * Method borrowBook get the book in bookTable.
     *
     * @param userName
     * @param bookName
     * @throws SQLException
     */
    public void borrowBook(String userName, String bookName,LocalDate borrowDate, LocalDate returnDate) throws SQLException {
        String query = "INSERT INTO borrowedBooks(userName, bookName, borrowDate, returnDate) " +
                "VALUES (?, ?, ?, ?);";
        borrowDate = LocalDate.now();
        try (Connection con = DatabaseConnection.getInstance().getDBConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, userName);  // Liên kết với UserDataBase
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

//    public void removeBookFromBorrowed(String userName, String bookName) throws SQLException {
//        String query = "DELETE FROM borrowedBooks WHERE userName = ? AND bookName = ?";
//
//        try (Connection con = databaseConnection.getDBConnection()) {
//            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
//                preparedStatement.setString(1, userName); // Set tên người dùng
//                preparedStatement.setString(2, bookName); // Set tên sách
//
//                int rowsAffected = preparedStatement.executeUpdate();
//                if (rowsAffected > 0) {
//                    System.out.println("Sách '" + bookName + "' đã được xóa khỏi danh sách mượn của người dùng "
//                            + userName);
//                } else {
//                    System.out.println("Không tìm thấy sách '" + bookName + "' trong danh sách mượn của người dùng "
//                            + userName);
//                }
//            } catch (SQLException e) {
//                System.err.println("Lỗi khi xóa sách khỏi danh sách mượn.");
//                e.printStackTrace();
//                throw e;
//            }
//        } catch (SQLException e) {
//            System.err.println("Không thể kết nối với cơ sở dữ liệu.");
//            e.printStackTrace();
//            throw e;
//        }
//    }


    /**
     * Get Bookb->>Duc Minh
     */
//    public void getBookFromBorrowed(String userName) throws SQLException {
//        String query = "SELECT bookName FROM borrowedBooks WHERE userName = ?";
//
//        try (Connection con = databaseConnection.getDBConnection()) {
//            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
//                preparedStatement.setString(1, userName); // Set tên người dùng
//
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    while (resultSet.next()) {
//                        String bookName = resultSet.getString("bookName");
//                    }
//                }
//            } catch (SQLException e) {
//                System.err.println("Lỗi khi truy vấn sách từ danh sách mượn.");
//                e.printStackTrace();
//                throw e;
//            }
//        } catch (SQLException e) {
//            System.err.println("Không thể kết nối với cơ sở dữ liệu.");
//            e.printStackTrace();
//            throw e;
//        }
//    }

    /**
     * Lay so luong sach da muon.
     * Can kiem tra phan nay.
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

                LocalDate returnDate = null;
                LocalDate today = LocalDate.now();

                if (returnVal.next()) {
                   returnDate = returnVal.getDate("returnDate").toLocalDate();
                } else {
                    System.err.println("No book record found");
                    return;
                }

                if (today.isBefore(returnDate) || returnDate.equals(today)) {
                    // Xóa sách khỏi borrowedBooks sau khi trả
                    try (PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
                         PreparedStatement returnQuantity = con.prepareStatement(returnValue)) {

                        deleteStatement.setString(1, userName);
                        deleteStatement.setString(2, bookName);

                        returnQuantity.setString(1, bookName);

                        //Check if the book has been successfully returned or not
                        int check = deleteStatement.executeUpdate();

                        if (check > 0) {
                            System.out.println("Sách '" + bookName + "' đã được xóa khỏi danh sách mượn của người dùng " + userName);
                            //if returned successfully, increase number of that book by one
                            returnQuantity.executeUpdate();
                        } else {
                            System.out.println("No record found to return");
                        }
                    }
                }

            } catch (SQLException e) {
                System.err.println("Lỗi khi kiểm tra sách.");
                e.printStackTrace();
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi kết nối cơ sở dữ liệu.");
            e.printStackTrace();
            throw e;
        }
    }
}

