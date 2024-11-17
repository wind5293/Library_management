package GUI.AdminGUI;

import DataBaseSQL.BookDataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddBookStageController {


    @FXML
    private TextField bookNameTextField;
    @FXML
    private TextField bookAuthorTextField;
    @FXML
    private TextField bookNumTextField;

    @FXML
    public void AddButtonSubmit(ActionEvent event) throws SQLException {

        String bookName = bookNameTextField.getText();
        String bookAuthor = bookAuthorTextField.getText();
        int bookNum = Integer.parseInt(bookNumTextField.getText());

        BookDataBase bookDataBase = new BookDataBase();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Thêm sách thành công");
        alert.show();

        bookDataBase.addToDataBase(bookName, bookAuthor, bookNum);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void CancelButtonClicked(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
