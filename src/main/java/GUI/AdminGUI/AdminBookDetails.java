package GUI.AdminGUI;

import DocumentManager.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminBookDetails implements Initializable {
    @FXML
    private Label BookNameLabel;
    @FXML
    private Label BookAuthorLabel;
    @FXML
    private Label BookTypeLabel;
    @FXML
    private Label BookNumLabel;

    Book selectedBook;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * method to get book information.
     * @param book book class that contain information from database
     */
    public void setBookDetails(Book book) {
        selectedBook = book;

        BookNameLabel.setText(book.getBookName());
        BookAuthorLabel.setText(book.getBookAuthor());
        BookTypeLabel.setText(book.getBookType());
        BookNumLabel.setText(String.valueOf(book.getBookNums()));
    }

    @FXML
    public void OkButtonClicked(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    /**
     * Method to edit book properties.
     * @param event track input event
     * @throws IOException catch exception
     */
    public void EditBook(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/AdminGUI/UpdateBookStage.fxml"));
        Parent updateSceneRoot = loader.load();
        Scene scene = new Scene(updateSceneRoot);

        UpdateBookStageController updateBookStageController = loader.getController();
        updateBookStageController.setInformation(selectedBook);

        currentStage.setScene(scene);
    }

}
