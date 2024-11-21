package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserHomeController implements Initializable {

    @FXML
    private Button LogoutButton;

    @FXML
    private Button NotificationButton;

    @FXML
    private Button YourBookButton;

    @FXML
    private BorderPane mainPane;



    @FXML
    public void LogoutButtonClicked(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Bạn có muốn đăng xuất?");

        ButtonType button_type_yes = new ButtonType("Có", ButtonBar.ButtonData.YES);
        ButtonType button_type_no = new ButtonType("Không", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(button_type_yes, button_type_no);

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == button_type_yes) {

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/LoginStage.fxml")));
            Scene scene = new Scene(root);
            Stage loginStage = new Stage();
            loginStage.setScene(scene);
            loginStage.show();

            currentStage.close();
        }
    }

    @FXML
    public void NotificationButtonClicked(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader();
        Pane pane = loader.getPage("AdminGUI/StatisticsStage");

        mainPane.setCenter(pane);
    }

    @FXML
    public void YourBookButtonClicked(ActionEvent event) {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FxmlLoader loader = new FxmlLoader();
        Pane pane = loader.getPage("AdminGUI/StatisticsStage");

        mainPane.setCenter(pane);
    }
}
