package DataManagement;

import DataBaseSQL.DatabaseConnection;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class DatabaseToExcel {
     private final DatabaseConnection databaseConnection = new DatabaseConnection();

    /**
     * Export to Excel.
     * Su dung cho nguoi dung xuat danh sach sa muon
     * -> Query -> "select * from borrowedbook where username = ?"
     * SU dung cho Admin
     * + Xuat danh sach nguoi dung
     * -> Query -> "select * from readeraccount"
     * + Xuat danh sach borrowedBook
     * -> Query -> "select * from borrowedBook"
     * + Xuat danh sach sach
     * -> Query -> "select * from bookTable"
     */
    public abstract String getQuery();
    public abstract void writeDataToSheet(Sheet sheet, ResultSet resultSet) throws Exception;

    public void exportToExcel() {
        try (Connection con = databaseConnection.getDBConnection()) {
            String query = getQuery();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Data");

            writeDataToSheet(sheet, resultSet); // Lớp con sẽ triển khai

            // Chọn vị trí lưu file
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Excel File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                try (FileOutputStream outputStream = new FileOutputStream(file)) {
                    workbook.write(outputStream);
                    workbook.close();
                    String successMessage = "File saved successfully: " + file.getAbsolutePath();
                    System.out.println(successMessage);

                }
            } else {
                String cancelMessage = "Save operation was cancelled.";
                System.out.println(cancelMessage);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
