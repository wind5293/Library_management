package GUI;

import DataBaseSQL.UserDataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegisterStage implements Initializable {
    @FXML
    private Button SignUpButton;
    @FXML
    private Button LoginButton;
    @FXML
    private TextField UserNameTF;
    @FXML
    private TextField EmailTF;
    @FXML
    private TextField AddressTF;
    @FXML
    private TextField PasswordTF;
    @FXML
    private TextField CFPasswordTF;
    @FXML
    private ComboBox<Integer> AgeChoice;
    @FXML
    private Label informationText;

    public void switchScene(ActionEvent event, String newSceneName) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();;

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(newSceneName)));
        Scene newScene = new Scene(root);
        currentStage.setScene(newScene);
    }

    public void SignUpButtonClicked(ActionEvent event) throws SQLException {
        if (checkValidInput()) {
            try {

                String username = UserNameTF.getText();
                String password = PasswordTF.getText();
                String email = EmailTF.getText();
                Integer age = AgeChoice.getValue();
                String address = AddressTF.getText();

                UserDataBase userDataBase = new UserDataBase();
                userDataBase.addNewUser(username, password, email, address, age);

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

    public boolean checkValidInput() {
        String username = UserNameTF.getText();
        String email = EmailTF.getText();
        Integer age = AgeChoice.getValue();
        String address = AddressTF.getText();
        String password = PasswordTF.getText();
        String cfPassword = CFPasswordTF.getText();

        if (username.isEmpty()) {
            informationText.setText("Vui lòng nhập tên người dùng");
            return false;
        } else if (email.isEmpty() || !isValidEmail(email)) {
            informationText.setText("Vui lòng nhập email hợp lệ");
            return false;
        } else if (age == null) {
            informationText.setText("Vui lòng chọn độ tuổi");
            return false;
        } else if (address.isEmpty()) {
            informationText.setText("Vui lòng nhập địa chỉ");
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

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 5; i < 100; i++) {
            AgeChoice.getItems().add(i);
        }
        AgeChoice.setVisibleRowCount(5);
        AgeChoice.setValue(18);
    }
}
