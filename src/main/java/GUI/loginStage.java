package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class loginStage {

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button login_button;

    String username;
    String password;

    public void Login(ActionEvent event) {
        username = usernameTextField.getText();
        password = passwordTextField.getText();

        System.out.println(username + " " + password);
    }

}
