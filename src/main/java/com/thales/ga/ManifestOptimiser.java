package com.thales.ga;

import java.util.Random;

import org.jenetics.BitChromosome;
import org.jenetics.BitGene;
import org.jenetics.Mutator;
import org.jenetics.Optimize;
import org.jenetics.Phenotype;
import org.jenetics.RouletteWheelSelector;
import org.jenetics.SinglePointCrossover;
import org.jenetics.TournamentSelector;
import org.jenetics.engine.Engine;
import org.jenetics.engine.EvolutionResult;
import org.jenetics.engine.EvolutionStatistics;
import org.jenetics.engine.limit;
import org.jenetics.util.RandomRegistry;

import com.thales.model.Item;
import com.thales.model.Priority;
import com.thales.model.Store;
import com.thales.model.Urgency;
import com.thales.model.Vessel;
import com.thales.model.Vessel.Dimension;

public class ManifestOptimiser {

	private static final Store store = new Store();

	private static final Vessel vessel = new Vessel("Boaty McBoat Face", new Dimension(10, 10));

	static Item random() {
		final Random r = RandomRegistry.getRandom();
		return store.getItem(r.nextInt(store.size()));
	}

	public static void main(String[] args) {

		for (int i = 0; i < 100; i++) {
			int p = RandomRegistry.getRandom().nextInt(Priority.values().length);
			int u = RandomRegistry.getRandom().nextInt(Urgency.values().length);
			store.addItem(new Item("FOOOOBARRR", Priority.values()[p], Urgency.values()[u]));
		}

		Dimension deck = vessel.getDimension();
		int size = deck.width * deck.height;
		ManifestFitnessFunction ff = new ManifestFitnessFunction(store, size);

		final Engine<BitGene, Double> engine = Engine.builder(ff, BitChromosome.of(size, 0.5))
				.optimize(Optimize.MAXIMUM).populationSize(500).survivorsSelector(new TournamentSelector<>(5))
				.offspringSelector(new RouletteWheelSelector<>())
				.alterers(new Mutator<>(0.115), new SinglePointCrossover<>(0.16)).build();

		final EvolutionStatistics<Double, ?> statistics = EvolutionStatistics.ofNumber();

		final Phenotype<BitGene, Double> best = engine.stream().limit(1000)
				.peek(statistics).collect(EvolutionResult.toBestPhenotype());

		System.out.println(statistics);
		System.out.println(best);
	}

}
