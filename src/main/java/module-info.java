module org.example.library_management {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens GUI to javafx.fxml;
    exports GUI;
}