package DocumentManager;

public class Book {
    private Integer bookID;
    private String bookName;
    private int bookNums;
    private String bookAuthor;

    public Book() {
    }

    /**
     * Constructor.
     */
    public Book(Integer bookID, String bookName, String bookAuthor, int bookNums) {
        this.bookID = bookID;
        this.bookNums = bookNums;
        this.bookAuthor = bookAuthor;
        this.bookName = bookName;
    }

    /**
     * Getter n Setter.
     */
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

    public int getBookNums() {

        return bookNums;
    }

    public void setBookNums(int bookNums) {

        this.bookNums = bookNums;
    }

    /**
     * Get info.
     */
    public String getBookInfo() {
        return this.bookID + " " + this.bookName + " " + bookAuthor.toString();
    }

}
