package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class MainStage {
    @FXML
    private TextField searchText;
    @FXML
    private Button searchButton;

    public void searchButtonClicked(ActionEvent event) {
        String searchContent = searchText.getText();
        System.out.println(searchContent);
    }

}
