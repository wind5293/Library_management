package DataBaseSQL;

import java.sql.*;

public class UserDataBase {

    /**
     * Method to add new User account to database.
     *
     * @param username username
     * @param password password
     * @throws SQLException catch exception
     */
    public void addNewUser(String username, String password, String email,
                           String address, int age) throws SQLException {
        String insert = "INSERT INTO readerAccount(username, password, email, address, age) VALUES (?, ?, ?, ?, ?);";
        String check = "Select username from readerAccount where username = ?";
        String check2 = "Select password from readerAccount where password = ?";

        try (Connection con = DatabaseConnection.getInstance().getDBConnection()) {
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
                insertUser.setString(3, email);
                insertUser.setString(4, address);
                insertUser.setString(5, String.valueOf(age));

                insertUser.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Can not add new user");
                throw e;
            }
        } catch (Exception e) {
            System.err.println("Can not connect");
            throw e;
        }
    }

    /**
     * Method to delete user from database.
     *
     * @param username username
     * @param password password for confirmation
     * @throws SQLException catch exception
     */
    public void deleteUser(String username, String password) throws SQLException {
        String delete = "Delete from readerAccount where username = ? AND password = ?;";

        try (Connection con = DatabaseConnection.getInstance().getDBConnection()) {
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
     * Method to update password.
     * @param username username
     * @param email user's email
     * @param newPassword new password to update
     * @throws SQLException catch exception
     */
    public void updatePassword(String username, String email,
                               String newPassword) throws SQLException {
        String update = "Update readerAccount set password = ? where username = ? AND email = ?;";

        try (Connection con = DatabaseConnection.getInstance().getDBConnection()) {
            try (PreparedStatement updateQuery = con.prepareStatement(update)) {
                updateQuery.setString(1, newPassword);
                updateQuery.setString(2, username);
                updateQuery.setString(3, email);

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

    /**
     * Method to get password if forgot.
     * @param username username
     * @param email email address
     * @return return password that belong to that users
     * @throws SQLException catch Exception
     */
    public boolean forgotPassword(String username, String email, String newPass) throws SQLException {
        String newPassword = "update readerAccount set password = ? " +
                "where username = ? AND email = ?;";

        try (Connection con = DatabaseConnection.getInstance().getDBConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(newPassword)) {
                preparedStatement.setString(1, newPass);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, email);

                int affected = preparedStatement.executeUpdate();

                if (affected < 1) {
                    System.err.println("No matching user found!");
                    return false;
                }
            }
            return true;

        } catch (SQLException e) {
            System.err.println("Can not connect to database");
            throw e;
        }
    }

    /**
     * Check User is Exist.
     */
    public boolean isUserExists(String username, String password) throws SQLException {
        String query = "SELECT COUNT(*) FROM readerAccount WHERE username = ? AND password = ?";
        try (Connection con = DatabaseConnection.getInstance().getDBConnection()) {
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
     * Can kiem tra phan nay.
     */
    public int getTotalUsers() throws SQLException {
        String query = "Select Count(*) from readeraccount;";
        try (Connection con = DatabaseConnection.getInstance().getDBConnection()) {
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

