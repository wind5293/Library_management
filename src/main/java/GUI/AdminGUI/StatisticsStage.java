package GUI.AdminGUI;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import DataBaseSQL.BookDataBase;
import DataBaseSQL.BorrowedBookDataBase;
import DataBaseSQL.DatabaseConnection;
import DataBaseSQL.UserDataBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

public class StatisticsStage implements Initializable {
    BookDataBase bookDataBase = new BookDataBase();
    UserDataBase userDataBase = new UserDataBase();
    BorrowedBookDataBase borrowedBookDataBase
            = new BorrowedBookDataBase();

    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getDBConnection();

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllData();
    }

    // Tải dữ liệu số người đọc, sách, sách đã mượn

    /**
     * Duc Minh Kiem tra
     */
    private void loadStatistics() {
        try {
            numReaders.setText(String.valueOf(userDataBase.getTotalUsers()));
            numBooks.setText(String.valueOf(bookDataBase.getTotalBooks()));
            issuedBooks.setText(String.valueOf(borrowedBookDataBase.getIssuedBooks()));
        } catch (SQLException e) {
            System.out.println("Khong load dc text");
            e.printStackTrace();
        }
    }

    // Tải dữ liệu biểu đồ độ tuổi
    private void loadAgeBarChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Age Groups");

        String query = "SELECT " +
                "CASE " +
                "    WHEN age BETWEEN 10 AND 20 THEN '10-20' " +
                "    WHEN age BETWEEN 21 AND 30 THEN '21-30' " +
                "    WHEN age BETWEEN 31 AND 40 THEN '31-40' " +
                "    ELSE '40+' " +
                "END AS age_group, " +
                "COUNT(*) AS total_users " +
                "FROM readeraccount " +
                "GROUP BY age_group;";

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
            System.err.println("Error loading age group data: " + e.getMessage());
            e.printStackTrace();
        }
        ageBarChart.getData().add(series);
    }

    // Tải dữ liệu biểu đồ thể loại sách
    private void loadBookGenrePieChart() {
        String query = "SELECT bookType, COUNT(*) AS total FROM booktable GROUP BY bookType;";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagement",
                "root", "root");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String type = rs.getString("bookType"); // Lấy giá trị cột `type`
                int total = rs.getInt("total");    // Đếm số lượng sách theo loại
                bookGenrePieChart.getData().add(new PieChart.Data(type, total));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }
    }

    // Gọi phương thức này để tải tất cả dữ liệu vào khi ứng dụng bắt đầu
    public void loadAllData() {
        loadStatistics();
        loadAgeBarChart();
        loadBookGenrePieChart();
    }
}