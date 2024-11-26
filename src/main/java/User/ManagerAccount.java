package User;

import DataBaseSQL.BookDataBase;
import java.sql.SQLException;



public class ManagerAccount {
    /**
     * Quan ly sach.
     */
    private BookDataBase bookDataBase = new BookDataBase();

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

    // Cập nhật thông tin sách trong cơ sở dữ liệu
//    public void updateDataBase(String bookName, String bookAuthor, int oldBookId) throws SQLException {
//        // Đầu tiên tìm và cập nhật thông tin sách
//        try {
//            // Cập nhật thông tin sách
//            bookDataBase.updateDataBase(bookName, bookAuthor, oldBookId);
//            System.out.println("Sách đã được cập nhật thành công!");
//        } catch (SQLException e) {
//            System.err.println("Lỗi khi cập nhật sách: " + e.getMessage());
//            throw e;
//        }
//    }

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

    /**
     * Quan ly user.
     */

}

