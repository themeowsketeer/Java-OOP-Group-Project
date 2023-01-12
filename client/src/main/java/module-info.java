module com.project.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens com.project.client to javafx.fxml;
    exports com.project.client;
    exports com.project.client.RESTapiclients;
}