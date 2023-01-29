package com.project.client.ui.addUser;

import com.project.client.RESTapiclients.UserRESTRequest;
import com.project.client.object.Role;
import com.project.client.object.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

public class addUserController {

    ObservableList<String> roleList = FXCollections.observableArrayList("ADMIN", "USER");

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private TextField addressInput;

    @FXML
    private TextField birthdayInput;

    @FXML
    private Button cancelButton;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField phoneInput;

    @FXML
    private TextField userNameInput;

    @FXML
    private ChoiceBox<String> roleInput;

    @FXML
    void initialize() {
        // button
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'addUser.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'addUser.fxml'.";

        // user input
        assert userNameInput != null : "fx:id=\"userNameInput\" was not injected: check your FXML file 'addUser.fxml'.";
        assert birthdayInput != null : "fx:id=\"birthdayInput\" was not injected: check your FXML file 'addUser.fxml'.";
        assert addressInput != null : "fx:id=\"addressInput\" was not injected: check your FXML file 'addUser.fxml'.";
        assert passwordInput != null : "fx:id=\"civilIdInput\" was not injected: check your FXML file 'addUser.fxml'.";
        assert phoneInput != null : "fx:id=\"phoneInput\" was not injected: check your FXML file 'addUser.fxml'.";
        assert roleInput != null : "fx:id=\"roleInput\" was not injected: check your FXML file 'addUser.fxml'.";
        roleInput.setItems(roleList);
        roleInput.setValue("USER");
        birthdayInput.setPromptText("Format: yyyy-MM-dd");
        birthdayInput.setFocusTraversable(false);
    }

    @FXML
    private void addUser(ActionEvent event) throws NumberFormatException {
        try {
            String username = userNameInput.getText();
            String birthday = birthdayInput.getText();
            String address = addressInput.getText();
            String password = passwordInput.getText();
            String phoneNumber = phoneInput.getText();
            String role = roleInput.getValue();
            if (username.isEmpty() ||
                    birthday.isEmpty() ||
                    address.isEmpty() ||
                    password.isEmpty() ||
                    phoneNumber.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please enter all fields and try again!");
                alert.showAndWait();
            }
            else
            {
                Set<Role> userRole = new HashSet<>();
                if (role.equals("ADMIN")) {
                    userRole.add(new Role(1L, role));
                } else if (role.equals("USER")) {
                    userRole.add(new Role(2L, role));
                }
                Random random = new Random();
                long userID = random.nextLong(1000);

                User user = new User(userID, username, password, birthday, address, phoneNumber, userRole);
                HttpResponse<String> response = UserRESTRequest.addNewUser(user);

                assert response != null;
                int responseCode = response.statusCode();
                Alert alert;
                if (responseCode == 201) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Success");
                    alert.setContentText("User has been added.");
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Procedure failed");
                    alert.setContentText("Please try again. Error code: " + responseCode);
                }
                alert.showAndWait();
            }
        }
        catch (NumberFormatException numEx)
        {
            System.out.println("Empty space expected.");
        }
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
