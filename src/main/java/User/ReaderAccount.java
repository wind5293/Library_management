package User;

import DataBaseSQL.BorrowedBookDataBase;

import java.sql.SQLException;

public class ReaderAccount extends User {
    private BorrowedBookDataBase borrowedBookDataBase = new BorrowedBookDataBase();

    public ReaderAccount(int userId, String userName, String password) {
        super(userId, userName, password);
    }
}
