package GUI.AdminGUI;

import DocumentManager.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdminBookDetails {
    @FXML
    private Label bookName;
    @FXML
    private Label bookAuthor;
    @FXML
    private Label bookType;
    @FXML
    private Label bookNums;

    public void setBook(Book book) {
        bookName.setText(book.getBookName());
        bookAuthor.setText(book.getBookAuthor());
        bookType.setText(book.getBookType());
        bookNums.setText(String.valueOf(book.getBookNums()));
    }



}
