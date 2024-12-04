package GUI.AdminGUI;

import DataBaseSQL.DatabaseConnection;
import DataManagement.DatabaseToExcel;
import DataManagement.ExportBooks;
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
    private TableColumn<Book, Integer> BookNumColumn;

    @FXML
    private Button addBookButton;
    @FXML
    private Button removeBookButton;
    @FXML
    private Button DetailsButton;
    @FXML
    private Button ExcelExportButton;

    ObservableList<Book> bookObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        initializeTable();
        setupSearchFilter();
    }

    /**
     * create table from database and print to screen.
     */
    private void initializeTable() {
        Connection connectDB = DatabaseConnection.getInstance().getDBConnection();

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
                Integer queryBookNum = queryOutput.getInt("bookNums");

                bookObservableList.add(new Book(queryBookID, queryBookName, queryBookAuthor, queryBookType, queryBookNum));
            }

            BookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
            BookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
            BookAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
            BookTypeColumn.setCellValueFactory(new PropertyValueFactory<>("bookType"));
            BookNumColumn.setCellValueFactory(new PropertyValueFactory<>("bookNums"));

            BookTable.setItems(bookObservableList);

        } catch (SQLException e) {
            Logger.getLogger(ManageBooksStageController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * setup for search bar.
     */
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

    /**
     * Method to handle event when add book button clicked.
     * @param event track input event
     * @throws IOException catch Exception
     */
    public void AddButtonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/AdminGUI/AddBookStage.fxml")));
        Scene scene = new Scene(root);
        Stage popupStage = new Stage();
        popupStage.setScene(scene);
        popupStage.show();

        // Làm mới bảng sau khi thêm sách mới
        popupStage.setOnHidden(e -> refresh());
    }

    /**
     * Method to delete book when remove button clicked.
     * @param event track input event
     * @throws IOException catch Exception
     * @throws SQLException catch Exception
     */
    public void RemoveButtonClicked(ActionEvent event) throws IOException, SQLException {

        Book selectedBook = BookTable.getSelectionModel().getSelectedItem();

        if (selectedBook == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Chưa chọn sách");
            alert.setContentText("Vui lòng chọn một cuốn sách để xoá.");
            alert.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Bạn thật sự muốn xoá dữ liệu của cuốn sách này?");

        ButtonType button_type_yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType button_type_no = new ButtonType("No", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(button_type_yes, button_type_no);

        Optional<ButtonType> option = alert.showAndWait();

        if (option.isPresent() && option.get() == button_type_yes) {
            ManagerAccount managerAccount = new ManagerAccount();
            try {
                // Nếu không có người mượn, tiến hành xóa sách
                managerAccount.deleteBook(selectedBook.getBookName(), selectedBook.getBookAuthor());

                // Hiển thị thông báo thành công
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Xóa Sách Thành Công");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Cuốn sách '" + selectedBook.getBookName() +
                        "' của tác giả '" + selectedBook.getBookAuthor() +
                        "' đã được xóa khỏi cơ sở dữ liệu.");
                successAlert.show();

            } catch (SQLException e) {
                // Hiển thị thông báo lỗi khi không xóa được sách
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Lỗi Xóa Sách");
                errorAlert.setHeaderText("Không thể xóa sách");
                errorAlert.setContentText("Lỗi khi xóa sách: Có người dùng đang mượn cuốn sách này." );
                errorAlert.show();
            }
        }

        refresh();
    }

    /**
     * Method to print book detail to screen.
     * @param event track input event
     * @throws IOException catch Exception
     */
    public void DetailsButtonClicked(ActionEvent event) throws IOException {
        Book selectedBook = BookTable.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            System.out.println("Không có sách được chọn");
        }

        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AdminBookDetails.fxml"));
        Parent detailsSceneRoot = loader.load();
        Scene scene = new Scene(detailsSceneRoot);

        AdminBookDetails adminBookDetails = loader.getController();
        adminBookDetails.setBookDetails(selectedBook);

        stage.setScene(scene);
        stage.show();

        stage.setOnHidden(e -> refresh());
    }

    /**
     * Convert data to excel.
     * @param event track input event
     */
    public void ExcelExportClicked(ActionEvent event) {
        DatabaseToExcel exportBooks = new ExportBooks();
        exportBooks.exportToExcel();
    }
}
