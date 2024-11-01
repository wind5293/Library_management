package Function;

import DocumentManager.Book;

import java.util.ArrayList;

public class BookManagement {
    private ArrayList<Book> bookList;

    public BookManagement() {
        this.bookList = new ArrayList<>();
    }
    /**
     * Add Book.
     */
    public void addBook(Book book) {
        bookList.add(book);
    }

    /**
     * Remove Book.
     */
    public void removeBook(Book book) {
        bookList.remove(book);
    }

    /**
     * Getter n Setter.
     */
    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    /**
     *
     */
    public void getBookListInfo() {
        for(Book book: bookList) {
            System.out.println(book.getBookInfo());
        }
    }
}
