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

    /**
     * Get query.
     */
    public abstract String getQuery();

    /**
     * Method help write data to sheet.
     * @param sheet excel sheet
     * @param resultSet get result set
     * @throws Exception if there's an error
     */
    public abstract void writeDataToSheet(Sheet sheet, ResultSet resultSet) throws Exception;

    /**
     * Method export to excel.
     */
    public void exportToExcel() {

        try (Connection con = DatabaseConnection.getInstance().getDBConnection();
             Workbook workbook = new XSSFWorkbook()) {

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

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(tempFile);
            } else {
                System.out.println("Desktop is not supported. Cannot open the file automatically.");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
