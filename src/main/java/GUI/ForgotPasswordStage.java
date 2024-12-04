package GUI;

import DataBaseSQL.UserDataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ForgotPasswordStage implements Initializable {

    @FXML
    private TextField UsernameTF;
    @FXML
    private TextField EmailTF;
    @FXML
    private PasswordField PasswordTF;
    @FXML
    private PasswordField CFPasswordTF;

    @FXML
    private Label informationText;

    private String username;
    private String email;
    private String password;
    private String cfPassword;

    public void ResetPasswordButtonClicked(ActionEvent event) throws SQLException, IOException {
        username = UsernameTF.getText();
        email = EmailTF.getText();
        password = PasswordTF.getText();
        cfPassword = CFPasswordTF.getText();

        if (checkValidInput()) {
            UserDataBase userDataBase = new UserDataBase();
            boolean isPasswordUpdated = userDataBase.forgotPassword(username, email, password);

            if (!isPasswordUpdated) {
                informationText.setText("Tên người dùng hoặc email không đúng. Vui lòng kiểm tra lại.");
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Thành công",
                        "Mật khẩu của bạn đã được đổi thành công");
                switchScene(event, "LoginStage.fxml");
            }
        }
    }

    public void CancelButtonClicked(ActionEvent event) throws IOException {
        switchScene(event, "LoginStage.fxml");
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void switchScene(ActionEvent event, String newSceneName) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();;

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(newSceneName)));
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();

        currentStage.close();
    }

    /**
     * check password validation.
     * @return true if the format is correct, False if not
     */
    public boolean checkValidInput() {
        String usernameRegex = "^[a-z0-9]{3,20}$";
        String emailRegex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || cfPassword.isEmpty()) {
            informationText.setText("Vui lòng điền đầy đủ thông tin.");
            return false;
        }
        if (!username.matches(usernameRegex)) {
            informationText.setText("Tên người dùng chỉ được chứa ký tự chữ và số, từ 3-20 ký tự.");
            return false;
        }
        if (!email.matches(emailRegex)) {
            informationText.setText("Email không hợp lệ.");
            return false;
        }
        if (!password.matches(passwordRegex)) {
            informationText.setText("Mật khẩu phải có ít nhất 8 ký tự, chứa chữ hoa, chữ thường và số.");
            return false;
        }
        if (!password.equals(cfPassword)) {
            informationText.setText("Mật khẩu và xác nhận mật khẩu không khớp.");
            return false;
        }
        return true;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
