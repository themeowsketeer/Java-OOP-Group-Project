module com.project.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.logging;

    opens com.project.client to javafx.fxml;
    exports com.project.client.object to com.fasterxml.jackson.databind;
    exports com.project.client to javafx.graphics;
}