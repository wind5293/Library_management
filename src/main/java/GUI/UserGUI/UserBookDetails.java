package GUI.UserGUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class UserBookDetails implements Initializable {
    @FXML
    private Label BookNameLabel;
    @FXML
    private Label BookAuthorLabel;
    @FXML
    private Label BookTypeLabel;
    @FXML
    private Label BookNumLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
