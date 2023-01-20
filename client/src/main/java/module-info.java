module com.project.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.logging;

    opens com.project.client.object to javafx.base;
    opens com.project.client.ui.addBook;
    opens com.project.client.ui.mainMenu;
    opens com.project.client.ui.loginMenu;
    exports com.project.client.object to com.fasterxml.jackson.databind;
    exports com.project.client.ui.addBook;
    exports com.project.client.ui.mainMenu;
    exports com.project.client.ui.loginMenu;
}