package User;

import DataBaseSQL.BookDataBase;
import java.sql.SQLException;



public class ManagerAccount {
    /**
     * Quan ly sach.
     */
    BookDataBase bookDataBase = new BookDataBase();

    // Thêm sách vào cơ sở dữ liệu
    public void addBook(String bookName, String bookAuthor, String bookType, int bookNums) throws SQLException {
        try {
            bookDataBase.addToDataBase(bookName, bookAuthor, bookType, bookNums);
            System.out.println("Sách '" + bookName + "' đã được thêm vào cơ sở dữ liệu.");
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm sách: " + e.getMessage());
            throw e;
        }
    }

    // Xóa sách khỏi cơ sở dữ liệu
    public void deleteBook(String bookName, String bookAuthor) throws SQLException {
        try {
            bookDataBase.deleteFromDataBase(bookName, bookAuthor);
            System.out.println("Sách '" + bookName + "' đã được xóa khỏi cơ sở dữ liệu.");
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa sách: " + e.getMessage());
            throw e;
        }
    }
}

