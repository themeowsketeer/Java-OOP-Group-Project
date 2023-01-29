package com.project.client.ui.viewReturnedMenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class viewReturnedMenuApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(viewReturnedMenuApplication.class.getResource("viewReturnedMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 976, 568);
        stage.setTitle("FRA-UAS Library");
        stage.setScene(scene);
        stage.show();
    }
}