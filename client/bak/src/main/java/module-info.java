module org.modernclient.javafx_test {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;

    opens org.modernclient.javafx_test;
    exports org.modernclient.javafx_test.model to com.fasterxml.jackson.databind;
}