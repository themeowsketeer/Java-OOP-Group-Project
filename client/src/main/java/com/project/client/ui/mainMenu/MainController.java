package com.project.client.ui.mainMenu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.client.RESTapiclients.BookRESTRequest;
import com.project.client.RESTapiclients.IssueReturnBookRESTRequest;
import com.project.client.RESTapiclients.UserRESTRequest;
import com.project.client.object.Book;
import com.project.client.object.accessToken;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.*;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Class that defines actions for buttons presenting on the UI of View All Available Book
 * menu, handles API calls, deserializes JSON string format response, pushes up the information and
 * gives alerts when unexpected results occur.
 * <p>
 * This is also the very first UI menu that greets the user upon logging in, as well as providing much
 * access to other functionalities
 * @author Trọng Nhân
 * @author Minh Duy
 */
public class MainController {

    private final ObservableList<Book> book = observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addBookButton;

    @FXML
    private Button viewIssuedButton;

    @FXML
    private Button viewReturnedButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Button bookMenu;

    @FXML
    private Button logoutButton;

    @FXML
    private Button userMenu;

    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> idCol;

    @FXML
    private TableColumn<Book, String> nameCol;

    @FXML
    private TableColumn<Book, String> authorCol;

    @FXML
    private TableColumn<Book, Integer> quantityCol;

    @FXML
    private TableColumn<Book, Long> yearCol;

    @FXML
    private TableColumn<Book, Integer> editionCol;

    @FXML
    private TableColumn<Book, Date> uploadCol;

    @FXML
    private TableColumn<Book, Void> actionCol;

    /**
     * Upon initialization, perform a check on all column of table containing Book objects,
     * as well as all available button.
     */
    @FXML
    void initialize() {
        // buttons
        assert addBookButton != null : "fx:id=\"addBookButton\" was not injected: check your FXML file 'main.fxml'.";
        assert viewIssuedButton != null : "fx:id=\"viewIssuedButton\" was not injected: check your FXML file 'main.fxml'.";
        assert viewReturnedButton != null : "fx:id=\"viewReturnedButton\" was not injected: check your FXML file 'main.fxml'.";
        assert refreshButton != null : "fx:id=\"addBookButton\" was not injected: check your FXML file 'main.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'main.fxml'.";

        //menu
        assert bookMenu != null : "fx:id=\"bookMenu\" was not injected: check your FXML file 'main.fxml'.";
        assert userMenu != null : "fx:id=\"userMenu\" was not injected: check your FXML file 'main.fxml'.";

        // book table
        assert bookTable != null : "fx:id=\"bookTable\" was not injected: check your FXML file 'main.fxml'.";
        assert idCol != null : "fx:id=\"idCol\" was not injected: check your FXML file 'main.fxml'.";
        assert nameCol != null : "fx:id=\"nameCol\" was not injected: check your FXML file 'main.fxml'.";
        assert authorCol != null : "fx:id=\"authorCol\" was not injected: check your FXML file 'main.fxml'.";
        assert quantityCol != null : "fx:id=\"quantityCol\" was not injected: check your FXML file 'main.fxml'.";
        assert yearCol != null : "fx:id=\"yearCol\" was not injected: check your FXML file 'main.fxml'.";
        assert editionCol != null : "fx:id=\"editionCol\" was not injected: check your FXML file 'main.fxml'.";
        assert uploadCol != null : "fx:id=\"uploadCol\" was not injected: check your FXML file 'main.fxml'.";
        assert actionCol != null : "fx:id=\"actionCol\" was not injected: check your FXML file 'main.fxml'.";

        /**
         * If the user role is USER, user cannot have access to Users List menu, View
         * List of Return Book Order button, Borrow Book button and Add Book functionality.
         */
        if (accessToken.getRoleID()==2L)
        {
            addBookButton.setVisible(false);
            addBookButton.setManaged(false);
            userMenu.setVisible(false);
            userMenu.setManaged(false);
            actionCol.setVisible(false);
            viewReturnedButton.setVisible(false);
            viewReturnedButton.setManaged(false);
        }
    }

