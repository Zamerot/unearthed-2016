package com.thales.window;/**
 * Created by Administrator on 8/04/2016.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.InputStream;
import java.util.ResourceBundle;

public class MainWindow extends Application {

    final Pane mainPane = new StackPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Loady Boat");

        Scene scene = new Scene(mainPane, 1280, 800, true);

        InputStream icon = this.getClass().getResourceAsStream("/icon.png");

        Image image = new Image(icon);
        primaryStage.getIcons().add(image);

        mainPane.getChildren().add(new VesselOptimizationView());
        primaryStage.setScene(scene);





        primaryStage.show();

    }
}
