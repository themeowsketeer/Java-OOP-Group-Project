package com.project.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addBookButton;

    @FXML
    private Button bookMenu;

    @FXML
    private Button logoutButton;

    @FXML
    private Button userMenu;

    @FXML
    private TableView<?> bookTable;
    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> authorCol;

    @FXML
    private TableColumn<?, ?> quantityCol;

    @FXML
    private TableColumn<?, ?> yearCol;

    @FXML
    private TableColumn<?, ?> editionCol;

    @FXML
    private TableColumn<?, ?> uploadCol;

    @FXML
    private TableColumn<?, ?> actionCol;

    @FXML
    void initialize() {
        assert actionCol != null : "fx:id=\"actionCol\" was not injected: check your FXML file 'main.fxml'.";
        assert addBookButton != null : "fx:id=\"addBookButton\" was not injected: check your FXML file 'main.fxml'.";
        assert authorCol != null : "fx:id=\"authorCol\" was not injected: check your FXML file 'main.fxml'.";
        assert bookMenu != null : "fx:id=\"bookMenu\" was not injected: check your FXML file 'main.fxml'.";
        assert bookTable != null : "fx:id=\"bookTable\" was not injected: check your FXML file 'main.fxml'.";
        assert editionCol != null : "fx:id=\"editionCol\" was not injected: check your FXML file 'main.fxml'.";
        assert idCol != null : "fx:id=\"idCol\" was not injected: check your FXML file 'main.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'main.fxml'.";
        assert nameCol != null : "fx:id=\"nameCol\" was not injected: check your FXML file 'main.fxml'.";
        assert quantityCol != null : "fx:id=\"quantityCol\" was not injected: check your FXML file 'main.fxml'.";
        assert uploadCol != null : "fx:id=\"uploadCol\" was not injected: check your FXML file 'main.fxml'.";
        assert userMenu != null : "fx:id=\"userMenu\" was not injected: check your FXML file 'main.fxml'.";
        assert yearCol != null : "fx:id=\"yearCol\" was not injected: check your FXML file 'main.fxml'.";
    }

    @FXML
    private void openAddBook(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("addBook.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 400);
            Stage stage = new Stage();
            stage.setTitle("Add book");
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
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("login.fxml"));
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
