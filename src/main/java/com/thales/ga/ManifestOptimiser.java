package com.thales.ga;

import static org.jenetics.engine.EvolutionResult.toBestPhenotype;

import java.util.List;
import java.util.function.Function;

import org.jenetics.AnyChromosome;
import org.jenetics.BitChromosome;
import org.jenetics.Chromosome;
import org.jenetics.EnumGene;
import org.jenetics.Genotype;
import org.jenetics.IntegerChromosome;
import org.jenetics.IntegerGene;
import org.jenetics.MultiPointCrossover;
import org.jenetics.Mutator;
import org.jenetics.Optimize;
import org.jenetics.PartiallyMatchedCrossover;
import org.jenetics.PermutationChromosome;
import org.jenetics.Phenotype;
import org.jenetics.RouletteWheelSelector;
import org.jenetics.SinglePointCrossover;
import org.jenetics.SwapMutator;
import org.jenetics.TournamentSelector;
import org.jenetics.engine.Engine;
import org.jenetics.engine.EvolutionStatistics;
import org.jenetics.util.ISeq;

import com.thales.model.Destination;
import com.thales.model.Item;
import com.thales.model.Manifest;
import com.thales.model.Priority;
import com.thales.model.Store;
import com.thales.model.Urgency;
import com.thales.model.Vessel;

public class ManifestOptimiser {

	private final Store store;
	private final Vessel vessel;

	public ManifestOptimiser(Store store, Vessel vessel) {
		this.store = store;
		this.vessel = vessel;
	}

	private Manifest create(Genotype<EnumGene<Item>> genotype) {
		Manifest manifest = new Manifest(vessel);
		((PermutationChromosome<Item>) genotype.getChromosome()).stream()
				.forEach((i) -> manifest.addItem(i.getAllele()));
		return manifest;

	}

	public Manifest optimise(ManifestOptimsationFunction func) {
		// ManifestFitnessFunction ff1 = new ManifestFitnessFunction(vessel,
		// store);

		// IntegerChromosome c = IntegerChromosome.of(0, store.size() - 1,
		// vessel.getDimension().size);
		ISeq<Item> seq = ISeq.of(store.allItems());
		PermutationChromosome<Item> c = PermutationChromosome.of(seq, vessel.getDimension().size);
		final Engine<EnumGene<Item>, Double> engine = Engine.builder(new FitnessFunction2(vessel), c)
				.optimize(Optimize.MAXIMUM).populationSize(50).survivorsSelector(new TournamentSelector<>(5))
				.offspringSelector(new RouletteWheelSelector<>()).maximalPhenotypeAge(500)
				.alterers(new SwapMutator<>(0.2), new PartiallyMatchedCrossover<>(0.35)).build();

		EvolutionStatistics<Double, ?> statistics = EvolutionStatistics.ofNumber();

		Phenotype<EnumGene<Item>, Double> best = engine.stream().peek(statistics).limit(1000)
				.peek((r) -> func.apply(r.getGeneration(), statistics, create(r.getBestPhenotype().getGenotype())))
				.collect(toBestPhenotype());

		// return create(best.getGenotype());
		return null;
	}

}
