package GUI.AdminGUI;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StatisticsStage {
    @FXML
    private Label numReaders;
    @FXML
    private Label numBooks;
    @FXML
    private Label issuedBooks;
    @FXML
    private BarChart<String, Number> ageBarChart;
    @FXML
    private PieChart bookGenrePieChart;

    @FXML
    public void initialize() {
        loadStatistics();
        loadAgeBarChart();
        loadBookGenrePieChart();
    }

    private void loadStatistics() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagement",
                "root", "root")) {
            Statement stmt = conn.createStatement();

            // Số lượng người đọc
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total_readers FROM user");
            if (rs.next()) {
                numReaders.setText(String.valueOf(rs.getInt("total_readers")));
            }

            // Số lượng sách
            rs = stmt.executeQuery("SELECT COUNT(*) AS total_books FROM books");
            if (rs.next()) {
                numBooks.setText(String.valueOf(rs.getInt("total_books")));
            }

            // Số sách đã được mượn
            rs = stmt.executeQuery("SELECT COUNT(*) AS issued_books FROM borrowed_books");
            if (rs.next()) {
                issuedBooks.setText(String.valueOf(rs.getInt("issued_books")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAgeBarChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Age Groups");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database",
                "username", "password")) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT CASE " +
                            "WHEN age BETWEEN 10 AND 20 THEN '10-20' " +
                            "WHEN age BETWEEN 21 AND 30 THEN '21-30' " +
                            "WHEN age BETWEEN 31 AND 40 THEN '31-40' " +
                            "ELSE '40+' END AS age_group, COUNT(*) AS total_users " +
                            "FROM user GROUP BY age_group;"
            );

            while (rs.next()) {
                String ageGroup = rs.getString("age_group");
                int totalUsers = rs.getInt("total_users");
                series.getData().add(new XYChart.Data<>(ageGroup, totalUsers));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ageBarChart.getData().add(series);
    }

    private void loadBookGenrePieChart() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagement",
                "root", "root")) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT genre, COUNT(*) AS total FROM books GROUP BY genre");

            while (rs.next()) {
                String genre = rs.getString("genre");
                int total = rs.getInt("total");
                bookGenrePieChart.getData().add(new PieChart.Data(genre, total));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}