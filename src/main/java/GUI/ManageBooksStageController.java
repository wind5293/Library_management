package GUI;

import DataBaseSQL.DatabaseConnection;
import DocumentManager.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
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
    private TableColumn<Book, Integer> BookReleaseYearColumn;

    @FXML
    private Button addBookButton;
    @FXML
    private Button removeBookButton;
    @FXML
    private Button updateBooksButton;

    ObservableList<Book> bookObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getDBConnection();

        String libraryViewQuery = "select bookId, title, author, ReleaseYear from booktable";

        try {
            /*
             * Get books info table from database
             */
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(libraryViewQuery);

            while (queryOutput.next()) {
                Integer queryBookID = queryOutput.getInt("bookId");
                String queryBookName = queryOutput.getString("title");
                String queryBookAuthor = queryOutput.getString("author");
                Integer queryReleaseYear = queryOutput.getInt("ReleaseYear");

                bookObservableList.add(new Book(queryBookID, queryBookName, queryBookAuthor, queryReleaseYear));
            }

            BookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
            BookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
            BookAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
            BookReleaseYearColumn.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));

            BookTable.setItems(bookObservableList);


            /*
              Search event: Users type in search text field.
             */
            FilteredList<Book> filteredData = new FilteredList<>(bookObservableList, b -> true);

            SearchBookTextField.textProperty().addListener(((observable, oldValue, newValue) -> {

                filteredData.setPredicate(book -> {
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (book.getBookName().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (book.getBookAuthor().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else
                        return false;
                });
            }));

            SortedList<Book> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(BookTable.comparatorProperty());

            BookTable.setItems(sortedData);

        } catch (SQLException e) {
            Logger.getLogger(ManageBooksStageController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void AddButtonClicked(ActionEvent event) {
        System.out.println("Add Button Clicked");
    }

    public void RemoveButtonClicked(ActionEvent event) {
        System.out.println("Add Button Clicked");
    }
    public void UpdateButtonClicked(ActionEvent event) {
        System.out.println("Add Button Clicked");
    }
}
