module com.project.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.logging;

    opens com.project.client.object to javafx.base;
    opens com.project.client.ui.addBook to javafx.fxml, javafx.graphics;
    opens com.project.client.ui.mainMenu to javafx.fxml, javafx.graphics;
    opens com.project.client.ui.loginMenu to javafx.fxml, javafx.graphics;
    exports com.project.client.object to com.fasterxml.jackson.databind;
    exports com.project.client.ui.addBook;
    exports com.project.client.ui.mainMenu;
    exports com.project.client.ui.loginMenu;
}