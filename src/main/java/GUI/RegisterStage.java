package GUI;

import DataBaseSQL.BookDataBase;
import DataBaseSQL.UserDataBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
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
    public void SignUp(ActionEvent event) throws SQLException {
        String userName = tf_username.getText();
        String userPassWord = tf_password.getText();
        String cfUserPassWord = tf_cfpassword.getText();

        UserDataBase userDataBase = new UserDataBase();


        /**
         * Check userPassword == confirm
         */

        // Kiểm tra mật khẩu và confirm mật khẩu
        if (!userPassWord.equals(cfUserPassWord)) {
            ///showAlert(Alert.AlertType.ERROR, "Đăng ký thất bại", "Mật khẩu không khớp. Vui lòng kiểm tra lại.");
            return;
        }

        // Lưu username và password vào database
        try {
            userDataBase.addNewUser(userName, userPassWord);
            //showAlert(Alert.AlertType.INFORMATION, "Thành công", "Đăng ký thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    // Phương thức hiển thị alert
//    private void showAlert(Alert.AlertType alertType, String title, String message) {
//        Alert alert = new Alert(alertType);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }

}
