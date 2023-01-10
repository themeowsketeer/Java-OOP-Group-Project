package org.modernclient.javafx_test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.text.SimpleDateFormat;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.effect.DropShadow;
import javafx.geometry.Pos;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.TimeZone;

import org.modernclient.javafx_test.model.Model;
import org.modernclient.javafx_test.model.Weather;

public class WeatherApp extends Application {
    private static final String API_KEY = "e4d8849826c2311791bc18c4339776b3";
    private static final String CITY = "Frankfurt";
    private ImageView imageView;
    private Label weatherLabel;
    private Label descriptionLabel;
    private Label tempLabel;
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm a z");

    @Override
    public void start(Stage stage) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(CITY));
        imageView = new ImageView();
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);
        imageView.setEffect(new DropShadow());
        Label label = new Label("The weather in " + CITY);
        weatherLabel = new Label();
        descriptionLabel = new Label();
        descriptionLabel.getStyleClass().add("desc");
        tempLabel = new Label();
        tempLabel.getStyleClass().add("temp");
        label.setMinWidth(80);
        label.setMinHeight(50);
        Button sample_tap = new Button("Press to check");
        Popup sample_popup = new Popup();
        sample_popup.getContent().add(label);
        Alert alert_sample = new Alert(Alert.AlertType.NONE);
        EventHandler<ActionEvent> event =
                e -> {
                    alert_sample.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert_sample.setContentText("No weather information shown. Please debug the program.");
                    alert_sample.show();
                };
        sample_tap.setOnAction(event);
        VBox root = new VBox(10, label, imageView, weatherLabel, descriptionLabel, tempLabel,sample_tap);
        root.setAlignment(Pos.CENTER);
        Scene sceneMain = new Scene(root, 400, 800);
        sceneMain.getStylesheets().add(
                Objects.requireNonNull(WeatherApp.class.getResource("/styles.css")).toExternalForm());
        stage.setScene(sceneMain);
        stage.setTitle("The Weather App");
        stage.show();
        retrieveWeather();
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    void retrieveWeather() {
        try {
            String restUrl =
                    "https://api.openweathermap.org/data/2.5/weather?appid="
                            + API_KEY
                            + "&q="
                            + CITY
                            + "&units=metric";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("accept","application/xml")
                    .uri(URI.create(restUrl))
                    .build();
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Model model = objectMapper.readValue(response.body(), new TypeReference<>() {});
            System.out.println(response.body());
            updateModel(model);
        } catch (Throwable e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
    private void updateModel(Model model) throws MalformedURLException, URISyntaxException {
        if (model != null) {
            if (!model.getWeather().isEmpty()) {
                Weather w = model.getWeather().get(0);
                imageView.setImage(new Image(
                        new URL("https://openweathermap.org/img/wn/" + w.getIcon() + "@2x.png")
                                .toURI()
                                .toString()));
                weatherLabel.setText(w.getMain());
                descriptionLabel.setText(w.getDescription());
            }
            tempLabel.setText(String.format("""
                             City: %s - Country: %s
                            
                             Cloud Density: %s%%
                             
                             Longitude: %.2f / Latitude: %.2f
                             
                             Temperature: %.2f °C / Actual: %.2f °C
                             
                             Highest Temperature: %.2f °C
                             Lowest Temperature: %.2f °C
                             
                             Humidity: %.1f%%
                             Wind Speed: %.2f m/s
                             Wind Direction: %.2f degree
                             Wind Gust: %.2f m/s
                             
                             Dawn: %s
                             Dusk: %s""",
                    model.getName(),model.getSys().getCountry(),
                    model.getClouds().getAll(),
                    model.getCoord().getLon(),model.getCoord().getLat(),
                    model.getMain().getTemp(),model.getMain().getFeels_like(),
                    model.getMain().getTemp_max(),
                    model.getMain().getTemp_min(),
                    model.getMain().getHumidity(),
                    model.getWind().getSpeed(),
                    model.getWind().getDeg(),
                    model.getWind().getGust(),
                    dateFormat.format(new Date(model.getSys().getSunrise()*1000)),
                    dateFormat.format(new Date(model.getSys().getSunset()*1000))));
        }
    }
}