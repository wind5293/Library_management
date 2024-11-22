package DataManagement;

import DataBaseSQL.DatabaseConnection;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
        try (Connection con = databaseConnection.getDBConnection()){
            String query = getQuery();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Data");

            writeDataToSheet(sheet, resultSet);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
