package com.project.client.ui.addUser;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
