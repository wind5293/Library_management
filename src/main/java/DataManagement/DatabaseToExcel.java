package DataManagement;

import DataBaseSQL.DatabaseConnection;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
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

            // Thực hiện query lấy dữ liệu
            String query = getQuery();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Tạo sheet và viết dữ liệu
            Sheet sheet = workbook.createSheet("Data");
            writeDataToSheet(sheet, resultSet);

            // Tạo file tạm trong thư mục hệ thống
            File tempFile = File.createTempFile("temp_excel", ".xlsx");
            tempFile.deleteOnExit(); // Xóa file khi chương trình kết thúc

            // Ghi dữ liệu ra file tạm
            try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                workbook.write(outputStream);
            }


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
