package org.modernclient.javafx_test;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MyShapes extends Application{
    @Override
    public void start(Stage stage) {
// Create an Ellipse and set fill color
        Ellipse ellipse = new Ellipse(110,110);
        ellipse.setFill(Color.web("#301599"));
        Text text = new Text("My Shapes");
        text.setFont(new Font("Arial Bold", 24));
// Create a Text shape with font and size
        Group group = new Group(ellipse, text);
// Manually placing components is tedious and error-prone
        ellipse.setCenterX(175);
        ellipse.setCenterY(115);
        ellipse.setOnMouseClicked(mouseEvent -> System.out.println(mouseEvent.getSource().getClass() + " detected a click."));
        text.setX(175-(text.getLayoutBounds().getWidth()/2));
        text.setY(115+(text.getLayoutBounds().getHeight()/2));
        RotateTransition rotate = new RotateTransition(
                Duration.millis(1000), group);
        rotate.setToAngle(360);
        rotate.setFromAngle(0);
        rotate.setInterpolator(Interpolator.LINEAR);
        text.setOnMouseClicked(mouseEvent -> {
            if (rotate.getStatus().equals(Animation.Status.RUNNING)) {
                rotate.pause();
            }
            else { rotate.play(); }
            System.out.println(mouseEvent.getSource().getClass()
                    + " clicked.");
        });
        Scene scene = new Scene(group, 350, 230, Color.web("#B951d6"));
        stage.setTitle("MyShapes with JavaFX");
        stage.setScene(scene);
        group.setOnMouseClicked(mouseEvent -> {
            System.out.println(mouseEvent.getSource().getClass() + " detected a click.");
        });
        stage.show();
        // create a few toggle buttons
        ToggleButton tb1 = new ToggleButton("Toggle button 1");
        ToggleButton tb2 = new ToggleButton("Toggle button 2");
        ToggleButton tb3 = new ToggleButton("Toggle button 3");
    }
    public static void main(String[] args) {
        launch(args);
    }
}
