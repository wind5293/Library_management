module org.example.library_management {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens GUI to javafx.fxml;
    exports GUI;
    exports DataBaseSQL;
    opens DataBaseSQL to javafx.fxml;
    opens DocumentManager to javafx.base;
    exports GUI.AdminGUI;
    opens GUI.AdminGUI to javafx.fxml;
    opens User to javafx.base;
    exports GUI.UserGUI;
    opens GUI.UserGUI to javafx.fxml;
    exports GUI.BaseGUI;
    opens GUI.BaseGUI to javafx.fxml;
    exports AppVersion;
    opens AppVersion to javafx.fxml;

}