    /**
     * Method used to re-direct the main UI screen to main menu, as well as table for showing all Book objects.
     * @param event Variable registered upon interacted by user, such as clicking.
     */
    @FXML
    private void openBookMenu (ActionEvent event) {
        try {
            Stage stage = (Stage) bookMenu.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainController
                    .class
                    .getResource("/com/project/client/ui/mainMenu/main.fxml"));
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
            FXMLLoader fxmlLoader = new FXMLLoader(MainController
                    .class
                    .getResource("/com/project/client/ui/mainUserMenu/mainUserMenu.fxml"));
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
     * Method used to re-direct main UI screen to menu containing table for showing all issueBookInfo objects.
     * @param event Variable registered upon interacted by user, such as clicking.
     */
    @FXML
    private void openViewIssuedMenu (ActionEvent event) {
        try {
            Stage stage = (Stage) viewIssuedButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainController
                    .class
                    .getResource("/com/project/client/ui/viewIssuedMenu/viewIssuedMenu.fxml"));
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
     * Method used to re-direct main UI screen to menu containing table for showing all returnBookInfo objects.
     * @param event Variable registered upon interacted by user, such as clicking.
     */
    @FXML
    private void openViewReturnedMenu (ActionEvent event) {
        try {
            Stage stage = (Stage) viewReturnedButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/com/project/client/ui/viewReturnedMenu/viewReturnedMenu.fxml"));
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
     * of adding new Book object into the database.
     * @param event Variable registered upon interacted by user, such as clicking.
     */
    @FXML
    private void openAddBook(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainController
                    .class
                    .getResource("/com/project/client/ui/addBook/addBook.fxml"));
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

    /**
     * Method used to log the user out of the application and re-direct to login UI.
     * @param event Variable registered upon interacted by user, such as clicking.
     */
    @FXML
    private void logout(ActionEvent event) {
        try {
            Stage staging = (Stage) logoutButton.getScene().getWindow();
            staging.close();
            FXMLLoader fxmlLoader = new FXMLLoader(MainController
                    .class
                    .getResource("/com/project/client/ui/loginMenu/login.fxml"));
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
     * of JSON string format of Book objects.
     * Action column is also set up independently to only show Borrow Book buttons for any
     * object that is available and present on the table. Unique to user with Admin role only.
     */
    @FXML
    private void setTable() throws JsonProcessingException {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("releasedYear"));
        editionCol.setCellValueFactory(new PropertyValueFactory<>("edition"));
        uploadCol.setCellValueFactory(new PropertyValueFactory<>("placedAt"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // actionCol
        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                return new TableCell<>() {
                    private final Button borrowButton = new Button("Issue");

                    private final List<String>userLightInfoList;

                    {
                        try {
                            userLightInfoList = setUsersLightList();
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    /**
                     * Upon clicking Issue Book button, provide the user with drop-down list of
                     * valid user to be issued with Book on same row with the button.
                     */
                    final ChoiceDialog issueConfirm = new ChoiceDialog(userLightInfoList.get(0), userLightInfoList);
                    {
                        borrowButton.setOnAction((ActionEvent event) ->
                        {
                            Book data = getTableView().getItems().get(getIndex());
                            issueConfirm.setHeaderText("To whose ID you want to issue this book: " + data.getName()+ " ?");
                            issueConfirm.setContentText("Select user ID along username: ");
                            Optional result = issueConfirm.showAndWait();
                            String userInfo = (String) issueConfirm.getSelectedItem();
                            if (result.isPresent())
                            {
                                String[] userInfoArr = userInfo.split(" ");
                                String userId = userInfoArr[0];
                                sendBookToUser(Long.parseLong(userId),(data.getId()));
                            }
                            else
                            {
                                issueConfirm.close();
                            }
                        });
                    }

                    /**
                     * Update the button to every available object showed on table
                     * @param item
                     * @param empty
                     */
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(borrowButton);
                        }
                    }
                };
            }
        };
        actionCol.setCellFactory(cellFactory);
        updateData();
        bookTable.setItems(book);
    }

    /**
     * Method used to update the columns of table with corresponding attributes
     * in which the issueBookInfo object provides.
     * @throws JsonProcessingException Exception thrown when the response is not a valid JSON string format.
     */
    private void updateData() throws JsonProcessingException {
        HttpResponse<String> response = BookRESTRequest.getBookByID(String.valueOf(0));
        assert response != null;
        ObjectMapper objectMapper = new ObjectMapper();
        List<Book> bookListDatabase = objectMapper.readValue(response.body(), new TypeReference<>() {
        });
        bookTable.getItems().addAll(bookListDatabase);
    }

    /**
     * Method that refreshes the table and update its content. This method must be called
     * upon accessing the menu for first time.
     * @param event Variable registered upon interacted by user, such as clicking.
     */
    @FXML
    private void refreshTable(ActionEvent event) {
        book.clear();
        try {
            setTable();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that provides the drop-down list of available user for the pop-up
     * used when interacting with Issue Book button.
     * @throws JsonProcessingException Exception throws when response from API call is
     * not a valid JSON string format.
     */
    private List<String> setUsersLightList() throws JsonProcessingException {
        List<String> usersLightList = new ArrayList<>();
        HttpResponse<String> responseAllUser = UserRESTRequest.getUserByID(String.valueOf(0));
        assert responseAllUser != null;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode JSONArrayUser = objectMapper.readTree(responseAllUser.body());
        for (JsonNode detail : JSONArrayUser)
        {
            String IdAndUsername = detail.get("id").asText() + " - " + detail.get("username").asText();
            usersLightList.add(IdAndUsername);
        }
        return usersLightList;
    }

    /**
     * Method that sends request to the server in order to make a Book Issue API calls.
     * Response provided has its status code dealt with and provides pop-up alert for each
     * unique code.
     * @param userID Decide which user will receive the book
     * @param bookID Decide which book the user with corresponding user ID will receive
     */
    private void sendBookToUser(long userID, String bookID)
    {
        HttpResponse<String> response = IssueReturnBookRESTRequest
                .issueBookToUser(userID,bookID);
        Alert alert;
        if (response == null)
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("User not found");
            alert.setContentText("User ID not found or illegal ID expected. Please try again.");
        }
        if((response != null ? response.statusCode() : 0) == 401)
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unauthorized request");
            alert.setContentText("Session timed out. Please log in again.");
        }
        else if ((response != null ? response.statusCode() : 0) == 200) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Success");
            alert.setContentText("Book has been issued to user");
        }
        else if ((response != null ? response.statusCode() : 0) == 400) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Book is out of stock");
        }
        else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Procedure failed");
            alert.setContentText("An error has occurred. Please try again");
        }
        alert.showAndWait();
    }
}