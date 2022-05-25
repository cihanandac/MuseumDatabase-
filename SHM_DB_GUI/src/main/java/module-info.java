module com.example.shm_db_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.shm_db_gui to javafx.fxml;
    exports com.example.shm_db_gui;
}