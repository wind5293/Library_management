package DocumentManager;

public class BorrowedBook {
    private int id;
    private String bookName;
    private String borrowedDate;
    private String returnDate;

    public BorrowedBook(int id, String bookName, String returnDate, String borrowedDate) {
        this.id = id;
        this.bookName = bookName;
        this.returnDate = returnDate;
        this.borrowedDate = borrowedDate;
    }

    /**
     * Getter n Setter.
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate() {
        this.borrowedDate = borrowedDate;
    }
}
