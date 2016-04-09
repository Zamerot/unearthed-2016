package com.thales.window;

import com.thales.ga.ManifestOptimiser;
import com.thales.utils.FXExecutor;
import com.thales.window.Manifest.GenerationView;
import com.thales.window.Manifest.ManifestView;
import com.thales.window.deckView.DeckView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.jenetics.stat.DoubleMomentStatistics;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 8/04/2016.
 */
public class VesselOptimizationView extends HBox {

	// TODO
	final DeckView deckView = new DeckView();

	final GenerationView generationView = new GenerationView();

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
					generationView.setGeneration(g);
					DoubleMomentStatistics fitness = (DoubleMomentStatistics) s.getFitness();
					fitnessView.addDataToQueue(fitness.getMax());
					manifestView.update(m);
					deckView.updateDeck(m);
				});
			}
		});
	}

}
