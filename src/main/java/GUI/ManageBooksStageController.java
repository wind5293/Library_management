package GUI;

import DataBaseSQL.DatabaseConnection;
import DataBaseSQL.LibraryManagementModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private TableView<LibraryManagementModel> BookTable;
    @FXML
    private TableColumn<LibraryManagementModel, Integer> BookIDColumn;
    @FXML
    private TableColumn<LibraryManagementModel, String> BookNameColumn;
    @FXML
    private TableColumn<LibraryManagementModel, String> BookAuthorColumn;
    @FXML
    private TableColumn<LibraryManagementModel, Integer> BookReleaseYearColumn;


    ObservableList<LibraryManagementModel> libraryManagementModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getDBConnection();

        String libraryViewQuery = "select bookID, title, author, ReleaseYear from booktable";

        try {

            /*
             * Get books info table from database
             */
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(libraryViewQuery);

            while (queryOutput.next()) {
                Integer queryBookID = queryOutput.getInt("BookID");
                String queryTitle = queryOutput.getString("name");
                String queryAuthor = queryOutput.getString("author");
                Integer queryReleaseDate = queryOutput.getInt("ReleaseYear");

                libraryManagementModelObservableList.add(new LibraryManagementModel(queryBookID, queryTitle, queryAuthor,
                                                                                    queryReleaseDate));
            }

            BookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
            BookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
            BookAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
            BookReleaseYearColumn.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));

            BookTable.setItems(libraryManagementModelObservableList);


            /*
              Search event: Users type in search text field.
             */
            FilteredList<LibraryManagementModel> filteredData = new FilteredList<>(libraryManagementModelObservableList, b -> true);

            SearchBookTextField.textProperty().addListener(((observable, oldValue, newValue) -> {

                filteredData.setPredicate(libraryManagementModel -> {
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (libraryManagementModel.getBookName().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (libraryManagementModel.getBookAuthor().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (libraryManagementModel.getReleaseYear().toString().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else
                        return false;
                });
            }));

            SortedList<LibraryManagementModel> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(BookTable.comparatorProperty());

            BookTable.setItems(sortedData);

        } catch (SQLException e) {
            Logger.getLogger(ManageBooksStageController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
