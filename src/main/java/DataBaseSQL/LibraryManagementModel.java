package DataBaseSQL;

import java.sql.Date;

public class LibraryManagementModel {
    Integer bookID;
    String bookName, bookAuthor;
    Integer releaseYear;

    public LibraryManagementModel(Integer bookID, String bookName, String bookAuthor,
                                  Integer releaseYear) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.releaseYear = releaseYear;
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }
}
