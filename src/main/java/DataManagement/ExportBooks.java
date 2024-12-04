package DataManagement;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.sql.ResultSet;

public class ExportBooks extends DatabaseToExcel {

    @Override
    public String getQuery() {
        return "SELECT * FROM bookTable";
    }

    /**
     * Method to write information to excel sheet.
     * @param sheet Excel sheet
     * @param resultSet Result set that contains information from Database
     * @throws Exception catch Exception
     */
    @Override
    public void writeDataToSheet(Sheet sheet, ResultSet resultSet) throws Exception {
        // Ghi tiêu đề cột
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Book ID");
        headerRow.createCell(1).setCellValue("Book Name");
        headerRow.createCell(2).setCellValue("Book Author");
        headerRow.createCell(3).setCellValue("Book Type");
        headerRow.createCell(4).setCellValue("Book Nums");

        int rowIndex = 1;
        while (resultSet.next()) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(resultSet.getInt("bookId"));
            row.createCell(1).setCellValue(resultSet.getString("bookName"));
            row.createCell(2).setCellValue(resultSet.getString("bookAuthor"));
            row.createCell(3).setCellValue(resultSet.getString("bookType"));
            row.createCell(4).setCellValue(resultSet.getString("bookNums"));
        }
    }
}