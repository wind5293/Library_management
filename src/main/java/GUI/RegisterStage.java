package GUI;

import DataBaseSQL.BookDataBase;
import DataBaseSQL.UserDataBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegisterStage {
    @FXML
    private Button button_cfsignup_reg;

    @FXML
    private Button button_login_reg;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_cfpassword;

    @FXML
    private Label informationText;

    public void switchScene(ActionEvent event, String newSceneName) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();;

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(newSceneName)));
        Scene newScene = new Scene(root);
        currentStage.setScene(newScene);
    }

    public void SignUp(ActionEvent event) throws SQLException {
        String username = tf_username.getText();
        String password = tf_password.getText();
        String cfPassword = tf_cfpassword.getText();

        if (checkValidInput(username, password, cfPassword)) {
            try {
                UserDataBase userDataBase = new UserDataBase();
                userDataBase.addNewUser(username, password);

                showAlert(Alert.AlertType.INFORMATION, "Hệ thống",
                          "Đăng kí thành công");

                switchScene(event, "LoginStage.fxml");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void LoginButtonClicked(ActionEvent event) throws IOException {
        switchScene(event, "LoginStage.fxml");
    }

    // Phương thức hiển thị alert
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public boolean checkValidInput(String username, String password, String cfPassword) {
        if (username.isEmpty()) {
            informationText.setText("Vui lòng nhập tên nguòi dùng");
            return false;
        } else if (password.isEmpty()) {
            informationText.setText("Vui lòng nhập mật khẩu");
            return false;
        } else if (cfPassword.isEmpty()) {
            informationText.setText("Vui lòng nhập lại mật khẩu");
            return false;
        } else if (!Objects.equals(password, cfPassword)) {
            informationText.setText("Mật khẩu không trùng khớp! Vui lòng nhập lại");
            return false;
        }
        return true;
    }
}
