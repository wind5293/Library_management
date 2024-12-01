package GUI.UserGUI;

import DataBaseSQL.DatabaseConnection;
import DataManagement.DatabaseToExcel;
import DataManagement.ExportBorrowedBooks;
import DocumentManager.BorrowedBook;
import User.SaveUserName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
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
    private TableColumn<BorrowedBook, LocalDate> BorrowDateColumn;
    @FXML
    private TableColumn<BorrowedBook, LocalDate> ReturnDateColumn;

    @FXML
    private Button DetailsButton;
    @FXML
    private Button ReturnButton;

    private String getUsername;

    public void setLoggedInUsername(String username) {
        this.getUsername = username;

    }

    ObservableList<BorrowedBook> borrowedObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        getUsername = SaveUserName.getLoggedInUsername();
        initializeTable();
    }

    public void initializeTable() {
        Connection connectDB = DatabaseConnection.getInstance().getDBConnection();

        String borrowedBookViewQuery = "select borrowId, bookName, borrowDate, returnDate from borrowedbooks where userName = ?";

        try {
            borrowedObservableList.clear(); // Xóa dữ liệu hiện tại

            PreparedStatement statement = connectDB.prepareStatement(borrowedBookViewQuery);
            statement.setString(1, getUsername);
            ResultSet queryOutput = statement.executeQuery();

            while (queryOutput.next()) {
                int queryBorrowID = queryOutput.getInt("borrowId");
                String queryBookName = queryOutput.getString("bookName");
                String queryBorrowedDate = String.valueOf(queryOutput.getDate("borrowDate"));
                String queryReturnDate = String.valueOf(queryOutput.getDate("returnDate"));

                borrowedObservableList.add(new BorrowedBook(queryBorrowID, queryBookName, queryBorrowedDate, queryReturnDate));
            }

            BookIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            BookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
            BorrowDateColumn.setCellValueFactory(new PropertyValueFactory<>("borrowedDate"));
            ReturnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

            BorrowedBookTable.setItems(borrowedObservableList);

        } catch (SQLException e) {
            Logger.getLogger(UserBorrowedBook.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void DetailsButtonClicked(ActionEvent event) throws IOException {

        BorrowedBook selectedBook = BorrowedBookTable.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            System.out.println("No book selected!");
            return;
        }

        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UserBorrowBookDetails.fxml"));
        Parent detailsSceneRoot = loader.load();
        Scene scene = new Scene(detailsSceneRoot);

        UserBorrowBookDetails userBookDetails = loader.getController();
        userBookDetails.setBookDetails(selectedBook);

        stage.setScene(scene);
        stage.show();
    }

    public void ReturnButtonClicked(ActionEvent event) {
        System.out.println("Return clicked");
    }

    public void ExportExcelButtonClicked(ActionEvent event) {
        DatabaseToExcel exportBorrowBook = new ExportBorrowedBooks(getUsername);
        exportBorrowBook.exportToExcel();
    }

}
