package GUI.UserGUI;

import DataBaseSQL.DatabaseConnection;
import DocumentManager.Book;
import DocumentManager.BorrowedBook;
import GUI.AdminGUI.ManageBooksStageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private TableView<BorrowedBook> BorrowedBookTable;
    @FXML
    private TableColumn<BorrowedBook, Integer> BookIDColumn;
    @FXML
    private TableColumn<BorrowedBook, String> BookNameColumn;
    @FXML
    private TableColumn<BorrowedBook, String> BorrowDateColumn;
    @FXML
    private TableColumn<BorrowedBook, String> ReturnDateColumn;

    ObservableList<BorrowedBook> borrowedObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        initializeTable();
    }

    private void initializeTable() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getDBConnection();

        String borrowedBookViewQuery = "select bookName, borrowDate, returnDate from borrowedbooks where userName = ?";

        try {
            borrowedObservableList.clear(); // Xóa dữ liệu hiện tại

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(borrowedBookViewQuery);

            while (queryOutput.next()) {
                String queryBookName = queryOutput.getString("bookName");
                String queryBorrowedDate = queryOutput.getString("borrowDate");
                String queryReturnDate = queryOutput.getString("returnDate");

                borrowedObservableList.add(new BorrowedBook(queryBookName, queryBorrowedDate, queryReturnDate));
            }

            BookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
            BorrowDateColumn.setCellValueFactory(new PropertyValueFactory<>("borrowedDate"));
            ReturnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

            BorrowedBookTable.setItems(borrowedObservableList);

        } catch (SQLException e) {
            Logger.getLogger(UserBorrowedBook.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void DetailsButtonClicked(ActionEvent event) {
        System.out.println("Details clicked");
    }

    @FXML
    private void ReturnButtonClicked(ActionEvent event) {
        System.out.println("Return clicked");
    }

}
