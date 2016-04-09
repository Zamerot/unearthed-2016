package com.thales.window;

import com.thales.ga.ManifestOptimiser;
import com.thales.utils.FXExecutor;
import com.thales.window.Manifest.ManifestView;
import com.thales.window.deckView.DeckView;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.lang.management.PlatformLoggingMXBean;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.jenetics.stat.DoubleMomentStatistics;

/**
 * Created by Administrator on 8/04/2016.
 */
public class VesselOptimizationView extends HBox {

	// TODO
	final Pane deckView = new DeckView();

	final Label generationView = new Label("Count");

	private int count = 0;

	final FitnessView fitnessView = new FitnessView();

	final Executor executor = Executors.newSingleThreadExecutor();

	final ManifestView manifestView = new ManifestView();

	private final ManifestOptimiser optimiser;

	public VesselOptimizationView(ManifestOptimiser optimiser) {
		this.optimiser = optimiser;
		VBox leftBox = new VBox();

		leftBox.getChildren().addAll(generationView, fitnessView, manifestView);

		this.getChildren().addAll(leftBox, deckView);

	}

	public void go() {
		optimiser.optimise((g, s, m) -> {
			if (g % 20 == 0) {
				FXExecutor.INSTANCE.execute(() -> {
					generationView.setText("Generation " + g);
					DoubleMomentStatistics fitness = (DoubleMomentStatistics) s.getFitness();
					fitnessView.addDataToQueue(fitness.getMax());
					manifestView.update(m);
				});
			}
		});
	}

}
