package com.project.client.ui.loginMenu;

import com.project.client.RESTapiclients.LoginRESTRequest;
import com.project.client.object.userAuth;
import com.project.client.ui.mainMenu.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameInput;

    @FXML
    void initialize() {
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordInput != null : "fx:id=\"passwordInput\" was not injected: check your FXML file 'login.fxml'.";
        assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'login.fxml'.";
        assert usernameInput != null : "fx:id=\"usernameInput\" was not injected: check your FXML file 'login.fxml'.";
        loginButton.setDefaultButton(true);
    }

    @FXML
    private void login(ActionEvent event) {
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        Alert alert;
        if (username.isEmpty() || password.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter all fields and try again!");
            alert.showAndWait();
        }
        userAuth user = new userAuth(username, password);
        HttpResponse<String> response = LoginRESTRequest.loginRequest(user);
        if (response == null || response.statusCode() != 200) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Incorrect username or password. Please try again.");
            alert.showAndWait();
        } else {
            loginSuccess(event);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Successfully logged in.");
            alert.showAndWait();
        }
    }

    private void loginSuccess(ActionEvent event) {
        try {
            Stage staging = (Stage) loginButton.getScene().getWindow();
            staging.close();
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("main.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 976, 568);
            Stage stage = new Stage();
            stage.setTitle("FRA-UAS Library");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
}