package GUI.BaseGUI;

import DataBaseSQL.UserDataBase;
import User.SaveUserName;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private Label forgotPasswordLabel;

    /**
     * Handle event when user clicked into "Login" button.
     * @param event event
     * @throws SQLException if there's an error about SQL connected
     * @throws IOException if there's an error about in/out
     */
    public void Login(ActionEvent event) throws SQLException, IOException {
        String userName = tf_username.getText();
        String userPassWord = tf_password.getText();

        UserDataBase userDataBase = new UserDataBase();

        if (checkAdmin(userName, userPassWord)) {
            switchScene(event,"/GUI/AdminHome.fxml");
        }
        // Check if user is existed.
        else if (userDataBase.isUserExists(userName, userPassWord)) {
            // Login success.
            SaveUserName.setLoggedInUsername(userName);
            showAlert(Alert.AlertType.INFORMATION, "Đăng nhập thành công",
                    "Chào mừng " + userName + "!");

            switchScene(event, "/GUI/UserHome.fxml");
        }
        else {
            // Login failed
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

    // Hàm xử lý sự kiện khi người dùng nhấn vào nút "Đăng ký" (nếu có).
    @FXML
    public void goToSignUp(ActionEvent event) throws IOException {
        switchScene(event,"/GUI/BaseGUI/RegisterStage.fxml");
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

    public void ForgotPassword(MouseEvent event) throws IOException {
        switchScene(event, "/GUI/BaseGUI/ForgotPasswordStage.fxml");
    }

    /**
     * Method helps change the scene.
     * @param event the action event triggered by clicked buttons
     * @param newSceneName name of the new scene will be loaded
     * @throws IOException if there's an error about in/out
     */
    public void switchScene(Event event, String newSceneName) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();;

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(newSceneName)));
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();

        currentStage.close();
    }


}
