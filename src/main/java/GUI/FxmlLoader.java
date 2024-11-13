package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.net.URL;

public class FxmlLoader {

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
