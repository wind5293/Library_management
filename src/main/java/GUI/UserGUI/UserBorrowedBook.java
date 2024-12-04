package GUI.UserGUI;

import DataBaseSQL.BorrowedBookDataBase;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;
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

    public void ReturnButtonClicked(ActionEvent event) throws SQLException {

        BorrowedBook selectedBook = BorrowedBookTable.getSelectionModel().getSelectedItem();

        String username = SaveUserName.getLoggedInUsername();
        String bookName = selectedBook.getBookName();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Bạn có muốn trả cuốn sách này không?");

        ButtonType button_type_yes = new ButtonType("Có", ButtonBar.ButtonData.YES);
        ButtonType button_type_no = new ButtonType("Không", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(button_type_yes, button_type_no);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == button_type_yes) {
            BorrowedBookDataBase borrowedBookDataBase = new BorrowedBookDataBase();
            borrowedBookDataBase.returnBook(username, bookName);

            Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
            informationAlert.setHeaderText("Bạn đã trả cuốn sách " + bookName + " thành công.");
            informationAlert.setOnHidden(e -> initializeTable());
            informationAlert.showAndWait();

        }
    }

    public void ExportExcelButtonClicked(ActionEvent event) {
        DatabaseToExcel exportBorrowBook = new ExportBorrowedBooks(getUsername);
        exportBorrowBook.exportToExcel();
    }

}
