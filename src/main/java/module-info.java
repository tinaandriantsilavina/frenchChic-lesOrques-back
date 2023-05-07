module com.frenchchic {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.frenchchic to javafx.fxml;
    exports com.frenchchic;
    exports com.frenchchic.controller;
    opens com.frenchchic.controller to javafx.fxml;
}