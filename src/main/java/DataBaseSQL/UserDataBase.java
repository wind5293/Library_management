package DataBaseSQL;

import java.sql.*;

public class UserDataBase {

    /**
     * Xoa user
     * Truy cap thong tin:
     * Ten User
     * Phieu muon (borrowedBooks data0
     */
    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    /**
     * Method to add new User account to database.
     *
     * @param username username
     * @param password password
     * @throws SQLException catch exception
     */
    public void addNewUser(String username, String password) throws SQLException {
        String insert = "INSERT INTO readerAccount(username, password) VALUES (?, ?);";
        String check = "Select username from readerAccount where username = ?";
        String check2 = "Select password from readerAccount where password = ?";

        try (Connection con = databaseConnection.getDBConnection()) {
            try (PreparedStatement checkCondition = con.prepareStatement(check)) {
                checkCondition.setString(1, username);

                ResultSet rs = checkCondition.executeQuery();
                if (rs.next()) {
                    System.out.println("Username already existed");
                    return;
                }
            }

            try (PreparedStatement insertUser = con.prepareStatement(insert)) {
                insertUser.setString(1, username);
                insertUser.setString(2, password);

                insertUser.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Can not add new user");
                throw e;
            }
        } catch (Exception e) {
            System.err.println("Can not connect");
        }
    }

    /**
     * Method to delete user from database.
     * @param username username
     * @param password password for confirmation
     * @throws SQLException catch exception
     */
    public void deleteUser(String username, String password) throws SQLException {
        String delete = "Delete from readerAccount where username = ? AND password = ?;";

        try (Connection con = databaseConnection.getDBConnection()) {
            try (PreparedStatement deleteQuery = con.prepareStatement(delete)) {
                deleteQuery.setString(1, username);
                deleteQuery.setString(2, password);

                deleteQuery.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Can not delete user");
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Can not connect to database");
            throw e;
        }
    }

    /**
     * method to update password.
     * @param username username
     * @param oldPassword old password for confirmation
     * @param newPassword new password to update
     * @throws SQLException catch exception
     */
    public void updatePassword(String username, String oldPassword,
                               String newPassword) throws SQLException {
        String update = "Update readerAccount set password = ? where username = ? AND password = ?;";

        try (Connection con = databaseConnection.getDBConnection()) {
            try (PreparedStatement updateQuery = con.prepareStatement(update)) {
                updateQuery.setString(1, newPassword);
                updateQuery.setString(2, username);
                updateQuery.setString(3, oldPassword);

                updateQuery.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Can not change password");
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Can not connect to database");
            throw e;
        }
    }

    public boolean isUserExists(String username, String password) throws SQLException {
        String query = "SELECT COUNT(*) FROM readerAccount WHERE username = ? AND password = ?";
        try (Connection con = databaseConnection.getDBConnection()) {
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);

                ResultSet resultSet = statement.executeQuery();

                // Kiểm tra kết quả của truy vấn
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }


    /**
     * Lay so luong nguoi dung.
     * Can sua phan nay
     */
    public int getTotalUsers() throws SQLException {
        String query = "Select Count(*) from readeraccount;";
        try (Connection con = databaseConnection.getDBConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

                ResultSet result = preparedStatement.executeQuery();

                result.next();
                return result.getInt(1);
            } catch (SQLException e) {
                System.err.println(e.getMessage() + " " + e.getErrorCode());
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("DataBase is not connected");
            throw e;
        }
    }
}

