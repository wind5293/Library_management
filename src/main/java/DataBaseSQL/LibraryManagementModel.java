package DataBaseSQL;

import java.sql.Date;

public class LibraryManagementModel {
    Integer bookID;
    String bookName, bookAuthor;
    Integer releaseYear;
    String status;
    Date loanDate, returnDate;

    public LibraryManagementModel(Integer bookID, String bookName, String bookAuthor,
                                  Integer releaseYear, String status,
                                  Date loanDate, Date returnDate) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.releaseYear = releaseYear;
        this.status = status;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
