package com.project.client.ui.mainMenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * Class used to start up the main menu UI, as well as View List Of All Books functionality
 * @author Trọng Nhân
 */

public class MainApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 976, 568);
        stage.getIcons().add(new Image(Objects.requireNonNull(MainApplication
                        .class
                        .getResource("/com/project/client/icon/logo_white_blue.png"))
                .openStream()));
        stage.setTitle("FRA-UAS Library");
        stage.setScene(scene);
        stage.show();
    }
}