package GUI.UserGUI;

import DataBaseSQL.BorrowedBookDataBase;
import DocumentManager.Book;
import User.SaveUserName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookDetails implements Initializable {
    @FXML
    private Label BookNameLabel;
    @FXML
    private Label BookAuthorLabel;
    @FXML
    private Label BookTypeLabel;
    @FXML
    private Label BookNumLabel;

    private Book book;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setBookDetails(Book book) {
        this.book = book;

        BookNameLabel.setText(book.getBookName());
        BookAuthorLabel.setText(book.getBookAuthor());
        BookTypeLabel.setText(book.getBookType());
        BookNumLabel.setText(String.valueOf(book.getBookNums()));
    }

    @FXML
    public void OkButtonClicked(ActionEvent event) {
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    public void BorrowBookButtonClicked(ActionEvent event) throws SQLException {
        String username = SaveUserName.getLoggedInUsername();
        String bookName = book.getBookName();

        BorrowedBookDataBase borrowedBookDataBase = new BorrowedBookDataBase();
        borrowedBookDataBase.borrowBook(username, bookName);


    }

}
