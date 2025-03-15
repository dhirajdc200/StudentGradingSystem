module StudentGradingSystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports application;
    opens application to javafx.fxml;
}