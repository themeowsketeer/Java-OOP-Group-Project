package com.project.client.ui.addBook;

import com.project.client.RESTapiclients.BookRESTRequest;
import com.project.client.object.Author;
import com.project.client.object.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.net.http.HttpResponse;
import java.util.*;

public class AddBookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField authorInput;

    @FXML
    private TextField yearInput;

    @FXML
    private TextField editionInput;

    @FXML
    private TextField quantityInput;

    @FXML
    void initialize() {
        // button
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'addBook.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'addBook.fxml'.";

        // book input
        assert nameInput != null : "fx:id=\"nameInput\" was not injected: check your FXML file 'addBook.fxml'.";
        assert authorInput != null : "fx:id=\"authorInput\" was not injected: check your FXML file 'addBook.fxml'.";
        assert yearInput != null : "fx:id=\"yearInput\" was not injected: check your FXML file 'addBook.fxml'.";
        assert editionInput != null : "fx:id=\"editionInput\" was not injected: check your FXML file 'addBook.fxml'.";
        assert quantityInput != null : "fx:id=\"quantityInput\" was not injected: check your FXML file 'addBook.fxml'.";
    }


    @FXML
    private void addBook(ActionEvent event) throws NumberFormatException {
        try {
            String name = nameInput.getText();
            String authors = authorInput.getText();
            List<String> authorName = new ArrayList<>(Arrays.asList(authors.split(", ")));
            Set<Author> authorList = new HashSet<>();
            long authorID = 7;
            for (String s : authorName) {
                authorList.add(new Author(authorID, s));
                authorID++;
            }
            String releasedYear = yearInput.getText();
            String edition = editionInput.getText();
            String quantity = quantityInput.getText();

            if (name.isEmpty() ||
                    authors.isEmpty() ||
                    releasedYear.isEmpty() ||
                    edition.isEmpty() ||
                    quantity.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please enter all fields and try again!");
                alert.showAndWait();
            }

            Date now = new Date();
            String bookID = "5";
            Book book = new Book(bookID,
                    name,
                    authorList,
                    Long.parseLong(releasedYear),
                    Integer.parseInt(edition), Integer.parseInt(quantity),
                    now);
            HttpResponse<String> response = BookRESTRequest.addNewBook(book);
            assert response != null;
            int responseCode = response.statusCode();

            if (responseCode == 201) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Book has been added.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please try again.");
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
