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

public class SearchStageController implements Initializable {
    @FXML
    public TextField searchText;
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
    @FXML
    private TableColumn<LibraryManagementModel, String> StatusColumn;
    @FXML
    private TableColumn<LibraryManagementModel, Date> LoanDateColumn;
    @FXML
    private TableColumn<LibraryManagementModel, Date> ReturnDateColumn;

    ObservableList<LibraryManagementModel> libraryManagementModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getDBConnection();

        String libraryViewQuery = "select BookID, BookName, BookAuthor, ReleaseYear, status, loanDate, returnDate from library";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(libraryViewQuery);

            while (queryOutput.next()) {
                Integer queryBookID = queryOutput.getInt("BookID");
                String queryBookName = queryOutput.getString("BookName");
                String queryBookAuthor = queryOutput.getString("BookAuthor");
                Integer queryReleaseDate = queryOutput.getInt("ReleaseYear");
                String queryStatus = queryOutput.getString("status");
                Date queryLoanDate = queryOutput.getDate("loanDate");
                Date queryReturnDate = queryOutput.getDate("returnDate");

                libraryManagementModelObservableList.add(new LibraryManagementModel(queryBookID, queryBookName, queryBookAuthor,
                                                                                    queryReleaseDate, queryStatus,
                                                                                    queryLoanDate, queryReturnDate));
            }

            BookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
            BookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
            BookAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
            BookReleaseYearColumn.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
            StatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
            LoanDateColumn.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
            ReturnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

            BookTable.setItems(libraryManagementModelObservableList);

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
                    } else if (libraryManagementModel.getStatus().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else
                        return false;
                });
            }));

            SortedList<LibraryManagementModel> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(BookTable.comparatorProperty());

            BookTable.setItems(sortedData);

        } catch (SQLException e) {
            Logger.getLogger(SearchStageController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
