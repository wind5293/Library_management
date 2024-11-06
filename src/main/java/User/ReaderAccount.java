package User;

import DocumentManager.Book;

import java.util.List;

public class ReaderAccount extends User {

    private List<Book> listBookBorrowed;
    private int bookBorrowedCount;


    public ReaderAccount(String userId, String userName, String password,
                         List<Book> listBookBorrowed, int bookBorrowedCount) {
        super(userId, userName, password);
        this.listBookBorrowed = listBookBorrowed;
        this.bookBorrowedCount = bookBorrowedCount;
    }

    /**
     * Add book.
     */
    public void addBookToCart(Book book) {

    }



    /**
     * Delete book.
     * Xoa khoi danh sach lua chon.
     */
    public void deleteBook(Book book) {
        
    }

    @Override
    public void addBook(Book book) {

    }

    @Override
    public String getUserType() {
        return "";
    }
}
