package GUI.AdminGUI;

import DataBaseSQL.BookDataBase;
import DataManagement.ExcelToDatabase;
import User.ManagerAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddBook {


    @FXML
    private TextField bookNameTextField;
    @FXML
    private TextField bookAuthorTextField;
    @FXML
    private TextField bookNumTextField;
    @FXML
    private TextField ExcelLinkTextField;
    @FXML
    private TextField bookTypeTextField;

    @FXML
    public void AddButtonSubmit(ActionEvent event) throws SQLException {

        if (ExcelLinkTextField.getText() != null) {
            //sua lai link excel
            String fixExcelLink = ExcelLinkTextField.getText().replace("\\","\\\\");
            ExcelToDatabase importer = new ExcelToDatabase(fixExcelLink);
            importer.importData();
        }
        String bookName = bookNameTextField.getText();
        String bookAuthor = bookAuthorTextField.getText();
        String bookType = bookTypeTextField.getText();
        int bookNum = Integer.parseInt(bookNumTextField.getText());

        ManagerAccount managerAccount = new ManagerAccount();
        managerAccount.addBook(bookName, bookAuthor, bookType, bookNum);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Thêm sách thành công");
        alert.show();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void CancelButtonClicked(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
