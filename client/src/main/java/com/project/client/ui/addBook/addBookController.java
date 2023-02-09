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
import java.util.Random;

/**
 * Class that accepts information of a Book object and make API calls to the server
 * in order to add that Book object to the database.
 * @author Trọng Nhân
 * @author Minh Duy
 */
public class addBookController {

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

    /**
     * Upon initialization, perform a check on text boxes and two buttons
     */
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

    /**
     * Method used to initiate a Book object with sufficient and valid information from text boxes
     * and make API calls to server in order to add that Book object to the database. Method also handle
     * different alert pop-ups for different results, depending on status code received or type of response itself.
     * @param event Variable registered upon interacted by user, such as clicking.
     * @throws NumberFormatException Exception thrown when one of the text box is left empty.
     */
    @FXML
    private void addBook(ActionEvent event) throws NumberFormatException {
        try {
            String name = nameInput.getText();
            String authors = authorInput.getText();
            List<String> authorName = new ArrayList<>(Arrays.asList(authors.split(", ")));
            Set<Author> authorList = new HashSet<>();
            for (String nameAuth : authorName) {
                authorList.add(new Author(nameAuth));
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
            else {
                Random random = new Random();

                Date now = new Date();
                String bookID = String.valueOf(random.nextInt(1000));
                Book book = new Book(bookID,
                        name,
                        authorList,
                        Long.parseLong(releasedYear),
                        Integer.parseInt(edition), Integer.parseInt(quantity),
                        now);
                HttpResponse<String> response = BookRESTRequest.addNewBook(book);
                assert response != null;
                int responseCode = response.statusCode();

                Alert alert;
                if (responseCode == 201) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Success");
                    alert.setContentText("Book has been added.");
                    closeWindow(event);
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Procedure failed");
                    alert.setContentText("Please try again.");
                }
                alert.showAndWait();
            }
        }
        catch (NumberFormatException numEx)
        {
            System.out.println("Empty space expected.");
        }
    }

    /**
     * Method used to close the Add Book UI menu
     * @param event Variable registered upon interacted by user, such as clicking.
     */
    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
