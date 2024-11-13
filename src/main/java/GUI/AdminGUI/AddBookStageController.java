package GUI.AdminGUI;

import DataBaseSQL.BookDataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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

        bookDataBase.addToDataBase(bookName, bookAuthor, bookNum);
    }

}
