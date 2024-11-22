package GUI;

import DataManagement.DatabaseToExcel;
import DataManagement.ExportBooks;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ExportData {
    @FXML
    private Label statusLabel;

    public void onExportBooksClicked() {
        DatabaseToExcel exportBooks = new ExportBooks();
        exportBooks.exportToExcel();
    }
}
