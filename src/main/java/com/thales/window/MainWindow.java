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

import org.jenetics.util.RandomRegistry;

import com.thales.ga.ManifestOptimiser;
import com.thales.model.Destination;
import com.thales.model.Item;
import com.thales.model.Manifest;
import com.thales.model.Priority;
import com.thales.model.Store;
import com.thales.model.Urgency;
import com.thales.model.Vessel;
import com.thales.model.Vessel.Dimension;

import java.io.InputStream;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

public class MainWindow extends Application {

	final Pane mainPane = new StackPane();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Loady Boat");

		Scene scene = new Scene(mainPane, 1280, 800, true);

		 InputStream icon = this.getClass().getResourceAsStream("/icon.png");

		 Image image = new Image(icon);
		 primaryStage.getIcons().add(image);
		// Generate a random collection of store items.
		
		Store store = new Store();
		for (int i = 0; i < 3000; i++) {
			int p = RandomRegistry.getRandom().nextInt(Priority.values().length);
			int u = RandomRegistry.getRandom().nextInt(Urgency.values().length);
			int d = RandomRegistry.getRandom().nextInt(Destination.values().length);
			store.addItem(new Item("Item " + i, Priority.values()[p], Urgency.values()[u], Destination.values()[d]));
		}

		//Vessel vessel16 = new Vessel("Vessel 16", new Dimension(14, 50));
		 Vessel vessel7 = new Vessel("Vessel 7", new Dimension(10, 16));
		// Vessel vessel2 = new Vessel("Vessel 2", new Dimension(11, 19));
		ManifestOptimiser optimiser = new ManifestOptimiser(store, vessel7);
		VesselOptimizationView view = new VesselOptimizationView(optimiser);

		mainPane.getChildren().add(view);
		primaryStage.setScene(scene);
		primaryStage.show();

		Executors.newSingleThreadExecutor().execute(() -> view.go());
	}
}
