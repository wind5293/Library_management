package GUI;

import GUI.FxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class AdminHomeController implements Initializable {
    @FXML
    private Button homePageButton;
    @FXML
    private Button manageBooksButton;
    @FXML
    private BorderPane mainPane;

    @FXML
    public void handleHomePageButtonClicked(ActionEvent event) {
        System.out.println("You clicked homePageButton");

        FxmlLoader loader = new FxmlLoader();
        Pane pane  = loader.getPage("AdminGUI/StatisticsStage");
        mainPane.setCenter(pane);
    }

    @FXML
    public void handleManageBooksButtonClicked(ActionEvent event) {
        System.out.println("You clicked manageBooksButton");

        FxmlLoader loader = new FxmlLoader();
        Pane pane  = loader.getPage("AdminGUI/ManageBooksStage");
        mainPane.setCenter(pane);

    }

    public void handleManageUserButtonClicked(ActionEvent event) {
        System.out.println("You clicked manageUserButton");

        FxmlLoader loader = new FxmlLoader();
        Pane pane  = loader.getPage("AdminGUI/ManageUserStage");
        mainPane.setCenter(pane);
    }

    public void logoutButtonClicked(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/LoginStage.fxml")));
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();

        currentStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        FxmlLoader loader = new FxmlLoader();
        Pane pane  = loader.getPage("AdminGUI/StatisticsStage");
        mainPane.setCenter(pane);
    }
}
