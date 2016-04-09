package com.thales.ga;

import com.thales.model.*;
import org.jenetics.Genotype;
import org.jenetics.IntegerChromosome;
import org.jenetics.IntegerGene;

import java.util.function.Function;
import java.util.stream.Collector;

public class ManifestFitnessFunction implements Function<Genotype<IntegerGene>, Double> {

	private final Vessel vessel;
	private final Store store;
	private final double size;

	public ManifestFitnessFunction(Vessel vessel, Store store, double size) {
		this.vessel = vessel;
		this.store = store;
		this.size = size;
	}

	private Collector<Item, double[], double[]> sum() {
		return Collector.of(() -> new double[2], (a, b) -> {
			a[0] += (Priority.LOWEST.getValue() - b.getPriority().getValue()) * (Urgency.ROUTINE.getValue() - b.getUrgency().getValue());
			a[1]++;
		} , (a, b) -> {
			a[0] += b[0];
			a[1] += b[1];
			return a;
		} , r -> r);
	}
	
	public Double apply(Genotype<IntegerGene> gt) {
		double[] value = ((IntegerChromosome)gt.getChromosome()).stream().map((i) -> store.getItem(i.intValue())).collect(sum());
		return value[1] <= this.size ? value[0] : 0;
	}

}
