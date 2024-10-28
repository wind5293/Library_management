package Test;

import DocumentManager.Author;
import DocumentManager.Book;

public class Test {
    public static void main(String[] args) {
        Author author = new Author("Hung","12/9/1998","Nam Dinh");
        Book b = new Book("S0111","Hoa vang", author);

        System.out.println(b.getBookInfo());
    }
}
