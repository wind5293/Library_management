package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import javax.print.DocFlavor;
import java.net.URL;
import java.util.ResourceBundle;


public class MainStage implements Initializable {
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
        Pane pane  = loader.getPage("StartStage");
        mainPane.setCenter(pane);
    }

    @FXML
    public void handleManageBooksButtonClicked(ActionEvent event) {
        System.out.println("You clicked manageBooksButton");

        FxmlLoader loader = new FxmlLoader();
        Pane pane  = loader.getPage("ManageBooksStage");
        mainPane.setCenter(pane);

    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {

    }
}
