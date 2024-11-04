package DocumentManager;

public class Book {
    private String id;
    private String title;
    private Author author;
    private int numberOfBook;
    private boolean isBorrowed;

    /**
     *
     */
    public Book() {
    }

    /**
     * Contructor.
     */
    public Book(String id, String title, int numberOfBook, Author author) {
        this.id = id;
        this.title = title;
        this.numberOfBook = numberOfBook;
        this.author = author;
    }

    /**
     * Constructor 3.
     */
    public Book(String title, Author author) {
        this.author = author;
        this.title = title;
    }

    /**
     * Getter n Setter.
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    /**
     * Get info.
     */
    public String getBookInfo() {
        return String.format("Ma sach:%s\nTen sach:%s\nTac gia :%s\n",
                this.id, this.title, author.toString());
    }
}
