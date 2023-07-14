module com.example.storemanagementsystemfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.storemanagementsystemfx to javafx.fxml;
    opens com.example.storemanagementsystemfx.controller to javafx.fxml;
    opens com.example.storemanagementsystemfx.model to javafx.fxml;
    opens com.example.storemanagementsystemfx.model.holder to javafx.fxml;

    exports com.example.storemanagementsystemfx;
    exports com.example.storemanagementsystemfx.controller;
    exports com.example.storemanagementsystemfx.model;
    exports com.example.storemanagementsystemfx.model.holder;

}