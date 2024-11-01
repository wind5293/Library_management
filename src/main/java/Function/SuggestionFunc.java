package Function;

import DocumentManager.Book;

import java.util.ArrayList;
import java.util.List;

public class SuggestionFunc {


    /**
     * Khi nhap vao thanh tim kiem
     * Ham nay se hien len cac goi y
     */
    public List<Book> getSuggestionBook1(String keyword, BookManagement bookManagement) {
        List<Book> bookList = bookManagement.getBookList();
        List<Book> suggestions = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getTitle().startsWith(keyword)) {
                suggestions.add(book);
            }
        }
        return suggestions;
    }

    /**
     * Khi tra sach A co tac gia A1
     * Goi y tac pham cung tac gia.
     * Command line lay du lieu sach tu List.
     */
    public List<Book> getSuggestionBook2(Book book, BookManagement bookManagement) {
        List<Book> suggestionsBook = new ArrayList<>();
        List<Book> bookList = bookManagement.getBookList();
        for (Book books : bookList) {
            if (books.getAuthor().equals(book.getAuthor())) {
                suggestionsBook.add(books);
            }
        }
        return suggestionsBook;
    }


}