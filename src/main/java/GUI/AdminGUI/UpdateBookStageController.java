package GUI.AdminGUI;

import DataBaseSQL.BookDataBase;
import DocumentManager.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateBookStageController implements Initializable {

    @FXML
    private TextField BookNameTextField;
    @FXML
    private TextField BookAuthorTextField;
    @FXML
    private TextField BookTypeTextField;
    @FXML
    private TextField BookNumTextField;

    Book currentBook;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setInformation(Book book) {
        currentBook = book;

        BookNameTextField.setText(book.getBookName());
        BookAuthorTextField.setText(book.getBookAuthor());
        BookTypeTextField.setText(book.getBookType());
        BookNumTextField.setText(String.valueOf(book.getBookNums()));
    }

    public void OkButtonClicked(ActionEvent event) throws Exception {
        Stage updateStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        BookDataBase bookDataBase = new BookDataBase();

        String fixedBookName = BookNameTextField.getText();
        String fixedBookAuthor = BookAuthorTextField.getText();
        String fixedBookType = BookTypeTextField.getText();
        int fixBookNum = Integer.parseInt(BookNumTextField.getText());

        bookDataBase.fixBookInfo(fixedBookName, fixedBookAuthor, fixedBookType, fixBookNum, currentBook.getBookID());

        showAlert(Alert.AlertType.INFORMATION, "Thành công", "Sửa thông tin sách thành công");

        updateStage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
