package GUI.BaseGUI;

import GUI.AdminHomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.net.URL;

public class FxmlLoader {

    public FxmlLoader() {}

    /**
     * Get the pane to load in center pane.
     * @param fileName name of the .fxml file
     */
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
