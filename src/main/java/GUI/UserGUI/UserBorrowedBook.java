package GUI.UserGUI;

import DataBaseSQL.DatabaseConnection;
import DocumentManager.Book;
import GUI.AdminGUI.ManageBooksStageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserBorrowedBook implements Initializable {

    @FXML
    private TableView<Book> BookTable;
    @FXML
    private TableColumn<Book, String> BookNameColumn;
    @FXML
    private TableColumn<Book, String> BorrowedDateColumn ;
    @FXML
    private TableColumn<Book, String> ReturnDateColumn;

    ObservableList<Book> bookObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        initializeTable();
    }

    private void initializeTable() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getDBConnection();

        String borrowedBookViewQuery = "select bookName, borrowDate, returnDate from borrowedbooks where userName = ?;";

        try {
            bookObservableList.clear(); // Xóa dữ liệu hiện tại

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(borrowedBookViewQuery);

            while (queryOutput.next()) {
                String queryBookName = queryOutput.getString("bookName");
                String queryBorrowedDate = queryOutput.getString("borrowDate");
                String queryRetunrDate = queryOutput.getString("returnDate");

                //bookObservableList.add(new );
            }

            BookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
            BorrowedDateColumn.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
            ReturnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        } catch (SQLException e) {
            Logger.getLogger(ManageBooksStageController.class.getName()).log(Level.SEVERE, null, e);
        }
    }


}
