package GUI.AdminGUI;

import DocumentManager.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminBookDetails implements Initializable {
    @FXML
    private Label BookNameLabel;
    @FXML
    private Label BookAuthorLabel;
    @FXML
    private Label BookTypeLabel;
    @FXML
    private Label BookNumLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setBookDetails(Book book) {
        BookNameLabel.setText(book.getBookName());
        BookAuthorLabel.setText(book.getBookAuthor());
        BookTypeLabel.setText(book.getBookType());
        BookNumLabel.setText(String.valueOf(book.getBookNums()));
    }

    @FXML
    public void OkButtonClicked(ActionEvent event) {
        System.out.println("OkButtonClicked");
    }

}
