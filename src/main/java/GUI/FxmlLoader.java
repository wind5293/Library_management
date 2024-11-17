package GUI;

import GUI.AdminGUI.AdminHomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class FxmlLoader {

    public FxmlLoader() {}

    public Pane getPage(String fileName) {
        Pane view;
        try {

            URL fileUrl = AdminHomeController.class.getResource(fileName + ".fxml");
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("FXML file cannot be found");
            }

            view = FXMLLoader.load(fileUrl);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return view;
    }
}
