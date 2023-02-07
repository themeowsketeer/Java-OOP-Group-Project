package com.project.client.ui.addBook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Class used to start up the UI for Add Book functionality
 * @author Trọng Nhân
 */

public class addBookApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this
                .getClass()
                .getResource("addBook.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        stage.getIcons().add(new Image(Objects.requireNonNull(this
                        .getClass()
                        .getResource("/com/project/client/icon/logo_white_blue.png"))
                .openStream()));
        stage.setTitle("Add book");
        stage.setScene(scene);
        stage.show();
    }
}