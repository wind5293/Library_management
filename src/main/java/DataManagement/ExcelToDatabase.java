package DataManagement;

import DataBaseSQL.DatabaseConnection;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExcelToDatabase {
    private final DatabaseConnection databaseConnection = new DatabaseConnection();
    private final String excelFilePath;

    /**
     * Get excel file path.
     */
    public ExcelToDatabase(String excelFilePath) {
        this.excelFilePath = excelFilePath;
    }

    /**
     * Import data from excel sheet.
     */
    public void importData() {
        try (Connection con = databaseConnection.getDBConnection();
             FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            String checkQuery = "SELECT bookNums FROM bookTable WHERE bookName = ? AND bookAuthor = ?";
            String insertQuery = "INSERT INTO bookTable (bookName, bookAuthor, bookType, bookNums) VALUES (?, ?, ?, ?)";
            String updateQuery = "UPDATE bookTable SET bookNums = bookNums + ? WHERE bookName = ? AND bookAuthor = ?";

            try (PreparedStatement checkStatement = con.prepareStatement(checkQuery);
                 PreparedStatement insertStatement = con.prepareStatement(insertQuery);
                 PreparedStatement updateStatement = con.prepareStatement(updateQuery)) {

                for (Row row : sheet) {
                    if (row.getRowNum() == 0) continue;

                    String bookName = row.getCell(0).getStringCellValue();
                    String bookAuthor = row.getCell(1).getStringCellValue();
                    String bookType = row.getCell(2).getStringCellValue();
                    int bookNums = (int) row.getCell(3).getNumericCellValue();

                    // Kiểm tra xem sách đã tồn tại trong database chưa
                    checkStatement.setString(1, bookName);
                    checkStatement.setString(2, bookAuthor);
                    ResultSet rs = checkStatement.executeQuery();

                    if (rs.next()) {
                        // Nếu sách đã tồn tại, cập nhật số lượng sách
                        int currentQuantity = rs.getInt("bookNums");
                        updateStatement.setInt(1, bookNums); // Cộng số lượng sách từ Excel
                        updateStatement.setString(2, bookName);
                        updateStatement.setString(3, bookAuthor);
                        updateStatement.executeUpdate();
                        System.out.println("Sách '" + bookName + "' của tác giả '" + bookAuthor + "' đã được cập nhật số lượng!");
                    } else {
                        // Nếu sách chưa tồn tại, thêm mới vào database
                        insertStatement.setString(1, bookName);
                        insertStatement.setString(2, bookAuthor);
                        insertStatement.setString(3, bookType);
                        insertStatement.setInt(4, bookNums);
                        insertStatement.executeUpdate();
                        System.out.println("Sách '" + bookName + "' của tác giả '" + bookAuthor + "' đã được thêm mới vào database!");
                    }
                }

                System.out.println("Dữ liệu đã được xử lý thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}