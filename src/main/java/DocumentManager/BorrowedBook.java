package DocumentManager;

public class BorrowedBook {
    private String book;
    private String user;
    private String borrowDate;
    private String returnDate;

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        user = user;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public BorrowedBook(String user, String book, String borrowDate, String returnDate) {
        this.book = book;
        this.returnDate = returnDate;
        this.borrowDate = borrowDate;
        this.user = user;
    }
}
