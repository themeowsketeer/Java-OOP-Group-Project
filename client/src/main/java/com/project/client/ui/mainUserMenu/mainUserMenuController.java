package com.project.client.ui.mainUserMenu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.client.RESTapiclients.UserRESTRequest;
import com.project.client.object.User;
import com.project.client.ui.mainMenu.MainController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Class that defines actions for buttons presenting on the UI of View All Added Users
 * menu, handles API calls, deserializes JSON string format response, pushes up the information and
 * gives alerts when unexpected results occur.
 * @author Trọng Nhân
 * @author Minh Duy
 */

public class mainUserMenuController {

    private final ObservableList<User> users = observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addUserButton;

    @FXML
    private TableColumn<User, String> addressCol;

    @FXML
    private TableColumn<User, Date> birthdayCol;

    @FXML
    private Button bookMenu;

    @FXML
    private TableView<User> userTable;

    @FXML
    private Button logoutButton;

    @FXML
    private TableColumn<User, String> phoneCol;

    @FXML
    private Button refreshButton;

    @FXML
    private TableColumn<User, String> roleCol;

    @FXML
    private TableColumn<User, Long> userIdCol;

    @FXML
    private Button userMenu;

    @FXML
    private TableColumn<User, String> userNameCol;

    /**
     * Upon initialization, perform a check on all column of table containing User objects,
     * as well as all available button.
     */
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
        assert phoneCol != null : "fx:id=\"phoneCol\" was not injected: check your FXML file 'mainUserMenu.fxml'.";
        assert roleCol != null : "fx:id=\"roleCol\" was not injected: check your FXML file 'mainUserMenu.fxml'.";

    }

    /**
     * Method used to re-direct the main UI screen to main menu, as well as table for showing all Book objects.
     * @param event Variable registered upon interacted by user, such as clicking.
     */
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

    /**
     * Method used to re-direct main UI screen to menu containing table for showing all User objects.
     * @param event Variable registered upon interacted by user, such as clicking.
     */
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

    /**
     * Method used to open a new UI menu for making request to server
     * of adding new User object into the database.
     * @param event Variable registered upon interacted by user, such as clicking.
     */
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

    /**
     * Method used to log the user out of the application and re-direct to login UI.
     * @param event Variable registered upon interacted by user, such as clicking.
     */
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

    /**
     * Method used set each column of the table to capture value from each corresponding attribute
     * of JSON string format of User objects.
     */
    @FXML
    private void setTable() throws JsonProcessingException {
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        birthdayCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("roles"));
        updateData();
        userTable.setItems(users);
    }

    /**
     * Method used to update the columns of table with corresponding attributes
     * in which the Uer object provides.
     * @throws JsonProcessingException Exception throws when there's an error during processing JSON data,
     * such as data is NULL pointer
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    private void updateData() throws JsonProcessingException {
        HttpResponse<String> response = UserRESTRequest.getUserByID(String.valueOf(0));
        assert response != null;
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> userListDatabase = objectMapper.readValue(response.body(), new TypeReference<>() {});
        userTable.getItems().addAll(userListDatabase);
    }

    /**
     * Method that refreshes the table and update its content. This method must be called
     * upon accessing the menu for first time.
     * @param event Variable registered upon interacted by user, such as clicking.
     */
    @FXML
    private void refreshTable(ActionEvent event) {
        users.clear();
        try {
            setTable();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
