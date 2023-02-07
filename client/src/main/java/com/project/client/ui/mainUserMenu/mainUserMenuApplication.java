package com.project.client.ui.mainUserMenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Class used to start up the UI for View List Of All Added Users functionality
 * @author Trọng Nhân
 */

public class mainUserMenuApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this
                .getClass()
                .getResource("mainUserMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 976, 568);
        stage.getIcons().add(new Image(Objects.requireNonNull(this
                        .getClass()
                        .getResource("/com/project/client/icon/logo_white_blue.png"))
                .openStream()));
        stage.setTitle("FRA-UAS Library");
        stage.setScene(scene);
        stage.show();
    }
}