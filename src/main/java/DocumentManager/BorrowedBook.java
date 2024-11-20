package DocumentManager;

public class BorrowedBook {
    private Book book;
    private String borrwedDate;
    private String returnDate;

    public BorrowedBook(Book book, String returnDate, String borrwedDate) {
        this.book = book;
        this.returnDate = returnDate;
        this.borrwedDate = borrwedDate;
    }

    /**
     * Getter n Setter.
     */
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getBorrwedDate() {
        return borrwedDate;
    }

    public void setBorrwedDate(String borrwedDate) {
        this.borrwedDate = borrwedDate;
    }
}
