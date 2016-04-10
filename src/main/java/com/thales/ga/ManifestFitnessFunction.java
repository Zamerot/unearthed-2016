package com.thales.ga;

import java.util.List;
import java.util.function.Function;

import org.jenetics.Chromosome;
import org.jenetics.EnumGene;
import org.jenetics.Genotype;
import org.jenetics.util.ISeq;

import com.thales.model.Item;
import com.thales.model.Manifest;
import com.thales.model.Vessel;

public class ManifestFitnessFunction implements Function<Genotype<EnumGene<Item>>, Double> {

	private final List<Vessel> vessels;

	public ManifestFitnessFunction(List<Vessel> vessels) {
		this.vessels = vessels;
	}

	@Override
	public Double apply(Genotype<EnumGene<Item>> gt) {
		ISeq<Chromosome<EnumGene<Item>>> seq = gt.toSeq();
		double tcost = 0;
		for(int i = 0; i < vessels.size(); i++) {
			Vessel vessel = vessels.get(i);
			List<EnumGene<Item>> genes = gt.getChromosome().toSeq().subSeq(i, i + vessel.getDimension().size).asList();
			double vcost = 0;
			for (EnumGene<Item> c : gt.getChromosome().toSeq()) {
				Item item = c.getAllele();
//				int x = c.getAlleleIndex() % vessel.getDimension().width;
//				int y = c.getAlleleIndex() / vessel.getDimension().width;
				vcost += (item.getPriority().getValue() * item.getUrgency().getValue());
//				fitness += ((Priority.LOWEST.getValue() - item.getPriority().getValue())
//						* (Urgency.ROUTINE.getValue() - item.getUrgency().getValue()));
			}
			tcost += vcost / genes.size();
		}

		
		return tcost;
	}

}
