package com.thales.ga;

import java.util.function.Function;

import org.jenetics.EnumGene;
import org.jenetics.Genotype;

import com.thales.model.Destination;
import com.thales.model.Item;
import com.thales.model.Priority;
import com.thales.model.Urgency;
import com.thales.model.Vessel;

public class ManifestFitnessFunction implements Function<Genotype<EnumGene<Item>>, Double> {

	private final Vessel vessel;

	public ManifestFitnessFunction(Vessel vessel) {
		this.vessel = vessel;
	}

	@Override
	public Double apply(Genotype<EnumGene<Item>> gt) {
		double fitness = 0.0;
		for (EnumGene<Item> c : gt.getChromosome().toSeq()) {
			Item item = c.getAllele();
			int x = c.getAlleleIndex() % vessel.getDimension().width;
			int y = c.getAlleleIndex() / vessel.getDimension().width;
			int sector = x / Destination.values().length; // actual sector
			int distance = Destination.values().length - Math.abs(item.getDestination().ordinal() - sector);
			fitness += ((Priority.LOWEST.getValue() - item.getPriority().getValue())
					* (Urgency.ROUTINE.getValue() * item.getUrgency().getValue()));
		}
		return fitness;
	}

}
