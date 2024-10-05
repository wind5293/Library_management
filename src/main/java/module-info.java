module org.example.library_management {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.library_management to javafx.fxml;
    exports org.example.library_management;
}