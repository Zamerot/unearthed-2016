package com.thales.ga;

import static org.jenetics.engine.EvolutionResult.toBestPhenotype;

import java.util.List;

import org.jenetics.AnyChromosome;
import org.jenetics.BitChromosome;
import org.jenetics.EnumGene;
import org.jenetics.Genotype;
import org.jenetics.IntegerChromosome;
import org.jenetics.IntegerGene;
import org.jenetics.MultiPointCrossover;
import org.jenetics.Mutator;
import org.jenetics.Optimize;
import org.jenetics.PermutationChromosome;
import org.jenetics.Phenotype;
import org.jenetics.RouletteWheelSelector;
import org.jenetics.SinglePointCrossover;
import org.jenetics.SwapMutator;
import org.jenetics.TournamentSelector;
import org.jenetics.engine.Engine;
import org.jenetics.engine.EvolutionStatistics;

import com.thales.model.Item;
import com.thales.model.Manifest;
import com.thales.model.Store;
import com.thales.model.Vessel;

public class ManifestOptimiser {

	private final Store store;
	private final Vessel vessel;

	public ManifestOptimiser(Store store, Vessel vessel) {
		this.store = store;
		this.vessel = vessel;
	}

	private Manifest create(Genotype<IntegerGene> genotype) {
		Manifest manifest = new Manifest(vessel);
		((IntegerChromosome) genotype.getChromosome()).stream()
				.forEach((i) -> manifest.addItem(store.getItem(i.intValue())));
		return manifest;

	}

	public Manifest optimise(ManifestOptimsationFunction func) {
		ManifestFitnessFunction ff1 = new ManifestFitnessFunction(vessel, store);
		ManifestFitnessFunction2 ff = new ManifestFitnessFunction2(vessel, store);

		IntegerChromosome c = IntegerChromosome.of(0, store.size() - 1, vessel.getDimension().size);
		//PermutationChromosome<List<Item>> c = PermutationChromosome.of(store.allItems());
		Engine<IntegerGene, Double> engine = Engine.builder(ff1, c)
				.optimize(Optimize.MAXIMUM)
				.populationSize(50)
				.survivorsSelector(new TournamentSelector<>(5))
				.offspringSelector(new RouletteWheelSelector<>())
				.maximalPhenotypeAge(500)
				.alterers(new SwapMutator(), new MultiPointCrossover(6)).build();

		EvolutionStatistics<Double, ?> statistics = EvolutionStatistics.ofNumber();

		Phenotype<IntegerGene, Double> best = engine.stream().peek(statistics)
				.peek((r) -> func.apply(r.getGeneration(), statistics, create(r.getBestPhenotype().getGenotype())))
				.collect(toBestPhenotype());

		return create(best.getGenotype());
	}

}
