package User;

import DataBaseSQL.BookDataBase;
import DocumentManager.Author;
import DocumentManager.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ManagerAccount {
    private List<ReaderAccount> readerManageList;
    private BookDataBase bookDataBase;

    public ManagerAccount(BookDataBase bookDataBase) {
        this.readerManageList = new ArrayList<>();
        this.bookDataBase = bookDataBase;
    }

    /**
     * Thêm sách vào cơ sở dữ liệu.
     */
    public void addBookToDatabase(String title, String author) {
        try {
            bookDataBase.addToDataBase(title, author);
            System.out.println("Book added to database.");
        } catch (SQLException e) {
            System.err.println("Failed to add book: " + e.getMessage());
        }
    }

    /**
     * Xóa sách khỏi cơ sở dữ liệu.
     */
    public void deleteBookFromDatabase(String title, String author) {
        try {
            bookDataBase.deleteFromDataBase(title, author);
            System.out.println("Book deleted from database.");
        } catch (SQLException e) {
            System.err.println("Failed to delete book: " + e.getMessage());
        }
    }

    /**
     * Tìm sách trong cơ sở dữ liệu.
     */
    public List<Book> searchBooksInDatabase(String searchQuery) {
        List<Book> foundBooks = new ArrayList<>();
        try {
            ResultSet resultSet = bookDataBase.searchFromDataBase(searchQuery);
            while (resultSet.next()) {
                String title = resultSet.getString("Title");
                String authorName = resultSet.getString("Author");
                Author author = new Author(authorName);
                foundBooks.add(new Book(title, author));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.err.println("Failed to search for book: " + e.getMessage());
        }
        return foundBooks;
    }

    /**
     * Update Library.
     */


//    /**
//     * Display Library
//     */
//    public List<Book> displayAllBooks() {
//        List<Book> allBooks = new ArrayList<>();
//        try {
//            ResultSet resultSet = bookDataBase.getAllBooks();
//            while (resultSet.next()) {
//                String title = resultSet.getString("title");
//                String authorName = resultSet.getString("author");
//                Author author = new Author(authorName);
//                allBooks.add(new Book(title, author));
//            }
//            resultSet.close();
//        } catch (SQLException e) {
//            System.err.println("Failed to retrieve books: " + e.getMessage());
//        }
//        return allBooks;
//    }

}

