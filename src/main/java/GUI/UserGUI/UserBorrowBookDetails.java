package GUI.UserGUI;

import DocumentManager.BorrowedBook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserBorrowBookDetails implements Initializable {
    @FXML
    private Label BookNameLabel;
    @FXML
    private Label BorrowedDateLabel;
    @FXML
    private Label ReturnDateLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setBookDetails(BorrowedBook selectedBook) {
        BookNameLabel.setText(selectedBook.getBookName());
        BorrowedDateLabel.setText(selectedBook.getBorrowedDate());
        ReturnDateLabel.setText(selectedBook.getReturnDate());
    }

    public void OKButtonClicked(ActionEvent event) {
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currentStage.close();
    }
}
