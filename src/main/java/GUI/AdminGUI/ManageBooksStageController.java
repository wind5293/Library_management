package GUI.AdminGUI;

import DataBaseSQL.DatabaseConnection;
import DocumentManager.Book;
import User.ManagerAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageBooksStageController implements Initializable {
    @FXML
    public TextField SearchBookTextField;
    @FXML
    private TableView<Book> BookTable;
    @FXML
    private TableColumn<Book, Integer> BookIDColumn;
    @FXML
    private TableColumn<Book, String> BookNameColumn;
    @FXML
    private TableColumn<Book, String> BookAuthorColumn;
    @FXML
    private TableColumn<Book, String> BookTypeColumn;
    @FXML
    private TableColumn<Book, Integer> BookNumsColumn;

    @FXML
    private Button addBookButton;
    @FXML
    private Button removeBookButton;
    @FXML
    private Button updateBooksButton;

    ObservableList<Book> bookObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        initializeTable();
        setupSearchFilter();
    }

    private void initializeTable() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getDBConnection();

        String libraryViewQuery = "select bookId, bookName, bookAuthor, bookType, bookNums from booktable";

        try {
            bookObservableList.clear(); // Xóa dữ liệu hiện tại

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(libraryViewQuery);

            while (queryOutput.next()) {
                Integer queryBookID = queryOutput.getInt("bookId");
                String queryBookName = queryOutput.getString("bookName");
                String queryBookAuthor = queryOutput.getString("bookAuthor");
                String queryBookType = queryOutput.getString("bookType");
                Integer queryBookNums = queryOutput.getInt("bookNums");

                bookObservableList.add(new Book(queryBookID, queryBookName, queryBookAuthor, queryBookType, queryBookNums));
            }

            BookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
            BookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
            BookAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
            BookTypeColumn.setCellValueFactory(new PropertyValueFactory<>("bookType"));
            BookNumsColumn.setCellValueFactory(new PropertyValueFactory<>("bookNums"));

            BookTable.setItems(bookObservableList);

        } catch (SQLException e) {
            Logger.getLogger(ManageBooksStageController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void setupSearchFilter() {
        FilteredList<Book> filteredData = new FilteredList<>(bookObservableList, b -> true);

        SearchBookTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(book -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                return book.getBookName().toLowerCase().contains(searchKeyword) ||
                        book.getBookAuthor().toLowerCase().contains(searchKeyword) ||
                        book.getBookType().toLowerCase().contains(searchKeyword);
            });
        });

        SortedList<Book> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(BookTable.comparatorProperty());
        BookTable.setItems(sortedData);
    }

    public void refresh() {
        initializeTable(); // Tải lại dữ liệu từ cơ sở dữ liệu và cập nhật bảng
    }

    public void AddButtonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/AdminGUI/AddBookStage.fxml")));
        Scene scene = new Scene(root);
        Stage popupStage = new Stage();
        popupStage.setScene(scene);
        popupStage.show();

        // Làm mới bảng sau khi thêm sách mới
        popupStage.setOnHidden(e -> refresh());
    }

    public void RemoveButtonClicked(ActionEvent event) throws IOException, SQLException {

        Book selectedBook = BookTable.getSelectionModel().getSelectedItem();

        // System.out.println(selectedBook.getBookID() + " " + selectedBook.getBookName());

        // Thông báo admin database sẽ xoá dữ iệu của sách
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Bạn thật sự muốn xoá dữ liệu của cuốn sách này?");

        ButtonType button_type_yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType button_type_no = new ButtonType("No", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(button_type_yes, button_type_no);

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == button_type_yes) {
            ManagerAccount managerAccount = new ManagerAccount();
            managerAccount.deleteBook(selectedBook.getBookName(), selectedBook.getBookAuthor());
        }

        refresh();
    }
    public void UpdateButtonClicked(ActionEvent event) {
        System.out.println("Add Button Clicked");
    }
}