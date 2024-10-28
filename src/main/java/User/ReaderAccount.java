package User;

import DocumentManager.Book;

import java.util.List;

public class ReaderAccount extends User {

    private List<Book>bookList;


    /**
     * Methods
     */

    /**
     * Add book.
     * Lua chon sach de muon.
     */
    public void addBook(Book book) {
        bookList.add(book);
    }

    /**
     * Delete book.
     * Xoa khoi danh sach lua chon.
     */
    public void deleteBook(Book book) {
        bookList.remove(book);
    }

    /**
     * Lay danh sach tu lua chon cua nguoi doc.
     */
    public List<Book> getList(Book book) {
        // Ban CommandLine -> in theo dong
        return bookList;
    }

    /**
     * SearchBook in DataBase.
     */
    //public List<Book> searchBook() {

    //}

    /**
     * Search Func.
     */
}
