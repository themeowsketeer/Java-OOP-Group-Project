package com.project.client.ui.viewReturnedMenu;

import com.project.client.object.accessToken;
import com.project.client.ui.loginMenu.LoginController;
import com.project.client.ui.mainMenu.MainController;
import com.project.client.ui.mainUserMenu.mainUserMenuController;
import com.project.client.ui.viewIssuedMenu.issueInfo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class viewReturnedMenuController {

    private final ObservableList<issueInfo> issuedList = observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<issueInfo, Date> returnedDateCol;

    @FXML
    private TableColumn<issueInfo, Long> bookIDCol;

    @FXML
    private Button bookMenu;

    @FXML
    private TableColumn<issueInfo, String> bookNameCol;

    @FXML
    private TableColumn<issueInfo, Long> borrowIDCol;

    @FXML
    private TableColumn<issueInfo, Date> issuedDateCol;

    @FXML
    private TableView<issueInfo> returnedTable;

    @FXML
    private Button logoutButton;

    @FXML
    private Button refreshButton;

    @FXML
    private TableColumn<issueInfo, Long> userIDCol;

    @FXML
    private Button userMenu;

    @FXML
    private TableColumn<issueInfo, String> userNameCol;

    @FXML
    private Button viewAllButton;


    @FXML
    void initialize() {
        assert returnedDateCol != null : "fx:id=\"actionCol\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert bookIDCol != null : "fx:id=\"bookIDCol\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert bookMenu != null : "fx:id=\"bookMenu\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert bookNameCol != null : "fx:id=\"bookNameCol\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert borrowIDCol != null : "fx:id=\"borrowIDCol\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert issuedDateCol != null : "fx:id=\"issuedDateCol\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert returnedTable != null : "fx:id=\"issuedTable\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert refreshButton != null : "fx:id=\"refreshButton\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert userIDCol != null : "fx:id=\"userIDCol\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert userMenu != null : "fx:id=\"userMenu\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert userNameCol != null : "fx:id=\"userNameCol\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert viewAllButton != null : "fx:id=\"viewAllButton\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";

        if (accessToken.getRoleID()==2L)
        {
            userMenu.setVisible(false);
            userMenu.setManaged(false);
        }
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
            FXMLLoader fxmlLoader = new FXMLLoader(mainUserMenuController.class.getResource("/com/project/client/ui/mainUserMenu/mainUserMenu.fxml"));
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
    private void openViewAllMenu (ActionEvent event) {
        try {
            Stage stage = (Stage) viewAllButton.getScene().getWindow();
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
    private void logout(ActionEvent event) {
        try {
            Stage staging = (Stage) logoutButton.getScene().getWindow();
            staging.close();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/com/project/client/ui/loginMenu/login.fxml"));
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

    @FXML
    private void refreshTable(ActionEvent event) {

    }
}