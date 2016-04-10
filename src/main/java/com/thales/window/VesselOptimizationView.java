package com.thales.window;

import com.thales.ga.ManifestOptimiser;
import com.thales.model.Priority;
import com.thales.utils.FXExecutor;
import com.thales.window.Manifest.FitnessView;
import com.thales.window.Manifest.GenerationView;
import com.thales.window.Manifest.ManifestView;
import com.thales.window.deckView.DeckView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.jenetics.stat.DoubleMomentStatistics;

/**
 * Created by Administrator on 8/04/2016.
 */
public class VesselOptimizationView extends HBox {

	// TODO
	final DeckView deckView = new DeckView();

	final ColorMenubar menuBar;

	final GenerationView generationView = new GenerationView();

	final FitnessView fitnessView = new FitnessView();

	final ManifestView manifestView = new ManifestView();

	private final ManifestOptimiser optimiser;

	public VesselOptimizationView(ManifestOptimiser optimiser) {
		this.optimiser = optimiser;
		VBox leftBox = new VBox();

		menuBar = new ColorMenubar(deckView);

		leftBox.getChildren().addAll(generationView, fitnessView, manifestView);

		this.getChildren().addAll(leftBox, menuBar);

	}

	public void go() {
		optimiser.optimise((g, s, m) -> {
			FXExecutor.INSTANCE.execute(() -> {
				if (g % 5 == 0) {
					DoubleMomentStatistics fitness = (DoubleMomentStatistics) s.getFitness();
					fitnessView.addDataToQueue(fitness.getMin(), fitness.getMean());
					generationView.setPriority(
							m.getItems().stream().filter((item) -> item.getPriority().equals(Priority.HIGH)).count());
					manifestView.update(m);
					generationView.setBoxCount(m.getItems().size());
					deckView.updateDeck(m);
				}

				generationView.setGeneration(g);
			});
		});
	}

}
