package Test;

import DocumentManager.Author;
import DocumentManager.Book;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.application.Application;

import java.util.Objects;


public class Test {
    public static void main1(String[] args) {
        Author author = new Author("Hung","12/9/1998","Nam Dinh");
        Book b = new Book("S0111","Hoa vang", author);

        System.out.println(b.getBookInfo());
    }
}
