module org.example.library_management {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;

    opens GUI to javafx.fxml;
    exports GUI;
    exports DataBaseSQL;
    opens DataBaseSQL to javafx.fxml;
}