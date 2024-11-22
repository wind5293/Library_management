package GUI;

import DataBaseSQL.UserDataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginStage {

    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    @FXML
    private Button button_login_log;
    @FXML
    private Button button_signup_log;
    @FXML
    private Label informationText;

    public void Login(ActionEvent event) throws SQLException, IOException {
        String userName = tf_username.getText();
        String userPassWord = tf_password.getText();

        UserDataBase userDataBase = new UserDataBase();

        if (checkAdmin(userName, userPassWord)) {
            switchScene(event,"AdminHome.fxml");
        }
        // Kiểm tra người dùng có tồn tại trong database hay không với mật khẩu
        else if (userDataBase.isUserExists(userName, userPassWord)) {
            // Đăng nhập thành công
            showAlert(Alert.AlertType.INFORMATION, "Đăng nhập thành công",
                    "Chào mừng " + userName + "!");

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();


        }
        else {
            // Đăng nhập thất bại
            showAlert(Alert.AlertType.ERROR, "Đăng nhập thất bại",
                    "Tên người dùng hoặc mật khẩu không chính xác.");
        }
    }

    /**
     * Hàm để hiển thị thông báo alert.
     *
     * @param alertType Loại alert (ERROR, INFORMATION...)
     * @param title Tiêu đề của alert
     * @param content Nội dung thông báo
     */
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);  // Không có header
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Hàm xử lý sự kiện khi người dùng nhấn vào nút "Đăng ký" (nếu có)
    @FXML
    public void goToSignUp(ActionEvent event) throws IOException {
        switchScene(event,"RegisterStage.fxml");
    }

    public boolean checkValidInput(String username, String password) {
        if (username.isEmpty()) {
            informationText.setText("Vui lòng nhập tên nguòi dùng");
            return false;
        } else if (password.isEmpty()) {
            informationText.setText("Vui lòng nhập mật khẩu");
            return false;
        }
        return true;
    }

    public boolean checkAdmin(String username, String password) {
        return Objects.equals(username, "admin") && Objects.equals(password, "admin");
    }

    public void switchScene(ActionEvent event, String newSceneName) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();;

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(newSceneName)));
        Scene newScene = new Scene(root);
        currentStage.setScene(newScene);
    }
}
