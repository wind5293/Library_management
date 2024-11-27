package GUI.UserGUI;

import DataBaseSQL.BorrowedBookDataBase;
import DocumentManager.Book;
import User.SaveUserName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class BorrowBookAction implements Initializable {
    @FXML
    private Label BookNameLabel;
    @FXML
    private Label BookAuthorLabel;
    @FXML
    private Label BookTypeLabel;
    @FXML
    private Label BookNumLabel;
    @FXML
    private DatePicker ReturnDatePicker;

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
    public void CancelButtonClicked(ActionEvent event) {
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    public void BorrowBookButtonClicked(ActionEvent event) throws SQLException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String username = SaveUserName.getLoggedInUsername();
        String bookName = book.getBookName();
        LocalDate returnDate = ReturnDatePicker.getValue();

        Optional<ButtonType> result = confirmAlert();

        if (result.get().getButtonData() == ButtonBar.ButtonData.YES) {
            BorrowedBookDataBase borrowedBookDataBase = new BorrowedBookDataBase();
            borrowedBookDataBase.borrowBook(username, bookName, returnDate);

            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setHeaderText("Mượn sách thành công");
        }
        currentStage.close();
    }

    public Optional<ButtonType> confirmAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Xác nhận?");
        alert.setHeaderText("Bạn có muốn mượn cuốn sách này?");

        ButtonType button_type_yes = new ButtonType("Đồng ý", ButtonBar.ButtonData.YES);
        ButtonType button_type_no = new ButtonType("Huỷ bỏ", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(button_type_yes, button_type_no);

        return alert.showAndWait();
    }


}
