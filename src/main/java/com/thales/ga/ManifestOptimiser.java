package com.thales.ga;

import static org.jenetics.engine.EvolutionResult.toBestPhenotype;

import java.util.Comparator;

import org.jenetics.EnumGene;
import org.jenetics.Genotype;
import org.jenetics.Optimize;
import org.jenetics.PartiallyMatchedCrossover;
import org.jenetics.PermutationChromosome;
import org.jenetics.Phenotype;
import org.jenetics.RouletteWheelSelector;
import org.jenetics.SwapMutator;
import org.jenetics.TournamentSelector;
import org.jenetics.engine.Engine;
import org.jenetics.engine.EvolutionStatistics;
import org.jenetics.engine.limit;
import org.jenetics.util.ISeq;

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

	private static class ManifestComparitor implements Comparator<EnumGene<Item>> {

		@Override
		public int compare(EnumGene<Item> o1, EnumGene<Item> o2) {
			Item item1 = o1.getAllele();
			Item item2 = o2.getAllele();
			int delta = item1.getDestination().compareTo(item2.getDestination());
			if(delta == 0) {
				return item1.getPriority().compareTo(item2.getPriority());
			}
			return delta;
		}

	}

	private Manifest create(Genotype<EnumGene<Item>> genotype) {
		Manifest manifest = new Manifest(vessel);
		((PermutationChromosome<Item>) genotype.getChromosome()).stream().sorted(new ManifestComparitor())
				.forEach((i) -> manifest.addItem(i.getAllele()));
		return manifest;
	}

	public Manifest optimise(ManifestOptimsationFunction func) {
		ISeq<Item> seq = ISeq.of(store.allItems());
		PermutationChromosome<Item> c = PermutationChromosome.of(seq, vessel.getDimension().size);
		final Engine<EnumGene<Item>, Double> engine = Engine.builder(new ManifestFitnessFunction(vessel), c)
				.optimize(Optimize.MINIMUM).populationSize(500).maximalPhenotypeAge(50)
				.alterers(new SwapMutator<>(0.2), new PartiallyMatchedCrossover<>(0.35)).build();

		EvolutionStatistics<Double, ?> statistics = EvolutionStatistics.ofNumber();

		Phenotype<EnumGene<Item>, Double> best = engine.stream().limit(limit.bySteadyFitness(200)).peek(statistics)
				.limit(10000)
				.peek((r) -> func.apply(r.getGeneration(), statistics, create(r.getBestPhenotype().getGenotype())))
				.collect(toBestPhenotype());

		return create(best.getGenotype());
	}

}
