package GUI.AdminGUI;

import java.sql.*;

import DataBaseSQL.BookDataBase;
import DataBaseSQL.BorrowedBookDataBase;
import DataBaseSQL.UserDataBase;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

public class StatisticsStage {
    private final BookDataBase bookDataBase = new BookDataBase();
    private final UserDataBase userDataBase = new UserDataBase();
    private final BorrowedBookDataBase borrowedBookDataBase
            = new BorrowedBookDataBase();

    @FXML
    private Label numReaders;

    @FXML
    private Label numBooks;

    @FXML
    private Label issuedBooks;

    @FXML
    private XYChart<String, Number> ageBarChart;

    @FXML
    private PieChart bookGenrePieChart;


    // Tải dữ liệu số người đọc, sách, sách đã mượn
    private void loadStatistics() {
        try {
            numReaders.setText(String.valueOf(userDataBase.getTotalUsers()));
            numBooks.setText(String.valueOf(bookDataBase.getTotalBooks()));
            issuedBooks.setText(String.valueOf(borrowedBookDataBase.getIssuedBooks()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Tải dữ liệu biểu đồ độ tuổi
    private void loadAgeBarChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Age Groups");

        String query = "SELECT CASE " +
                "WHEN age BETWEEN 10 AND 20 THEN '10-20' " +
                "WHEN age BETWEEN 21 AND 30 THEN '21-30' " +
                "WHEN age BETWEEN 31 AND 40 THEN '31-40' " +
                "ELSE '40+' END AS age_group, COUNT(*) AS total_users " +
                "FROM user GROUP BY age_group";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagement",
                "root", "root");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String ageGroup = rs.getString("age_group");
                int totalUsers = rs.getInt("total_users");
                series.getData().add(new XYChart.Data<>(ageGroup, totalUsers));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Thêm xử lý lỗi nếu cần
        }

        ageBarChart.getData().add(series);
    }

    // Tải dữ liệu biểu đồ thể loại sách
    private void loadBookGenrePieChart() {
        String query = "SELECT genre, COUNT(*) AS total FROM books GROUP BY genre";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagement",
                "root", "root");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String genre = rs.getString("genre");
                int total = rs.getInt("total");
                bookGenrePieChart.getData().add(new PieChart.Data(genre, total));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Thêm xử lý lỗi nếu cần
        }
    }

    // Gọi phương thức này để tải tất cả dữ liệu vào khi ứng dụng bắt đầu
    public void loadAllData() {
        loadStatistics();
        loadAgeBarChart();
        loadBookGenrePieChart();
    }
}