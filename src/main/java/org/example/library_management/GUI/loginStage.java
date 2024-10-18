package org.example.library_management.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class loginStage {

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button login_button;

    String username;
    String password;

    public void Login(ActionEvent event) throws IOException {
        username = usernameTextField.getText();
        password = passwordTextField.getText();

        System.out.println(username + " " + password);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainStage.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
