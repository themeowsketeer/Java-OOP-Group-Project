package com.project.client.ui.mainUserMenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.project.client.ui.mainMenu.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class mainUserMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addUserButton;

    @FXML
    private TableColumn<?, ?> addressCol;

    @FXML
    private TableColumn<?, ?> birthdayCol;

    @FXML
    private Button bookMenu;

    @FXML
    private TableView<?> userTable;

    @FXML
    private TableColumn<?, ?> civilIdCol;

    @FXML
    private Button logoutButton;

    @FXML
    private TableColumn<?, ?> phoneCol;

    @FXML
    private Button refreshButton;

    @FXML
    private TableColumn<?, ?> roleCol;

    @FXML
    private TableColumn<?, ?> userIdCol;

    @FXML
    private Button userMenu;

    @FXML
    private TableColumn<?, ?> userNameCol;

    @FXML
    void initialize() {
        // button
        assert addUserButton != null : "fx:id=\"addUserButton\" was not injected: check your FXML file 'mainUserMenu.fxml'.";
        assert refreshButton != null : "fx:id=\"refreshButton\" was not injected: check your FXML file 'mainUserMenu.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'mainUserMenu.fxml'.";

        //menu
        assert bookMenu != null : "fx:id=\"bookMenu\" was not injected: check your FXML file 'mainUserMenu.fxml'.";
        assert userMenu != null : "fx:id=\"userMenu\" was not injected: check your FXML file 'mainUserMenu.fxml'.";

        // user table
        assert userTable != null : "fx:id=\"bookTable\" was not injected: check your FXML file 'mainUserMenu.fxml'.";
        assert userIdCol != null : "fx:id=\"userIdCol\" was not injected: check your FXML file 'mainUserMenu.fxml'.";
        assert userNameCol != null : "fx:id=\"userNameCol\" was not injected: check your FXML file 'mainUserMenu.fxml'.";
        assert birthdayCol != null : "fx:id=\"birthdayCol\" was not injected: check your FXML file 'mainUserMenu.fxml'.";
        assert addressCol != null : "fx:id=\"addressCol\" was not injected: check your FXML file 'mainUserMenu.fxml'.";
        assert civilIdCol != null : "fx:id=\"civilIdCol\" was not injected: check your FXML file 'mainUserMenu.fxml'.";
        assert phoneCol != null : "fx:id=\"phoneCol\" was not injected: check your FXML file 'mainUserMenu.fxml'.";
        assert roleCol != null : "fx:id=\"roleCol\" was not injected: check your FXML file 'mainUserMenu.fxml'.";

    }

    @FXML
    private void openBookMenu (ActionEvent event) {
        try {
            Stage stage = (Stage) bookMenu.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/com/project/client/ui/mainMenu/main.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("FRA-UAS Library");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    @FXML
    private void openUserMenu (ActionEvent event) {
        try {
            Stage stage = (Stage) userMenu.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/com/project/client/ui/mainUserMenu/mainUserMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("FRA-UAS Library");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    @FXML
    private void openAddUser(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/com/project/client/ui/addUser/addUser.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 400);
            Stage stage = new Stage();
            stage.setTitle("Add user");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
    @FXML
    private void logout(ActionEvent event) {
        try {
            Stage staging = (Stage) logoutButton.getScene().getWindow();
            staging.close();
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/com/project/client/ui/loginMenu/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
}
