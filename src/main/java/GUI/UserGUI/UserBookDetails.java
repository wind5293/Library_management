package GUI.UserGUI;

import DocumentManager.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UserBookDetails {
    @FXML
    private Label bookName;
    @FXML
    private Label bookAuthor;
    @FXML
    private Label bookType;
    @FXML
    private Label bookNums;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button borrowBtn;

    public void setBook(Book book) {
        bookName.setText(book.getBookName());
        bookAuthor.setText(book.getBookAuthor());
        bookType.setText(book.getBookType());
        bookNums.setText(String.valueOf(book.getBookNums()));
    }
}
