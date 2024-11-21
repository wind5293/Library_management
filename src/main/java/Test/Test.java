package Test;

import DataBaseSQL.BookDataBase;
import DataBaseSQL.BorrowedBookDataBase;
import DataBaseSQL.DatabaseConnection;
import DataBaseSQL.UserDataBase;
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
        BookDataBase bookDataBase = new BookDataBase();
        UserDataBase userDataBase = new UserDataBase();
        BorrowedBookDataBase borrowedBookDataBase
                = new BorrowedBookDataBase();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getDBConnection();
        System.out.println(String.valueOf(userDataBase.getTotalUsers()));
        System.out.println(String.valueOf(bookDataBase.getTotalBooks()));
        System.out.println(String.valueOf(borrowedBookDataBase.getIssuedBooks()));
    }
}
