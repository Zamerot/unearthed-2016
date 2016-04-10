package com.thales.ga;

import java.util.function.Function;

import org.jenetics.EnumGene;
import org.jenetics.Genotype;

import com.thales.model.Item;
import com.thales.model.Vessel;

public class ManifestFitnessFunction implements Function<Genotype<EnumGene<Item>>, Double> {

	private final Vessel vessel;

	public ManifestFitnessFunction(Vessel vessel) {
		this.vessel = vessel;
	}

	@Override
	public Double apply(Genotype<EnumGene<Item>> gt) {
		double cost = 0.0;
		for (EnumGene<Item> c : gt.getChromosome().toSeq()) {
			Item item = c.getAllele();
			int x = c.getAlleleIndex() % vessel.getDimension().width;
			int y = c.getAlleleIndex() / vessel.getDimension().width;
			cost += (item.getPriority().getValue() * item.getUrgency().getValue());
//			fitness += ((Priority.LOWEST.getValue() - item.getPriority().getValue())
//					* (Urgency.ROUTINE.getValue() - item.getUrgency().getValue()));
		}
		return cost;
	}

}
