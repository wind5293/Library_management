package User;

import DataBaseSQL.BorrowedBookDataBase;

import java.sql.SQLException;

public class ReaderAccount extends User {
    private BorrowedBookDataBase borrowedBookDataBase = new BorrowedBookDataBase();

    public ReaderAccount(int userId, String userName, String password) {
        super(userId, userName, password);
    }
    //private UserDataBase

    // Thêm sách vào cơ sở dữ liệu borrowedBooks
    public void borrowBook(String userName, String bookName) throws SQLException {
        try {
            borrowedBookDataBase.borrowBook(this.getUsername(), bookName);
            System.out.println("Sách '" + bookName + "' đã được thêm vào cơ sở dữ liệu.");
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm sách: " + e.getMessage());
            throw e;
        }
    }

    // Xóa sách khỏi cơ sở dữ liệu borrowedBooks.
//    public void removeBookFromBorrowed(String bookName, String bookAuthor) throws SQLException {
//        try {
//            borrowedBookDataBase.removeBookFromBorrowed(bookName, bookAuthor);
//            System.out.println("Sách '" + bookName + "' đã được xóa khỏi phieu muon.");
//        } catch (SQLException e) {
//            System.err.println("Lỗi khi xóa sách: " + e.getMessage());
//            throw e;
//        }
//    }
}
