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

    public String searchButtonClicked(ActionEvent event) {
        String searchContent = searchText.getText();
<<<<<<< HEAD

=======
        return searchContent;
>>>>>>> 5aff11eb5d97483f30ad12ec0bc82d8e501fafe8
    }


}
