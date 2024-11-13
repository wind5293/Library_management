package GUI;

import DataBaseSQL.UserDataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class LoginStage {

    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    @FXML
    private Button button_login_log;
    @FXML
    private Button button_signup_log;

    public void Login(ActionEvent event) throws SQLException {
        String userName = tf_username.getText();
        String userPassWord = tf_password.getText();

        UserDataBase userDataBase = new UserDataBase();

        // Kiểm tra người dùng có tồn tại trong database hay không với mật khẩu
        if (userDataBase.isUserExists(userName, userPassWord)) {
            // Đăng nhập thành công
            showAlert(Alert.AlertType.INFORMATION, "Đăng nhập thành công",
                    "Chào mừng " + userName + "!");
        } else {
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
    public void goToSignUp(ActionEvent event) {
        // Chuyển đến giao diện đăng ký (SignUp)
        System.out.println("Chuyển đến giao diện đăng ký");
    }
}
