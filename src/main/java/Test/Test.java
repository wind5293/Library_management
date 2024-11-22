package Test;

import DataBaseSQL.BookDataBase;
import DataBaseSQL.BorrowedBookDataBase;
import DataBaseSQL.DatabaseConnection;
import DataBaseSQL.UserDataBase;
import DataManagement.ExcelToDatabase;
import DocumentManager.Author;
import DocumentManager.Book;
import Function.BookManagement;
import Function.SuggestionFunc;
import GUI.AdminHomeController;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws SQLException {
        String excelPath = "D:\\IdeaProjects\\LibMan\\BookExcelData.xlsx";
        ExcelToDatabase importer = new ExcelToDatabase(excelPath);
        importer.importData();
    }
}
