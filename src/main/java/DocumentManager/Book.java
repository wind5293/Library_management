package DocumentManager;

public class Book {
    private Integer bookID;
    private String bookName;
    private int bookNums;
    private String bookAuthor;
    private Integer releaseYear;

    public Book() {
    }

    /**
     * Constructor.
     */
    public Book(Integer bookID, String bookName, String bookAuthor, int releaseYear) {
        this.bookID = bookID;
        this.releaseYear = releaseYear;
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

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Get info.
     */
    public String getBookInfo() {
        return this.bookID + " " + this.bookName + " " + bookAuthor.toString();
    }

}
