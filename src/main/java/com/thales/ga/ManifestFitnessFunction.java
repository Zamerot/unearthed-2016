package com.thales.ga;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collector;

import org.jenetics.Genotype;
import org.jenetics.IntegerGene;
import org.jenetics.util.ISeq;

import com.thales.model.Destination;
import com.thales.model.Item;
import com.thales.model.Priority;
import com.thales.model.Store;
import com.thales.model.Urgency;
import com.thales.model.Vessel;

public class ManifestFitnessFunction implements Function<Genotype<IntegerGene>, Double> {

	private final Vessel vessel;
	private final Store store;

	public ManifestFitnessFunction(Vessel vessel, Store store) {
		this.vessel = vessel;
		this.store = store;
	}

	public Double apply(Genotype<IntegerGene> gt) {
	
		double fitness = 0;
		
		ISeq<IntegerGene> seq = gt.getChromosome().toSeq();
		for(int i = 0; i < seq.size(); i++) {
			Item item = store.getItem(seq.get(i).intValue());
			int x = i % vessel.getDimension().width;
			int y = i / vessel.getDimension().width;
			int sector = x / Destination.values().length; // actual sector
			int distance = Destination.values().length - Math.abs(item.getDestination().ordinal() - sector); // distance from optimal
			double value = (item.getDestination().ordinal() - sector) * (Priority.LOWEST.getValue() - item.getPriority().getValue()) * (Urgency.ROUTINE.getValue() - item.getUrgency().getValue());
			fitness += value * distance;
		}

		return fitness;
	}

}
