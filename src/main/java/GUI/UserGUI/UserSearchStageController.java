package GUI.UserGUI;

import DataBaseSQL.DatabaseConnection;
import DocumentManager.Book;
import GUI.AdminGUI.ManageBooksStageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserSearchStageController implements Initializable {

    @FXML
    private TableView<Book> searchBookTableView;
    @FXML
    private TableColumn<Book, String> BookNameTableColumn;
    @FXML
    private TableColumn<Book, String> BookAuthorTableColumn;
    @FXML
    private TableColumn<Book, String> BookTypeTableColumn;
    @FXML
    private TableColumn<Book, String> BookNumTableColumn;

    @FXML
    private TextField searchBarTextField;

    ObservableList<Book> bookObservableList = FXCollections.observableArrayList();

    private String SearchText;

    public void SearchTextSet(String SearchText) {
        this.SearchText = SearchText;
        searchBarTextField.setText(SearchText);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
        setupSearchFilter();
    }

    private void initializeTable() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getDBConnection();

        String libraryViewQuery = "select bookName, bookAuthor, bookType, bookNums from booktable";

        try {
            bookObservableList.clear(); // Xóa dữ liệu hiện tại

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(libraryViewQuery);

            while (queryOutput.next()) {
                String queryBookName = queryOutput.getString("bookName");
                String queryBookAuthor = queryOutput.getString("bookAuthor");
                String queryBookType = queryOutput.getString("bookType");
                Integer queryBookNum = queryOutput.getInt("bookNums");

                bookObservableList.add(new Book(queryBookName, queryBookAuthor, queryBookType, queryBookNum));
            }

            BookNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
            BookAuthorTableColumn.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
            BookTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("bookType"));
            BookNumTableColumn.setCellValueFactory(new PropertyValueFactory<>("bookNums"));

            searchBookTableView.setItems(bookObservableList);

        } catch (SQLException e) {
            Logger.getLogger(UserSearchStageController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void setupSearchFilter() {
        FilteredList<Book> filteredData = new FilteredList<>(bookObservableList, b -> true);

        searchBarTextField.textProperty().addListener((observable, oldValue, newValue) -> {
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
        sortedData.comparatorProperty().bind(searchBookTableView.comparatorProperty());
        searchBookTableView.setItems(sortedData);
    }

    public void BorrowBookButtonClicked(ActionEvent event) {
        Book selectedBook = searchBookTableView.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            System.out.println("Không có sách được chọn");
        }


    }

    public void DetailsButtonClicked(ActionEvent event) {
        System.out.println("DetailsButtonClicked");
    }

}
