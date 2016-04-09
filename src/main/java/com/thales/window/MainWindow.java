package com.thales.window;/**
							* Created by Administrator on 8/04/2016.
							*/

import java.util.concurrent.Executors;

import org.jenetics.util.RandomRegistry;

import com.thales.ga.ManifestOptimiser;
import com.thales.model.Destination;
import com.thales.model.Item;
import com.thales.model.Priority;
import com.thales.model.Store;
import com.thales.model.Urgency;
import com.thales.model.Vessel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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

		ManifestOptimiser optimiser = new ManifestOptimiser(store, Vessel.VESSEL16);
		VesselOptimizationView view = new VesselOptimizationView(optimiser);

		mainPane.getChildren().add(view);
		primaryStage.setScene(scene);
		primaryStage.show();

		Executors.newSingleThreadExecutor().execute(() -> view.go());
	}
}
