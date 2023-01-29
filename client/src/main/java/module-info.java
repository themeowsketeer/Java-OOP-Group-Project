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
    opens com.project.client.ui.addUser to javafx.fxml, javafx.graphics;
    opens com.project.client.ui.mainMenu to javafx.fxml, javafx.graphics;
    opens com.project.client.ui.mainUserMenu to javafx.fxml, javafx.graphics;
    opens com.project.client.ui.viewIssuedMenu to javafx.fxml, javafx.graphics;
    opens com.project.client.ui.viewReturnedMenu to javafx.fxml, javafx.graphics;
    opens com.project.client.ui.issueBook to javafx.fxml, javafx.graphics;
    opens com.project.client.ui.loginMenu to javafx.fxml, javafx.graphics;
    exports com.project.client.object to com.fasterxml.jackson.databind;
    exports com.project.client.ui.addBook;
    exports com.project.client.ui.addUser;
    exports com.project.client.ui.mainMenu;
    exports com.project.client.ui.mainUserMenu;
    exports com.project.client.ui.issueBook;
    exports com.project.client.ui.viewIssuedMenu;
    exports com.project.client.ui.viewReturnedMenu;
    exports com.project.client.ui.loginMenu;
}