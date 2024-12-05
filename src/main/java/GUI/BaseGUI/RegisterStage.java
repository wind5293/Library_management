package GUI.BaseGUI;

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
        switchScene(event, "/GUI/BaseGUI/LoginStage.fxml");
    }

    // Phương thức hiển thị alert
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Regex strings help check the valid input
     */
    private static final String USERNAME_REGEX = "^[a-zA-Z0-9_]{3,20}$";
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final String ADDRESS_REGEX = "^.{5,100}$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    public boolean checkValidInput() {
        String username = UserNameTF.getText();
        String email = EmailTF.getText();
        Integer age = AgeChoice.getValue();
        String address = AddressTF.getText();
        String password = PasswordTF.getText();
        String cfPassword = CFPasswordTF.getText();

        if (username.isEmpty() || !username.matches(USERNAME_REGEX)) {
            informationText.setText("Vui lòng nhập tên người dùng hợp lệ (3-20 ký tự, không ký tự đặc biệt)");
            return false;
        } else if (email.isEmpty() || !email.matches(EMAIL_REGEX)) {
            informationText.setText("Vui lòng nhập email hợp lệ");
            return false;
        } else if (age == null || age < 10 || age > 120) {
            informationText.setText("Vui lòng chọn độ tuổi hợp lệ (10-120)");
            return false;
        } else if (address.isEmpty() || !address.matches(ADDRESS_REGEX)) {
            informationText.setText("Vui lòng nhập địa chỉ hợp lệ (5-100 ký tự)");
            return false;
        } else if (password.isEmpty() || !password.matches(PASSWORD_REGEX)) {
            informationText.setText("Mật khẩu phải chứa ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 5; i < 100; i++) {
            AgeChoice.getItems().add(i);
        }
        AgeChoice.setVisibleRowCount(5);
        AgeChoice.setValue(18);
    }
}
