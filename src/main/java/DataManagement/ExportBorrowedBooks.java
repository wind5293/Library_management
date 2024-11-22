package DataManagement;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.sql.ResultSet;

public class ExportBorrowedBooks extends DatabaseToExcel {

    private String username;

    public ExportBorrowedBooks(String username) {
        this.username = username;
    }

    @Override
    public String getQuery() {
        return "SELECT * FROM borrowedbook WHERE username = '" + username + "'";
    }

    @Override
    public void writeDataToSheet(Sheet sheet, ResultSet resultSet) throws Exception {

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Borrow ID");
        headerRow.createCell(1).setCellValue("Book Name");
        headerRow.createCell(2).setCellValue("Borrow Date");
        headerRow.createCell(3).setCellValue("Return Date");

        int rowIndex = 1;
        while (resultSet.next()) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(resultSet.getInt("borrowId"));
            row.createCell(1).setCellValue(resultSet.getString("bookName"));
            row.createCell(2).setCellValue(resultSet.getDate("borrowDate").toString());
            row.createCell(3).setCellValue(resultSet.getDate("returnDate").toString());
        }
    }
}