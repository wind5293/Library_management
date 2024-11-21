package GUI;

import GUI.FxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
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


    @Override
    public void initialize(URL url, ResourceBundle resource) {
        FxmlLoader loader = new FxmlLoader();
        Pane pane  = loader.getPage("AdminGUI/StatisticsStage");
        mainPane.setCenter(pane);
    }
}
