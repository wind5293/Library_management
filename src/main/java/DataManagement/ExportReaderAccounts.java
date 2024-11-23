package DataManagement;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.sql.ResultSet;

public class ExportReaderAccounts extends DatabaseToExcel {

    @Override
    public String getQuery() {
        return "SELECT * FROM readeraccount";
    }

    @Override
    public void writeDataToSheet(Sheet sheet, ResultSet resultSet) throws Exception {
        // Ghi tiêu đề cột
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Username");
        headerRow.createCell(1).setCellValue("Age");
        headerRow.createCell(2).setCellValue("Email");
        headerRow.createCell(3).setCellValue("Address");

        int rowIndex = 1;
        while (resultSet.next()) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(resultSet.getString("username"));
            row.createCell(1).setCellValue(resultSet.getString("age"));
            row.createCell(2).setCellValue(resultSet.getString("email"));
            row.createCell(3).setCellValue(resultSet.getString("address"));
        }
    }
}
