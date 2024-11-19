package GUI.AdminGUI;

import DataBaseSQL.DatabaseConnection;
import User.User;
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

public class ManageUserStageController implements Initializable {

    @FXML
    private TableView<User> UserTable;
    @FXML
    private TableColumn<User, Integer> UserIDColumn;
    @FXML
    private TableColumn<User, String> UsernameColumn;
    @FXML
    private TableColumn<User, String> UserEmailColumn;
    @FXML
    private TableColumn<User, String> UserAddressColumn;

    ObservableList<User> userObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
    }

    public void initializeTable() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getDBConnection();

        String userViewQuery = "select userId, username, password from readeraccount";

        try {
            userObservableList.clear(); // Xóa dữ liệu hiện tại

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(userViewQuery);

            while (queryOutput.next()) {
                int queryUserID = queryOutput.getInt("userId");
                String queryUsername = queryOutput.getString("username");
                String queryPassword = queryOutput.getString("password");


                userObservableList.add(new User(queryUserID, queryUsername, queryPassword));
            }

            UserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
            UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

            UserTable.setItems(userObservableList);

        } catch (SQLException e) {
            Logger.getLogger(ManageUserStageController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
