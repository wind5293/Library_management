package DocumentManager;

public class Book {
    private String id;
    private String title;
    private Author author;

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
}
