package com.project.client.ui.viewIssuedMenu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.client.RESTapiclients.IssueReturnBookRESTRequest;
import com.project.client.object.accessToken;
import com.project.client.ui.mainMenu.MainController;
import com.project.client.object.issueBookInfo;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static javafx.collections.FXCollections.observableArrayList;

public class viewIssuedMenuController {

    String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);

    private final ObservableList<issueBookInfo> issuedList = observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<issueBookInfo, Void> actionCol;

    @FXML
    private TableColumn<issueBookInfo, String> bookIDCol;

    @FXML
    private Button bookMenu;

    @FXML
    private TableColumn<issueBookInfo, String> bookNameCol;

    @FXML
    private TableColumn<issueBookInfo, String> borrowIDCol;

    @FXML
    private TableColumn<issueBookInfo, Date> issuedDateCol;

    @FXML
    private TableView<issueBookInfo> issuedTable;

    @FXML
    private Button logoutButton;

    @FXML
    private Button refreshButton;

    @FXML
    private TableColumn<issueBookInfo, String> userIDCol;

    @FXML
    private Button userMenu;

    @FXML
    private TableColumn<issueBookInfo, String> userNameCol;

    @FXML
    private Button viewAllButton;


    @FXML
    void initialize() {
        assert actionCol != null : "fx:id=\"actionCol\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert bookIDCol != null : "fx:id=\"bookIDCol\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert bookMenu != null : "fx:id=\"bookMenu\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert bookNameCol != null : "fx:id=\"bookNameCol\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert borrowIDCol != null : "fx:id=\"borrowIDCol\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert issuedDateCol != null : "fx:id=\"issuedDateCol\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert issuedTable != null : "fx:id=\"issuedTable\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert refreshButton != null : "fx:id=\"refreshButton\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert userIDCol != null : "fx:id=\"userIDCol\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert userMenu != null : "fx:id=\"userMenu\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert userNameCol != null : "fx:id=\"userNameCol\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";
        assert viewAllButton != null : "fx:id=\"viewAllButton\" was not injected: check your FXML file 'viewIssuedMenu.fxml'.";

        if (accessToken.getRoleID() == 2L) {
            userMenu.setVisible(false);
            userMenu.setManaged(false);
            actionCol.setVisible(false);
        }
    }

    @FXML
    private void openBookMenu(ActionEvent event) {
        try {
            Stage stage = (Stage) bookMenu.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(viewIssuedMenuController.class.getResource("/com/project/client/ui/mainMenu/main.fxml"));
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
    private void openUserMenu(ActionEvent event) {
        try {
            Stage stage = (Stage) userMenu.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(viewIssuedMenuController.class.getResource("/com/project/client/ui/mainUserMenu/mainUserMenu.fxml"));
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
    private void openViewAllMenu(ActionEvent event) {
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
            FXMLLoader fxmlLoader = new FXMLLoader(viewIssuedMenuController.class.getResource("/com/project/client/ui/loginMenu/login.fxml"));
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
    private void setTable () throws JsonProcessingException, ParseException {
        borrowIDCol.setCellValueFactory(new PropertyValueFactory<>("borrowId"));
        issuedDateCol.setCellValueFactory(new PropertyValueFactory<>("issueAt"));
        bookIDCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        bookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        Callback<TableColumn<issueBookInfo, Void>, TableCell<issueBookInfo, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<issueBookInfo, Void> call(final TableColumn<issueBookInfo, Void> param) {
                return new TableCell<>() {
                    private final Button returnButton = new Button("Return");
                    final Alert issueConfirm = new Alert(Alert.AlertType.CONFIRMATION);
                    {
                        returnButton.setOnAction((ActionEvent event) ->
                        {
                            issueBookInfo data = getTableView().getItems().get(getIndex());
                            issueConfirm.setHeaderText("Confirm that this book has been returned?");
                            issueConfirm.setContentText("Press ok to confirm / cancel to go back");
                            Optional<ButtonType> result = issueConfirm.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                returnTheBook(Long.parseLong(data.getBorrowId()));
                            }
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(returnButton);
                        }
                    }
                };
            }
        };
        actionCol.setCellFactory(cellFactory);
        updateData();
        issuedTable.setItems(issuedList);
    }

    private void updateData() throws JsonProcessingException, ParseException {
        HttpResponse<String> response;
        if (accessToken.getRoleID() == 2)
        {
            response = IssueReturnBookRESTRequest.getAllIssuedBookOfUser(accessToken.getRoleID());
        }
        else {
            response = IssueReturnBookRESTRequest.getAllIssuedBook();
        }
        assert response != null;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode listIssued = objectMapper.readTree(response.body());
        List<issueBookInfo> issuedOrderDatabase = new ArrayList<>();
        for (JsonNode issuedInfo : listIssued)
        {
            issueBookInfo issuedOrder = new issueBookInfo
                    (
                        issuedInfo.get("id").asText(),
                            simpleDateFormat.parse(issuedInfo.get("issuedAt").asText()),
                            issuedInfo.path("book").get("id").asText(),
                            issuedInfo.path("book").get("name").asText(),
                            issuedInfo.path("user").get("id").asText(),
                            issuedInfo.path("user").get("username").asText()
                    );
            issuedOrderDatabase.add(issuedOrder);
        }
        ObservableList<issueBookInfo> issuedOrderList = issuedTable.getItems();
        issuedOrderList.addAll(issuedOrderDatabase);
    }
    @FXML
    private void refreshTable(ActionEvent event) {
        issuedList.clear();
        try {
            setTable();
        } catch (JsonProcessingException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void returnTheBook(Long borrowedID) {
        {
            HttpResponse<String> response = IssueReturnBookRESTRequest
                    .returnBookFromUser(borrowedID);
            Alert alert;
            if(response == null)
            {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Empty response. Please try again.");
            }
            assert response != null;
            if(response.statusCode() == 401)
            {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Session timed out. Please log in again.");
            }
            else if (response.statusCode() == 200) {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Book has been returned to library");
            }
            else if (response.statusCode() == 400) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Book is out of stock");
            }
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("An error has occurred. Please try again");
            }
            alert.showAndWait();
        }
    }
}