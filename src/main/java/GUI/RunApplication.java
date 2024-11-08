package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class RunApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/MainStage.fxml")));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}