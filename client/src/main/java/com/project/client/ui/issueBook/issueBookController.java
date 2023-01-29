package com.project.client.ui.issueBook;

import com.project.client.RESTapiclients.issueBookRESTRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.http.HttpResponse;

public class issueBookController {

    @FXML
    private Button addButton;

    @FXML
    private TextField bookIDinput;

    @FXML
    private TextField borrowerIDinput;

    @FXML
    private Button cancelButton;

    @FXML
    void initialize() {
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'issueBook.fxml'.";
        assert bookIDinput != null : "fx:id=\"bookIDinput\" was not injected: check your FXML file 'issueBook.fxml'.";
        assert borrowerIDinput != null : "fx:id=\"borrowerIDinput\" was not injected: check your FXML file 'issueBook.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'issueBook.fxml'.";

    }

    @FXML
    private void issueBook(ActionEvent event) throws NumberFormatException {
        try {
            String borrowerID = borrowerIDinput.getText();
            String borrowedBookID = bookIDinput.getText();
            if (borrowerID.isEmpty() ||
                    borrowedBookID.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please enter all fields and try again!");
                alert.showAndWait();
            }

            HttpResponse<String> response = issueBookRESTRequest.
                    issueBookToUser(Long.parseLong(borrowerID), borrowedBookID);
            assert response != null;
            int responseCode = response.statusCode();

            if (responseCode == 200) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Book has been issued successfully.");
                alert.showAndWait();
            }
            else if (responseCode == 400) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Book is out of stock.");
                alert.showAndWait();
            }
            else {
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
