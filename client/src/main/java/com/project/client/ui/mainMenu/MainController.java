package com.project.client.ui.mainMenu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.client.RESTapiclients.BookRESTRequest;
import com.project.client.object.Book;
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

public class MainController {

    private ObservableList<Book> book = observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addBookButton;

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

    //@FXML
    //private TableColumn<?, ?> actionCol;

    @FXML
    void initialize() throws JsonProcessingException {
        //assert actionCol != null : "fx:id=\"actionCol\" was not injected: check your FXML file 'main.fxml'.";
        assert addBookButton != null : "fx:id=\"addBookButton\" was not injected: check your FXML file 'main.fxml'.";
        assert refreshButton != null : "fx:id=\"addBookButton\" was not injected: check your FXML file 'main.fxml'.";
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
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/com/project/client/ui/addBook/addBook.fxml"));
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

    @FXML
    private void setTable() throws JsonProcessingException {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("releasedYear"));
        editionCol.setCellValueFactory(new PropertyValueFactory<>("edition"));
        uploadCol.setCellValueFactory(new PropertyValueFactory<>("placedAt"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        updateData();
        bookTable.setItems(book);
    }

    private void updateData() throws JsonProcessingException {
        HttpResponse<String> response = BookRESTRequest.getBookByID(String.valueOf(0));
        assert response != null;
        ObjectMapper objectMapper = new ObjectMapper();
        List<Book> bookListDatabase = objectMapper.readValue(response.body(), new TypeReference<>() {
        });
        ObservableList<Book> bookList = bookTable.getItems();
        bookList.addAll(bookListDatabase);
    }

    @FXML
    private void refreshTable(ActionEvent event) {
        book.clear();
        try {
            setTable();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}