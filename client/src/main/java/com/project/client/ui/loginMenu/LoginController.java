package com.project.client.ui.loginMenu;

import com.project.client.RESTapiclients.LoginRESTRequest;
import com.project.client.object.accessToken;
import com.project.client.object.userAuth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Initial class that activates upon the initialization of the application.
 * Class is used to handle login request, update access token and return
 * different alerts for different unexpected results.
 * @author Trọng Nhân
 * @author Minh Duy
 */

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
    private TextField usernameInput;

    /**
     * Upon initialization, perform a check on two text boxes and login button
     */
    @FXML
    void initialize() {
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordInput != null : "fx:id=\"passwordInput\" was not injected: check your FXML file 'login.fxml'.";
        assert usernameInput != null : "fx:id=\"usernameInput\" was not injected: check your FXML file 'login.fxml'.";
        loginButton.setDefaultButton(true);
    }

    /**
     * Method that makes API request to the server for user credentials validation.
     * If success, alert is present. Otherwise, configure accessToken object with
     * received access token from response.
     * @param event Variable registered upon interacted by user, such as clicking.
     */
    @FXML
    private void login(ActionEvent event) {
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        Alert alert;
        if (username.isEmpty() || password.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter all fields and try again!");
        }
        else {
            userAuth userLogin = new userAuth(username, password);
            HttpResponse<String> response = LoginRESTRequest.loginRequest(userLogin);
            if (response == null || response.statusCode() != 200) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Incorrect username or password. Please try again.");
            } else {
                loginSuccess(event);
                String role;
                if (accessToken.getRoleID() == 1L)
                {
                    role = "Admin.";
                }
                else
                {
                    role = "User.";
                }
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText(
                        "Successfully logged in as user "
                        + userLogin.getUsername() +
                        ", access role: "
                        + role
                );
            }
        }
        alert.showAndWait();
    }

    /**
     * If login successfully, this method is used to re-direct the main UI screen to main menu,
     * as well as table for showing all Book objects.
     * @param event Variable registered upon interacted by user, such as clicking.
     */
    private void loginSuccess(ActionEvent event) {
        try {
            Stage staging = (Stage) loginButton.getScene().getWindow();
            staging.close();
            FXMLLoader fxmlLoader = new FXMLLoader(this
                    .getClass()
                    .getResource("/com/project/client/ui/mainMenu/main.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 976, 568);
            Stage stage = new Stage();
            stage.getIcons().add(new Image(Objects.requireNonNull(this
                            .getClass()
                            .getResource("/com/project/client/icon/logo_white_blue.png"))
                    .openStream()));
            stage.setTitle("FRA-UAS Library");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
}