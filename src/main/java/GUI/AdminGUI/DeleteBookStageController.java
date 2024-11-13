package GUI.AdminGUI;

import User.ManagerAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DeleteBookStageController {


    @FXML
    private TextField bookNameTextField;
    @FXML
    private TextField bookAuthorTextField;

    @FXML
    public void AddButtonSubmit(ActionEvent event) throws SQLException {

        String bookName = bookNameTextField.getText();
        String bookAuthor = bookAuthorTextField.getText();

        ManagerAccount managerAccount = new ManagerAccount();
        managerAccount.deleteBook(bookName, bookAuthor);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Xoá sách thành công");
        alert.show();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void CancelButtonClicked(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
